public class Point {

    private double pointX;
    private double pointY;

    public Point(double x, double y) {
        this.pointX = x;
        this.pointY = y;
    }

    public double getPointX() {
        return pointX;
    }

    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public void setPointY(double pointY) {
        this.pointY = pointY;
    }

    /**
     * cmt.
     */
    public double distance(Point other) {
        double k = (this.pointX - other.pointX) * (this.pointX - other.pointX)
                + (this.pointY - other.pointY) * (this.pointY - other.pointY);
        double c = Math.sqrt(k);
        return c;
    }
}
