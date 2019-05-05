public class Postion {
    private int x,y;
    private Postion prev;

    public Postion(int x, int y,Postion prev) {
        this.x = x;
        this.y = y;
        this.prev = prev;
    }

    public Postion(int x, int y) {
        this.x = x;
        this.y = y;
        this.prev = null;
    }

    public Postion getPrev() {
        return prev;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

