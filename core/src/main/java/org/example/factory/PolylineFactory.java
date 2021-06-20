package org.example.factory;

import javafx.scene.canvas.GraphicsContext;
import org.example.Figure.Polyline;
import org.example.Serialize.Coordinates;

public class PolylineFactory extends FiguresFactory {
    public PolylineFactory() {
    }
    @Override
    public Polyline newFigure(GraphicsContext gc, Coordinates startPoint){
        return  new Polyline(gc,startPoint,startPoint);
    }
    @Override
    public String getName() {
        return "Ломаная";
    }
}
