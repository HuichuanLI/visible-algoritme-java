import java.awt.*;
import java.awt.event.*;

// model

public class AlgoVisualizer {
    // 数据
    private Circle[] circles;
    // view
    private AlgoFrame frame;
    private boolean isanimated = true;
    public AlgoVisualizer(int sceneWidth, int sceneHeigth, int N) {

        circles = new Circle[N];
        int R = 50;
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * (sceneWidth - 2 * R)) + R;
            int y = (int) (Math.random() * (sceneHeigth - 2 * R)) + R;
            int vx = (int) (Math.random() * 11) - 5;
            int vy = (int) (Math.random() * 11) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }

        // 事件分发线程
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeigth);
            // 添加事件
            frame.addKeyListener(new AlgokeyListener());

            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();

        });
    }

    // 动画逻辑 重要
    private void run() {
        while (true) {
            frame.Render(circles);
            AlgoVisHelper.pause(20);
            if(isanimated)
                for (Circle circle : circles) {
                    circle.move(0, 0, frame.getCanvasWidth(), frame.getCanvasHeigth());
                }
        }
    }

    private class AlgokeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent event){
            if(event.getKeyChar() == ' '){
                isanimated = !isanimated;
            }
        }
    }

    private class AlgoMouseListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent event){

            //System.out.println(event.getPoint());
            event.translatePoint(0,
                    -(frame.getBounds().height - frame.getCanvasHeigth()));

            for(Circle circle:circles){
                if(circle.contain(event.getPoint())){
                    circle.isFilled = !circle.isFilled;
                }
            }

        }
    }

}
