import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;


// view
public class AlgoFrame extends JFrame {


    private int canvasWidth;
    private int canvasHeigth;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {

        super(title);
        this.canvasHeigth = canvasHeight;
        this.canvasWidth = canvasWidth;
        AlgoCanvas canvas = new AlgoCanvas();
        // canvas.setPreferredSize(new Dimension(canvasWidth,canvasHeight));
        this.setContentPane(canvas);

        // 自适应
        pack();

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }


    public AlgoFrame(String title) {
        this(title, 1024, 768);
    }

    public int getCanvasHeigth() {
        return canvasHeigth;
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }


    public Circle[] circles;

    public void Render(Circle[] circles) {
        this.circles = circles;
        repaint();
    }


    // 画像元素 为私有函数
    private class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
            super(true);
        }

        // 函数签名
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 个性化
            // 转换 graph2ds

            int strokeWidth = 5;

            Graphics2D g2d = (Graphics2D) g;
            AlgoVisHelper.setStrokeWidth(g2d, strokeWidth);
            // 抗锯齿 怎么做 alisaed
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);

            AlgoVisHelper.setStrokeWidth(g2d, 1);
            AlgoVisHelper.setColor(g2d, Color.red);

            for (Circle circle : circles){
                if(circle.isFilled == false)
                    AlgoVisHelper.strokeCircle(g2d, circle.x, circle.y, circle.getR());
                else
                    AlgoVisHelper.fillCircle(g2d, circle.x, circle.y, circle.getR());
            }

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeigth);
        }


        //graph2d
    }
}
