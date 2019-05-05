import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {
    private static int blocksize = 8;
    // TODO: 创建自己的数据
    private MazeData data;        // 数据 model
    private AlgoFrame frame;    // 视图 view

    // 四个方向
    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public AlgoVisualizer(String mazeFile) {
        data = new MazeData(mazeFile);
        int sceneHeight = data.N() * blocksize;
        int sceneWeight = data.M() * blocksize;

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("maze solver visualization", sceneWeight, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {

        // TODO: 编写自己的动画逻辑
        setData(-1,-1,true);

        if(!go(data.getEntranceX(), data.getEntranceY())){
            System.out.println("no solutions");
        }

        setData(-1,-1,true);

    }

    private boolean go(int x, int y) {
        if (!data.inArea(x, y)) {
            throw new IllegalArgumentException("error!");
        }
        data.visited[x][y] = true;

        setData(x,y,true);

        if (x == data.getExitX() && y == data.getExitY())
            return true;

        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (data.inArea(newX, newY) && data.getMaze(newX, newY) == MazeData.ROAD && !data.visited[newX][newY])
                if(go(newX, newY))
                    return true;

        }
        setData(x,y,false);
        return false;

    }


    private void setData(int x, int y,boolean isPath) {
        if (data.inArea(x, y))
            data.path[x][y] = isPath;
        frame.render(data);
        AlgoVisHelper.pause(5);
    }

    public static void main(String[] args) {
        String mazeFile = "maze.txt";
        AlgoVisualizer vis = new AlgoVisualizer(mazeFile);
    }
}
