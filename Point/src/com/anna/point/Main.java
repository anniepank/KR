package com.anna.point;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Point p = new Point();
        Point a = new Point();
        Point p2 = new Point();

        System.out.println("Point a is created");

        System.out.println("Coordinates of a ? ");

        try {
            a.getX();
            a.getY();

        } catch (EmptyCoordinatesException e) {
            System.out.println("No coordinates found");
        }

        a.setX(1);
        a.setY(1);

        try {
            System.out.println("Coordinates of a now are " + Double.toString(a.getX()) + " ; " + a.getY());
        } catch (EmptyCoordinatesException e) {
            System.out.println("No coordinates found");
        }

        //a.offset(-2, -1);

        try {
            System.out.println("Shift a on (-2, -1). Now a is (" + Double.toString(a.getX()) + " ; " + a.getY() + ")");
        } catch (EmptyCoordinatesException e) {
            System.out.println("No coordinates found");
        }

        a.rotate(90);

        try {
            System.out.println("Rotate on 60 degrees. Now a is (" + Double.toString(a.getX()) + " ; " + a.getY() + ")");
            //System.out.println("!!!!!!!" + Math.floor(Math.cos(Math.toRadians(60)) * 100) / 100);
        } catch (EmptyCoordinatesException e) {
            System.out.println("No coordinates found");
        }


        a.rotate(330);

        try {
            System.out.println("Rotate on 330 degrees. Now a is (" + Double.toString(a.getX()) + " ; " + a.getY() + ")");
        } catch (EmptyCoordinatesException e) {
            System.out.println("No coordinates found");
        }


        p2.setX(1);
        p2.setY(1);

        p2.rotate(45);
        try {
            BigDecimal x = BigDecimal.valueOf(p2.getX());
            x.setScale(3, BigDecimal.ROUND_CEILING);
            BigDecimal y = BigDecimal.valueOf(p2.getY());
            y.setScale(3, BigDecimal.ROUND_CEILING);

            System.out.println("Rotate on 45 degrees. Now a is (" + x.toString() + " ; " + y.toString() + ")");
        } catch (EmptyCoordinatesException e) {
            System.out.println("No coordinates found");
        }

        p.setX(0);
        p.setY(0);

        System.out.println("");


        p.rotate(330);

        try {
            System.out.println("Rotate on p(0, 0) on 30 degrees. Now a is (" + Double.toString(p.getX()) + " ; " + p.getY() + ")");
        } catch (EmptyCoordinatesException e) {
            System.out.println("No coordinates found");
        }
    }
}
