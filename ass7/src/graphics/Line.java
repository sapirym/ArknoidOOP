package graphics;

import java.util.List;

/**
 * A Line class.
 * 316251818
 *
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 2.0
 * @since 2017-04-04
 */
public class Line {
    private Point start;
    private Point end;
    private double slope;
    private double n;

    /**
     * constructor for line.
     *
     * @param end   end point.
     * @param start start point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        if (this.start.getX() != this.end.getX()) {
            this.slope = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
            this.n = this.start.getY() - (this.slope * this.start.getX());
        }
    }


    /**
     * constructor for line.
     *
     * @param x1 the x of the start point of line.
     * @param x2 the x of the end point of line.
     * @param y1 the y of the start point of line.
     * @param y2 the y of the end point of line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        if (this.start.getX() != this.end.getX()) {
            this.slope = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
            this.n = this.start.getY() - (this.slope * this.start.getX());
        }
    }

    /**
     * This method returns the start point of the line.
     *
     * @return start values of the line's start point
     **/
    public Point getStart() {
        return this.start;
    }

    /**
     * This method returns the end point of the line.
     *
     * @return start values of the line's end point
     **/
    public Point getEnd() {
        return this.end;
    }

    /**
     * check if lines intersect, first check the slope, then
     * call intersectionWith.
     *
     * @param other graphics.Line to check intersection.
     * @return true- if intersect, false- if not.
     */
    public boolean isIntersecting(Line other) {
        Point intersection = intersectionWith(other);
        return intersection != null;
    }

    /**
     * check if the value of the intersect point and call inline.
     *
     * @param other graphics.Line to find intersection point.
     * @return intersection point if intersect and the line is
     * on the lines, null otherwise.
     */
    public Point intersectionWith(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (d == 0) {
            return null;
        }
        double xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
        double yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;
        Point p1 = new Point(xi, yi);
        if (this.inline(p1) && other.inline(p1)) {
            return p1;
        } else {
            return null;
        }

    }

    /**
     * the function check the closest intersection to start of the line.
     *
     * @param rect rectangle object.
     * @return graphics.Point of the intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line thisLine = new Line(start, end);
        double distance = Double.MAX_VALUE;
        List<Point> intersectionPoints = rect.intersectionPoints(thisLine);
        Point minPoint = null;
        if (intersectionPoints.isEmpty()) {
            return null;
        } else {
            for (int i = 0; i < (intersectionPoints.size()); i++) {
                if (intersectionPoints.get(i) != null) {
                    if (this.start.distance(intersectionPoints.get(i)) < distance) {
                        minPoint = intersectionPoints.get(i);
                        distance = this.start.distance(intersectionPoints.get(i));
                    }
                }
            }
            return minPoint;
        }
    }
    /**
     * check if the intersect point is on the line.
     *
     * @param intersect intersection point.
     * @return intersection point if the line is
     * on the lines, null otherwise.
     */
    public boolean inline(Point intersect) {
        if (intersect.getX() >= Math.min(this.start.getX(), this.end.getX())
                && intersect.getX() <= Math.max(this.start.getX(), this.end.getX())
                && intersect.getY() >= Math.min(this.start.getY(), this.end.getY())
                && intersect.getY() <= Math.max(this.start.getY(), this.end.getY())) {
            return true;
        }
        return false;
    }
}
