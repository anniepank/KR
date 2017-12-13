package com.anna.point;

import static java.lang.Math.*;

public class Point {
    private double x;
    private double y;
    private boolean haveX = false;
    private boolean haveY = false;

    public Point() { }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        this.haveX = true;
        this.haveY = true;
    }

    public void setX(double x) {
        this.x = x;
        this.haveX = true;
    }

    public void setY(double y) {
        this.y = y;
        this.haveY = true;

    }

    public double getX() throws EmptyCoordinatesException {
        if (!haveX ) {
            throw new EmptyCoordinatesException();
        }
        return this.x;
    }

    public double getY() throws EmptyCoordinatesException {
        if (!haveY) {
            throw new EmptyCoordinatesException();
        }
        return this.y;
    }

    private void ensureHaveCoordinates() throws EmptyCoordinatesException {
        if (!haveX || !haveY) {
            throw new EmptyCoordinatesException();
        }
    }

    public void offset(double a, double b) throws EmptyCoordinatesException {
        ensureHaveCoordinates();
        this.x += a;
        this.y += b;
    }

    public void rotate(double angle) throws EmptyCoordinatesException {
        ensureHaveCoordinates();
        if (x == 0 && y == 0) {
            return;
        }
        double angleInRadians = toRadians(angle);
        double sin = Math.floor((Math.sin(angleInRadians)) * 100) / 100;
        double cos =  Math.floor((Math.cos(angleInRadians)) * 100) / 100;;

        double tempX, tempY;

        tempX = x * cos - y * sin;
        tempY = x * sin + y * cos;

        this.x = tempX;
        this.y = tempY;
    }

}
