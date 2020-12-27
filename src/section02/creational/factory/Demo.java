package section02.creational.factory;

/**
 * Factory: A component responsibility solely for the wholesale (not piecewise) creation of objects.
 */
public class Demo {
    public static void main(String[] args) {
        Point point = Point.Factory.newCartesionPoint(2,3);
    }
}



class Point {
    private double x, y;

    // use private constructor
    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    static class Factory {
        public static Point newCartesionPoint(double x, double y) {
            return new Point(x, y);
        }

        public static Point newPolarPoint(double rho, double theta) {
            return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
        }
    }
/*
    public Point(double rho, double theta) {
        x = rho * Math.cos(theta);
        y = rho * Math.sin(theta);
    }
     */


}
