import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private QuickSortData data;        // 数据 model
    private AlgoFrame frame;    // 视图 view
    private boolean run1 = false;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, QuickSortData.Type dataType) {

        // 初始化数据
        // TODO: 初始化数据
        data = new QuickSortData(N, sceneHeight, dataType);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {

        while (true) {
            setData(0, data.N() - 1, -1, -1, -1, -1);
            if (run1) {
                setData(-1, -1, -1, -1, -1, -1);
                quickSort3ways(0, data.N() - 1);
                setData(-1, -1, -1, -1, -1, -1);
                break;
            }
        }


    }


    private void quickSort3ways(int l, int r) {
        if (l > r)
            return;
        if (l == r) {
            setData(l, r, r, -1, -1, -1);
        }
        setData(l, r, -1, -1, -1, -1);


        int p = (int) (Math.random() * (r - l + 1)) + l;
        setData(l, r, -1, p, -1, -1);

        int lt = l; // arr[l+1...lt] < v
        int gt = r + 1; // arr[gt+1 ... r] >v
        int i = l + 1; // arr[lt+1...i] == v

        data.swap(l, p);
        int v = data.get(l);
        setData(l, r, -1, l, lt, gt);

        while (i < gt) {
            if (data.get(i) < v) {
                data.swap(i, lt + 1);
                i++;
                lt++;
            } else if (data.get(i) > v) {
                data.swap(i, gt - 1);
                gt--;
            } else
                i++;
            setData(l, r, -1, l, i, gt);
        }
        data.swap(l, lt);
        for (int index = lt; index <= gt - 1; index++) {
            setData(l, r, index, -1, -1, -1);
        }

        setData(l, r, lt, -1, -1, -1);
        quickSort3ways(l, lt - 1);
        quickSort3ways(gt, r);
    }


//    private void quickSort(int l, int r) {
//        if (l > r)
//            return;
//        if (l == r) {
//            setData(l, r, r, -1, -1, -1);
//        }
//        setData(l, r, -1, -1, -1, -1);
//        int p = partition(l, r);
//        quickSort(l, p - 1);
//        quickSort(p + 1, r);
//    }

//    private int partition(int l, int r) {
//        int p = (int) (Math.random() * (r - l + 1) + l);
//        setData(l, r, -1, p, -1,-1);
//        data.swap(l,p);
//
//        int v = data.get(l);
//        int j = l;
//        for (int i = l + 1; i <= r; i++) {
//            setData(l, r, -1, l, );
//            if (data.get(i) < v) {
//                j++;
//                data.swap(i, j);
//                setData(l, r, -1, l, i);
//            }
//        }
//        data.swap(l, j);
//        setData(l, r, j, -1, -1);
//        return j;
//    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curElementL, int curElementR) {
        data.l = l;
        data.r = r;
        if (fixedPivot != -1) {
            data.fixedPivots[fixedPivot] = true;
        }
        data.curPivot = curPivot;
        data.curL = curElementL;
        data.curR = curElementR;
        frame.render(data);
        AlgoVisHelper.pause(10);
    }

    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == ' ') {
                run1 = true;
            }
        }
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N, QuickSortData.Type.Default);
    }
}
