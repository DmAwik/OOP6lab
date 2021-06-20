package org.example.Serialize;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private double x;
    private double y;

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
