import java.awt.*;

public class circle {
    private int x,y,r;

    public circle(int x, int y, int r){
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public boolean contain(Point p){
        return Math.pow(p.getX()-x,2) + Math.pow(p.getY()-y,2) <= r*r;
    }



}
