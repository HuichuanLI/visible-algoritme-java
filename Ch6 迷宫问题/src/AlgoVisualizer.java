import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

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
        setData(-1, -1, true);

        Stack<Postion> stack = new Stack<>();
        Postion entrance = new Postion(data.getEntranceX(), data.getEntranceY());
        stack.push(entrance);
        data.visited[entrance.getX()][entrance.getY()] = true;

        while (!stack.empty()) {
            Postion curPos = stack.pop();
            setData(curPos.getX(), curPos.getY(), true);

            if (curPos.getX() == data.getExitX() && curPos.getY() == data.getExitY())
                break;

            for (int i = 0; i < 4; i++) {
                int newX = curPos.getX()+d[i][0];
                int newY = curPos.getY()+d[i][1];

                if(data.inArea(newX,newY) && !data.visited[newX][newY] && data.getMaze(newX,newY)!= MazeData.WALL){
                    stack.push(new Postion(newX,newY));
                    data.visited[newX][newY] = true;
                }
            }
        }

        setData(-1, -1, true);

    }


    private void setData(int x, int y, boolean isPath) {
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
