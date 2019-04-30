import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private Bubblesortdata data;        // 数据 model
    private AlgoFrame frame;    // 视图 view
    private boolean run1 = false;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        // 初始化数据
        // TODO: 初始化数据
        data = new Bubblesortdata(N, sceneHeight);
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("bubble sort visualizes", sceneWidth, sceneHeight);
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
            frame.render(data);
            if (run1) {
                for (int i = 0; i < data.N(); i++) {
                    int max = data.getindex(i);
                    data.sorted = data.N()-i;
                    for (int j = 1; j < data.N() - i; j++) {
                        if (data.getindex(j - 1) > data.getindex(j)) {
                            data.swap(j, j - 1);
                            data.current = j;
                        }
                        frame.render(data);
                        AlgoVisHelper.pause(1);
                    }
                }
                data.current = -1;
                data.sorted = -1;
                frame.render(data);
                AlgoVisHelper.pause(1);
                break;
            }
        }
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
