import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private InsertionSort data;        // 数据 model
    private AlgoFrame frame;    // 视图 view
    private boolean run1;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        // 初始化数据
        // TODO: 初始化数据
        this.data = new InsertionSort(N, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);

            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {

                run();

            }).start();
        });
    }

    // 动画逻辑
    private void run() {
        while (true) {
            frame.render(data);

            AlgoVisHelper.pause(3);
            if (run1) {
                data.orderedIndex = 0;
                data.currentIndex = -1;
                frame.render(data);

                AlgoVisHelper.pause(3);
                for (int i = 1; i < data.N(); i++) {
                    data.orderedIndex = i - 1;
                    data.currentIndex = i;
                    frame.render(data);
                    AlgoVisHelper.pause(3);
                    for (int j = i; j > 0; j--) {
                        if (data.get(j - 1) > data.get(j)) {
                            data.swap(j, j - 1);
                            data.orderedIndex = i + 1;
                            data.currentIndex = j - 1;
                            frame.render(data);
                            AlgoVisHelper.pause(3);
                        }
                    }
                }
                data.orderedIndex = data.N();
                data.currentIndex = -1;
                frame.render(data);
                AlgoVisHelper.pause(3);
                break;
            }
        }

    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent event) {
            if (event.getKeyChar() == ' ') {
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
