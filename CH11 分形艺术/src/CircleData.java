public class CircleData {


    private int startX,startY;
    private  int startR;
    private int depth;
    private int step;

    public CircleData(int startX, int startY, int startR, int depth, int step) {
        this.startX = startX;
        this.startY = startY;
        this.startR = startR;
        this.depth = depth;
        this.step = step;
    }

    public int getDepth() {
        return depth;
    }

    public int getStartR() {
        return startR;
    }

    public int getStartY() {
        return startY;
    }

    public int getStartX() {
        return startX;
    }

    public int getStep() {
        return step;
    }
}
