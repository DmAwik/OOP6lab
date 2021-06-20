package org.example.factory;

import javafx.scene.canvas.GraphicsContext;
import org.example.Figure.Polygon;
import org.example.Serialize.Coordinates;

public class PolygonFactory extends FiguresFactory {

    public PolygonFactory() {
    }
    @Override
    public org.example.Figure.Polygon newFigure(GraphicsContext gc, Coordinates startPoint){
        return new Polygon(gc,startPoint,startPoint);
    }
    @Override
    public String getName() {
        return "Многоугольник";
    }
}
