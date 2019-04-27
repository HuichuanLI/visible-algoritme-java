
import java.awt.*;
import java.util.LinkedList;

public class MonteCarolePiData {
    private circle circle;
    private LinkedList<Point> points;
    private int insideCircle = 0;

    public MonteCarolePiData(circle circle) {
        this.circle = circle;
        this.points = new LinkedList<>();
    }

    public circle getCircle() {
        return circle;
    }

    public Point getPoint(int i){
        if(i < 0 || i>points.size())
            throw  new IllegalArgumentException("out of bound!");
        return points.get(i);
    }

    public int getPointsNumber(){
        return points.size();
    }

    public void addPoint(Point p){
        points.add(p);
        if(circle.contain(p))
            insideCircle++;
    }

    public double estimatePi(){
        if(points.size() == 0)
            return 0;
        int circleArea = insideCircle;
        int squareArea = points.size();
        return (double)circleArea * 4/squareArea;

    }
}
