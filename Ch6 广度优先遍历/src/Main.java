public class Main {

    public static void main(String[] args) {

        String mazeFile = "maze.txt";
        MazeData data = new MazeData(mazeFile);
        data.print();
    }
}