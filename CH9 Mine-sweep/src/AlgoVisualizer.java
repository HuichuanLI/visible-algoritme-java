import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private static int blockSize = 32;
    private MineSweeperData data;        // 数据 model
    private AlgoFrame frame;    // 视图 view

    public AlgoVisualizer(int N, int M, int mineNumber) {

        // 初始化数据
        // TODO: 初始化数据

        data = new MineSweeperData(N, M, mineNumber);
        int sceneWidth = blockSize * M;
        int sceneHeight = blockSize * N;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Mine_sweep", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {

        setData(true,-1,-1);
    }


    private void setData(boolean isLeftClicked,int x,int y){
        if(data.inArea(x,y)){
            if(isLeftClicked){
                if(data.isMine(x,y))
                    data.open[x][y] = true;
                else
                    data.open(x,y);
            } else
                data.flags[x][y] = !data.flags[x][y];
        }
        frame.render(data);
        AlgoVisHelper.pause(5);
    }
    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter {
    }

    private class AlgoMouseListener extends MouseAdapter {
        @Override
        public void mouseReleased(MouseEvent e) {
            e.translatePoint(-(int)(frame.getBounds().width-frame.getCanvasWidth()),
                    -(int)(frame.getBounds().height-frame.getCanvasHeight()));
            Point pos = e.getPoint();
            int w = frame.getCanvasWidth()/data.M();
            int h = frame.getCanvasHeight()/data.N();

            int x = pos.y/h;
            int y = pos.x / w;
            if(SwingUtilities.isLeftMouseButton(e))
               setData(true,x,y);
            else
                setData(false,x,y);
        }

    }

    public static void main(String[] args) {

        int N = 20;
        int M = 20;
        int mineNumber = 20;
        int sceneWidth = 800;
        int sceneHeight = 800;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(N, M,mineNumber);
    }
}
