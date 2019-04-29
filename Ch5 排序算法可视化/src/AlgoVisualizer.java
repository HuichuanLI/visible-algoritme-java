import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private SelectionSort data;        // 数据 model
    private AlgoFrame frame;    // 视图 view
    private boolean run1 = false;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        // 初始化数据
        data = new SelectionSort(N, sceneHeight);

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

    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent event) {
            if (event.getKeyChar() == ' ') {
                run1 = true;
            }
        }
    }

    // 动画逻辑
    private void run() {
        while (true) {
            frame.render(data);
            AlgoVisHelper.pause(2);
            if (run1) {

                for (int i = 0; i < data.N(); i++) {
                    int min = data.get(i);
                    int postion = i;
                    data.orderedIndex = i - 1;

                    for (int j = i + 1; j < data.N(); j++) {
                        data.currentCompareIndex = j;
                        if (data.get(j) < min) {
                            min = data.get(j);

                            postion = j;
                        }
                        data.currentMinindex = postion;
                        frame.render(data);
                        AlgoVisHelper.pause(2);
                    }

                    data.swap(postion, i);
                    frame.render(data);
                    AlgoVisHelper.pause(2);
                }
                data.orderedIndex = data.N();
                data.currentCompareIndex = -1;
                data.currentMinindex = -1;
                frame.render(data);
                AlgoVisHelper.pause(2);
                break;
            }
        }

    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, 100);
    }
}
