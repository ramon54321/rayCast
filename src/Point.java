/**
 * Created by Ramon Brand on 21-Oct-16.
 */
public class Point {

    public double x;
    public double y;
    public double length;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Point(double x, double y, double length){
        this.x = x;
        this.y = y;
        this.length = length;
    }

    public String toString(){
        return "X: " + x + " Y: " + y + " L: " + length;
    }

}
