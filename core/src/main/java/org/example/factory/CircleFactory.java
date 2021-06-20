package org.example.factory;

import javafx.scene.canvas.GraphicsContext;
import org.example.Figure.Circle;
import org.example.Serialize.Coordinates;

public class CircleFactory extends FiguresFactory {

    public CircleFactory() {
    }
    @Override
    public Circle newFigure(GraphicsContext gc, Coordinates startPoint){

        return new Circle(gc,0,0,startPoint);
    }
    @Override
    public String getName() {
        return "Эллипс";
    }
}
