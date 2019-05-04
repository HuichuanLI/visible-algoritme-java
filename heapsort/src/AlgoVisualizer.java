import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private HeapSortData data;        // 数据 model
    private AlgoFrame frame;    // 视图 view
    private boolean run1 = false;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        // 初始化数据
        // TODO: 初始化数据
        data = new HeapSortData(N, sceneHeight);
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
        // 建堆
        while (true) {

            setData(data.N());
            if (run1) {
                for (int i = (data.N() - 1 - 1) / 2; i >= 0; i--) {
                    shiftDown(data.N(), i);
                }
                // 堆排序
                for (int i = data.N() - 1; i > 0; i--) {
                    data.swap(0, i);
                    shiftDown(i, 0);
                    setData(i);
                }
                setData(0);
                break;
            }

        }


    }

    private void shiftDown(int n, int k) {

        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j + 1 < n && data.get(j + 1) > data.get(j))
                j += 1;

            if (data.get(k) >= data.get(j))
                break;

            data.swap(k, j);
            setData(data.heapIndex);

            k = j;
        }
    }


    private void setData(int heapIndex) {

        data.heapIndex = heapIndex;

        frame.render(data);
        AlgoVisHelper.pause(10);
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
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
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
