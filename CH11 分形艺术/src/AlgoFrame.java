import java.awt.*;
import javax.swing.*;

public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);

        setResizable(false);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public AlgoFrame(String title) {

        this(title, 1024, 768);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    // TODO: 设置自己的数据
    private FractalData data;

    public void render(FractalData data) {
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
            // 双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 具体绘制
            // TODO： 绘制自己的数据data
            // 通过使用 hepler
            //drawCircle(g2d, data.getStartX(), data.getStartY(), data.getStartR(), 0);
            drawFractal(g2d, 0, 0, canvasWidth, canvasHeight, 0);
        }

//        private void drawCircle(Graphics2D g2d, int x, int y, int r, int depth) {
////            if (depth == data.getDepth() || r < 1) {
////                return;
////            }
////            if (depth % 2 == 0)
////                AlgoVisHelper.setColor(g2d, AlgoVisHelper.Red);
////            else
////                AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
////
////            AlgoVisHelper.fillCircle(g2d, x, y, r);
////            drawCircle(g2d, x, y, r - data.getStep(), depth + 1);
////        }

        private void drawFractal(Graphics2D g2d, int x, int y, int w, int h, int depth) {
            int w_3 = w / 3;
            int h_3 = h / 3;

            if (depth == data.depth) {
                AlgoVisHelper.fillRectangle(g2d, x + w_3, y + h_3, w_3, h_3);
                return;
            }
            if (w <= 1 || h <= 1) {
//                AlgoVisHelper.fillRectangle(g2d, x, y, Math.max(w, 1), Math.max(h, 1));
                return;
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 1 && j == 1)
                        AlgoVisHelper.fillRectangle(g2d, x + w_3, y + h_3, w_3, h_3);
                    else
                        drawFractal(g2d,x+i*w_3,y+j*h_3,w_3,h_3,depth+1);
                }
            }
//            drawFractal(g2d,x,y,w_3,h_3,depth+1);
//            drawFractal(g2d,x+2*w_3,y,w_3,h_3,depth+1);
//            drawFractal(g2d,x+w_3,y+w_3,w_3,h_3,depth+1);
//            drawFractal(g2d,x,y+w_3*2,w_3,h_3,depth+1);
//            drawFractal(g2d,x+w_3*2,y+w_3*2,w_3,h_3,depth+1);


        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}

