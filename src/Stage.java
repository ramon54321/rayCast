import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * Created by Ramon Brand on 21-Oct-16.
 */
public class Stage {

    private UIData uiData;

    public Stage(UIData uiData){
        this.uiData = uiData;
    }

    // Segments
    private Segment[] segments = new Segment[]{
            // Border
            new Segment(10,10,790,10),
            new Segment(790,10,790,590),
            new Segment(10,590,790,590),
            new Segment(10,590,10,10),

            // Polygon 1
            new Segment(100,150,120,50),
            new Segment(120,50,200,80),
            new Segment(200,80,140,210),
            new Segment(140,210,100,150),

            new Segment(490,150,620,50),
            new Segment(620,50,460,80),
            new Segment(460,80,340,210),
            new Segment(340,210,490,150),

            new Segment(50,420,110,490),
            new Segment(110,490,210,530),
            new Segment(210,530,340,560),
            new Segment(340,560,50,420),

            new Segment(200,300,420,220),
            new Segment(420,220,400,300),
            new Segment(400,300,200,380),
            new Segment(200,380,200,300),

            new Segment(620,480,655,555),
            new Segment(655,555,400,450),
            new Segment(400,450,620,480)
    };

    public void render(Graphics g){

        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);

        // Draw Segments
        g2.setColor(Color.black);
        for(Segment seg : segments){
            g2.drawLine((int)seg.ax, (int)seg.ay, (int)seg.bx, (int)seg.by);
        }

        // Draw Mouse
        g2.setColor(Color.red);
        g2.fillOval(uiData.mouseX-5, uiData.mouseY-5, 10, 10);

        // Draw ray
        //Segment ray = new Segment(400,300,uiData.mouseX, uiData.mouseY);

        for(int i = 0; i < 36; i++) {
            double mx = uiData.mouseX;
            double my = uiData.mouseY;
            double increment = Math.PI * 2 / 36;
            double angle = increment * i + 0.005;
            Segment ray = new Segment(mx, my,5 * Math.cos(angle) + mx, 5 * Math.sin(angle) + my);
            Point closestIntersect = null;
            for (Segment seg : segments) {
                Point intersect = getIntersection(ray, seg);
                if (intersect == null)
                    continue;
                if (closestIntersect == null || (intersect.length < closestIntersect.length && !Double.isNaN(intersect.length)))
                    closestIntersect = intersect;
            }
            //System.out.println("Closest Intersect = " + closestIntersect);

            if (closestIntersect != null)
                g2.drawLine((int) mx, (int) my, (int) closestIntersect.x, (int) closestIntersect.y);
        }

    }

    public void test(){
        RayCastData rayCast = castRay(uiData.mouseX,uiData.mouseY, 0);
        System.out.println(rayCast);
    }

    private RayCastData castRay(double originX, double originY, double angleInRadians){
        // Ray
        double mx = originX;
        double my = originY;
        Segment ray = new Segment(mx, my, Math.cos(angleInRadians) + mx, Math.sin(angleInRadians) + my);

        // Collision Detection
        Point closestIntersect = null;
        Segment closestSegment = null;
        for (Segment seg : segments) {
            Point intersect = getIntersection(ray, seg);
            if (intersect == null)
                continue;
            if (closestIntersect == null || (intersect.length < closestIntersect.length && !Double.isNaN(intersect.length))) {
                closestIntersect = intersect;
                closestSegment = seg;
            }
        }

        // Return Data
        RayCastData data = new RayCastData();
        data.collidedSegment = closestSegment;
        data.hitPoint = closestIntersect;
        data.distance = closestIntersect.length;
        return data;
    }

    private Point getIntersection(Segment s1, Segment s2){
        double r_px = s1.ax;
        double r_py = s1.ay;
        double r_dx = s1.bx - s1.ax;
        double r_dy = s1.by - s1.ay;

        double s_px = s2.ax;
        double s_py = s2.ay;
        double s_dx = s2.bx - s2.ax;
        double s_dy = s2.by - s2.ay;

        double r_mag = Math.sqrt(r_dx*r_dx+r_dy*r_dy);
        double s_mag = Math.sqrt(s_dx*s_dx+s_dy*s_dy);
        if(r_dx/r_mag==s_dx/s_mag && r_dy/r_mag==s_dy/s_mag){ // Directions are the same.
            return null;
        }
/*
        if(Math.abs(r_dx/r_mag - s_dx/s_mag) < 0.01 && Math.abs(r_dy/r_mag - s_dy/s_mag) < 0.01){ // Directions are the
            // same.
            //System.out.println("Parallel");
            return null;
        }
*/
        //if((s_dx*r_dy - s_dy*r_dx) == 0)
        //    return null;

        double T2 = (r_dx*(s_py-r_py) + r_dy*(r_px-s_px)) / (s_dx*r_dy - s_dy*r_dx);

        //System.out.println("s_px  " + s_px);
        //System.out.println("s_dx  " + s_dx);
        //System.out.println("T2    " + T2);
        //System.out.println("r_px  " + r_px);
        //System.out.println("r_dx  " + r_dx);
        //System.out.println("Nom:  " + (s_px+s_dx*T2-r_px));

        double T1 = (s_px+s_dx*T2-r_px)/r_dx;




        if(Double.isNaN(T1)){
           //T1 = 0;
            return null;
        }


        if(T1<0)
            return null;

        if(T2<0 || T2>1)
            return null;

        //System.out.println("T1: " + T1);

        Point intersection = new Point(r_px+r_dx*T1, r_py+r_dy*T1, T1);
        //System.out.println(intersection);

        return intersection;
    }

}
