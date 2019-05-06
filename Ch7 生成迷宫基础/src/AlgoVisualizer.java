import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Stack;

public class AlgoVisualizer {
    private static int blocksize = 8;
    // TODO: 创建自己的数据
    private MazeData data;        // 数据 model
    private AlgoFrame frame;    // 视图 view

    // 四个方向
    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};


    public AlgoVisualizer(int N, int M) {
        data = new MazeData(N, M);
        int sceneHeight = data.N() * blocksize;
        int sceneWidth = data.M() * blocksize;

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Random Maze Generation Visualization", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {

        // TODO: 编写自己的动画逻辑
        setData(-1, -1);

//        go(data.getEntranceX(), data.getEntranceY() + 1);
        RandomQueue<Position> stack = new RandomQueue<>();
        stack.add(new Position(data.getEntranceX(), data.getEntranceY() + 1));
        data.openMist(data.getEntranceX(), data.getEntranceY() + 1);

        data.visited[data.getEntranceX()][data.getEntranceY() + 1] = true;
        while ( stack.size() !=0)  {
            Position curPostion = stack.remove();
            for (int i = 0; i < 4; i++) {
                int newX = curPostion.getX() + d[i][0] * 2;
                int newY = curPostion.getY() + d[i][1] * 2;
                if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                    stack.add(new Position(newX,newY));
                    data.visited[newX][newY] = true;
                    data.openMist(newX, newY);
                    setData(curPostion.getX() + d[i][0], curPostion.getY() + d[i][1]);

                }
            }
            setData(-1, -1);

        }
    }

//    public void go(int x, int y) {
//        if (!data.inArea(x, y))
//            throw new IllegalArgumentException("out og bound!");
//        data.visited[x][y] = true;
//        for (int i = 0; i < 4; i++) {
//            int newX = x + d[i][0] * 2;
//            int newY = y + d[i][1] * 2;
//            if(data.inArea(newX, newY) && !data.visited[newX][newY]){
//                setData(x + d[i][0], y + d[i][1]);
//                go(newX, newY);
//            }
//        }
//
//    }

    private void setData(int x, int y) {
        if (data.inArea(x, y))
            data.maze[x][y] = MazeData.ROAD;
        frame.render(data);
        AlgoVisHelper.pause(5);
    }

    public static void main(String[] args) {
        int N = 101;
        int M = 101;

        AlgoVisualizer vis = new AlgoVisualizer(N, M);


    }
}
