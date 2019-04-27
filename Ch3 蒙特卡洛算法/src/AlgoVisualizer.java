import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class AlgoVisualizer {

    private MonteCarolePiData monteCarolePiData;
    private AlgoFrame frame;    // 视图 view
    private int N;

    private int insisecircle = 0;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {


        if (sceneHeight != sceneHeight)
            throw new IllegalArgumentException("the demo must be run in a square");
        // 初始化数据
        // TODO: 初始化数据

        this.monteCarolePiData = new MonteCarolePiData(new circle(sceneHeight / 2, sceneHeight / 2, sceneHeight / 2));


        this.N = N;
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {

        for (int i = 0; i < N; i++) {
            frame.render(this.monteCarolePiData);


            if (i % 100 == 0) {
                double piestimation = monteCarolePiData.estimatePi();
                System.out.println(piestimation);
                AlgoVisHelper.pause(10);

            }

            int x = (int) (Math.random() * frame.getCanvasHeight());
            int y = (int) (Math.random() * frame.getCanvasHeight());

            Point p = new Point(x, y);
            monteCarolePiData.addPoint(p);
            if (monteCarolePiData.getCircle().contain(p)) {
                insisecircle++;
            }
        }
    }


    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, 10000);
    }
}
