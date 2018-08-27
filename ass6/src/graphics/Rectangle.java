package graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sapir Yamin <yamin.sapir@gmail.com>
 * @version 1.2
 * @since 2010-04-28
 */

public class Rectangle {
    private double width;
    private Point upperLeft;
    private double height;

    /**
     * the function Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of te rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
    }


    /**
     * the function check intersection points.
     *
     * @param line line to check the intersection.
     * @return a (possibly empty) List of intersection points
     * with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        List listIntersectionPoints = new ArrayList<Point>();
        Point intersectionPoint = null;
        intersectionPoint = this.getLeftLine().intersectionWith(line);
        if (intersectionPoint != null) {
            listIntersectionPoints.add(intersectionPoint);
        }
        intersectionPoint = this.getRightLine().intersectionWith(line);
        if (intersectionPoint != null) {
            listIntersectionPoints.add(intersectionPoint);
        }
        intersectionPoint = this.getUpperLine().intersectionWith(line);
        if (intersectionPoint != null) {
            listIntersectionPoints.add(intersectionPoint);
        }
        intersectionPoint = this.getLowerLine().intersectionWith(line);
        if (intersectionPoint != null) {
            listIntersectionPoints.add(intersectionPoint);
        }
        return listIntersectionPoints;
    }

    /**
     * the function return the upper line of the rectangle.
     *
     * @return the upper line of the rectangle.
     */
    public Line getUpperLine() {
        Point pointEnd = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        return new Line(this.upperLeft, pointEnd);
    }

    /**
     * the function return the left line of the rectangle.
     *
     * @return the left line of the rectangle.
     */
    public Line getLeftLine() {
        Point pointEnd = new Point(this.upperLeft.getX()
                , this.upperLeft.getY() + this.height);
        return new Line(this.upperLeft, pointEnd);
    }

    /**
     * the function return the lower line of the rectangle.
     *
     * @return the lower line of the rectangle.
     */
    public Line getLowerLine() {
        Point pointEnd = new Point(this.upperLeft.getX()
                + this.width, this.upperLeft.getY() + this.height);
        Point pointStart = new Point(this.upperLeft.getX()
                , this.upperLeft.getY() + this.height);
        return new Line(pointStart, pointEnd);
    }

    /**
     * the function return the right line of the rectangle.
     *
     * @return the right line of the rectangle.
     */
    public Line getRightLine() {
        Point pointEnd = new Point(this.upperLeft.getX()
                + this.width, this.upperLeft.getY() + this.height);
        Point pointStart = new Point(this.upperLeft.getX()
                + this.width, this.getUpperLeft().getY());
        return new Line(pointStart, pointEnd);
    }

    /**
     * the function Return the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * the function Return the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * the function Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * the function update the upper-left point of the rectangle.
     *
     * @param p to change the rectangle point.
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }

    /**
     * the function Returns the upper-right point of the rectangle.
     *
     * @return the upper-right point of the rectangle.
     */
    public Point getRightUpperPoint() {
        return new Point(this.getUpperLeft().getX() + this.width,
                this.getUpperLeft().getY());
    }

    /**
     * the function Returns the lower-left point of the rectangle.
     *
     * @return the lower-left point of the rectangle.
     */
    public Point getLeftLowerPoint() {
        return new Point(this.getUpperLeft().getX(),
                this.getUpperLeft().getY() + this.height);
    }

    /**
     * the function Returns the lower-right point of the rectangle.
     *
     * @return the lower-right point of the rectangle.
     */
    public Point getRightLowerPoint() {
        return new Point(this.getUpperLeft().getX() + this.width,
                this.getUpperLeft().getY() + this.height);
    }
}
