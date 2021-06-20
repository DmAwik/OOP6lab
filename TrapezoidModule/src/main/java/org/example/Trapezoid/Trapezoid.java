package org.example.Trapezoid;

import org.example.Figures;
import org.example.Serialize.Coordinates;
import javafx.scene.canvas.GraphicsContext;

public class Trapezoid extends org.example.Figures {

    private Coordinates topLeftCorner;
    private double width;
    private double height;

    public Trapezoid(GraphicsContext gc, Coordinates topLeftCorner, double width, double height) {
        super(gc);
        this.topLeftCorner = topLeftCorner;
        this.width = width;
        this.height = height;
        polyFigure = false;
    }

    public Trapezoid() {
    }

    @Override
    public void paint(GraphicsContext gc) {
        figureStyle(gc);
        double x = topLeftCorner.getX();
        double y = topLeftCorner.getY();
        double[] xPoints = {x, x + width * 0.25, x + width * 0.75, x + width};
        double[] yPoints = {y, y + height, y + height, y};
        gc.fillPolygon(xPoints, yPoints, 4);
        gc.strokePolygon(xPoints, yPoints, 4);
    }

    @Override
    public void saveLastPoint(Coordinates newPoint) {
        width = newPoint.getX() - topLeftCorner.getX();
        height = newPoint.getY() - topLeftCorner.getY();
    }

}
