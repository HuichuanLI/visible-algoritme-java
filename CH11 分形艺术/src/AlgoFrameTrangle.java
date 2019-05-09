import java.awt.*;
import javax.swing.*;

public class AlgoFrameTrangle extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrameTrangle(String title, int canvasWidth, int canvasHeight) {

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

    public AlgoFrameTrangle(String title) {

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
            drawTriangle(g2d, 0, canvasHeight, canvasWidth, 0);
        }

        private void drawTriangle(Graphics2D g2d, int Ax, int Ay, int side, int depth) {
            if (side <= 1) {
                AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
                AlgoVisHelper.fillRectangle(g2d, Ax, Ay, 1, 1);
                return;
            }
            int Bx = Ax + side;
            int By = Ay;

            int Cx = Ax + side / 2;
            int Cy = Ay - (int) (Math.sin(60 * Math.PI / 180.0) * side);

            if (depth == data.depth) {
                AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
                AlgoVisHelper.fillTriangle(g2d, Ax, Ay, Bx,By,Cx,Cy);
            }

            int AB_centerx = (Ax + Bx) / 2;
            int AB_centery = (Ay + By) / 2;

            int AC_centerx = (Ax + Cx) / 2;
            int AC_centery = (Ay + Cy) / 2;

            int BC_centerx = (Bx + Cx) / 2;
            int BC_centery = (By + Cy) / 2;
            drawTriangle(g2d, Ax, Ay, side/2, depth+1);
            drawTriangle(g2d, AC_centerx, AC_centery, side/2, depth+1);
            drawTriangle(g2d, AB_centerx, AB_centery, side/2, depth+1);
            return;

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}

