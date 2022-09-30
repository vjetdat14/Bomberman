public class Triangle implements GeometricObject {

    private Point p1;
    private Point p2;
    private Point p3;

    /**
     * cmt.
     */
    public Triangle(Point p1, Point p2, Point p3) throws RuntimeException {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        if (p1.equals(p2) || p2.equals(p3) || p3.equals(p1)) {
            throw new RuntimeException();
        }
        if (p1.distance(p2) + p2.distance(p3) == p1.distance(p3)
                || p2.distance(p1) + p1.distance(p3) == p2.distance(p3)
                || p1.distance(p3) + p3.distance(p2) == p1.distance(p2)) {
            throw new RuntimeException();
        }
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public Point getP3() {
        return p3;
    }

    @Override
    public double getArea() {
        double x = p1.getPointX() * (p2.getPointY() - p3.getPointY());
        double y = p2.getPointX() * (p3.getPointY() - p1.getPointY());
        double z = p3.getPointX() * (p1.getPointY() - p2.getPointY());
        return (Math.abs(x + y + z)) / 2.0;
    }

    @Override
    public double getPerimeter() {
        double a = p1.distance(p2);
        double b = p1.distance(p3);
        double c = p2.distance(p3);
        return a + b + c;
    }

    @Override
    public String getInfo() {
        return String.format("Triangle[(%.2f,%.2f),(%.2f,%.2f),(%.2f,%.2f)]",
                p1.getPointX(), p1.getPointY(),
                p2.getPointX(), p2.getPointY(),
                p3.getPointX(), p3.getPointY());
    }
}
