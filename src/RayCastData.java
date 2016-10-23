/**
 * Created by Ramon Brand on 22-Oct-16.
 */
public class RayCastData {

    public Point hitPoint;
    public double distance;
    public Segment collidedSegment;


    public String toString(){
        return "Point[ " + hitPoint + " ] Distance: " + distance;
    }
}
