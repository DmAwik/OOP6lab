package org.example.Figure;

import javafx.scene.canvas.GraphicsContext;
import org.example.Figures;
import org.example.Serialize.Coordinates;

public class Circle extends Figures {

    private double width;
    private double height;
    private Coordinates topLeftCorner;

    public  Circle(GraphicsContext gc, double height, double width, Coordinates topLeftCorner){
        super(gc);
        polyFigure=false;
        this.height=height;
        this.width=width;
        this.topLeftCorner = topLeftCorner;
        polyFigure =false;
    }

    @Override
    public void paint(GraphicsContext gc){
        figureStyle(gc);

        gc.strokeOval(width > 0 ? topLeftCorner.getX() : topLeftCorner.getX() + width,
                height > 0 ? topLeftCorner.getY() : topLeftCorner.getY() + height,
                Math.abs(width), Math.abs(height));

        gc.fillOval(width > 0 ? topLeftCorner.getX() : topLeftCorner.getX() + width,
                height > 0 ? topLeftCorner.getY() : topLeftCorner.getY() + height,
                Math.abs(width), Math.abs(height));

    }
    @Override
    public void saveLastPoint(Coordinates newPoint){
        width = newPoint.getX() - topLeftCorner.getX();
        height =newPoint.getY()-topLeftCorner.getY();

    }
}