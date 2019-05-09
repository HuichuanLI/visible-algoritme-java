import java.awt.*;
import javax.swing.*;

public class AlgoFrameKroch extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrameKroch(String title, int canvasWidth, int canvasHeight) {

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

    public AlgoFrameKroch(String title) {

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

            drawLines(g2d, 0, canvasHeight - 3, canvasWidth, 0, 0);
        }

        private void drawLines(Graphics2D g2d, double x1, double y1, double side, double angle, int depth) {
            if (side <= 0)
                return;
            if (depth == data.depth) {
                double x2 = x1 + side * Math.cos(Math.PI / 180.0 * angle);
                double y2 = y1 - side * Math.sin(Math.PI / 180.0 * angle);

                AlgoVisHelper.setColor(g2d, AlgoVisHelper.Blue);
                AlgoVisHelper.drawLine(g2d, x1, y1, x2, y2);
                return;
            }

            double side_3 = side / 3;
            double x2 = x1 + side_3 * Math.cos(angle * Math.PI /180.0);
            double y2 = y1 - side_3 * Math.sin(angle * Math.PI /180.0);
            drawLines(g2d,x1,y1,side_3,angle,depth+1);

            double x3 = x2 + side_3 * Math.cos((angle+60.0) * Math.PI /180.0);
            double y3 = y2 - side_3 * Math.sin((angle+60.0) * Math.PI /180.0);
            drawLines(g2d,x2,y2,side_3,angle+60.0,depth+1);

            double x4 = x3 + side_3 * Math.cos((angle - 60) * Math.PI /180.0);
            double y4 = y3 - side_3 * Math.sin((angle - 60) * Math.PI /180.0);
            drawLines(g2d,x3,y3,side_3,angle-60.0,depth+1);
            drawLines(g2d,x4,y4,side_3,angle,depth+1);
            return;


        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}

