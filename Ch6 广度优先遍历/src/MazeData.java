import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class MazeData {
    public static final char ROAD = ' ';
    public static final char WALL = '#';


    private int M, N;
    private char[][] maze;
    private  int entranceX,entranceY;
    private  int exitX,exitY;

    public boolean [][] visited;
    public  boolean [][] path;
    public  boolean [][] result;
    public MazeData(String filename) {
        if (filename == null)
            throw new IllegalArgumentException("filne name is not null");
        Scanner scanner = null;
        try {
            File file = new File(filename);
            if (!file.exists())
                throw new IllegalArgumentException(filename + "not exists!");
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
            String nmline = scanner.nextLine();
            String[] nm = nmline.trim().split("\\s+");
            N = Integer.parseInt(nm[0]);
            M = Integer.parseInt(nm[1]);

            maze = new char[M][N];
            visited = new boolean[M][N];
            path = new boolean[M][N];
            result = new boolean[M][N];
            for (int i = 0; i < N; i++) {
                String line = scanner.nextLine();
                if (line.length() != M){
                    System.out.println(line.length());
                    throw new IllegalArgumentException("error");
                }

                for (int j = 0; j < M; j++) {
                    maze[i][j] = line.charAt(j);
                    visited[i][j] = false;
                    path[i][j] = false;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null)
                scanner.close();
        }
        entranceX = 1;
        entranceY = 0;
        exitX = N - 2;
        exitY = M - 1;


    }

    public int M() {
        return M;
    }

    public int N() {
        return N;
    }

    public int getEntranceX() {
        return entranceX;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public char getMaze(int x, int y) {
        if (!inArea(x, y))
            throw new IllegalArgumentException("out of bound");
        return maze[x][y];
    }

    public boolean inArea(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }

    public  void print(){
        System.out.println(N + " " + M);
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++)
                System.out.print(maze[i][j]);
            System.out.println();
        }
        return;
    }

}
