package org.example.factory;

import javafx.scene.canvas.GraphicsContext;
import org.example.Figure.Rectangle;
import org.example.Serialize.Coordinates;

public class RectangleFactory extends FiguresFactory {
    public RectangleFactory() {
    }
    @Override
    public Rectangle newFigure(GraphicsContext gc, Coordinates startPoint){
        return new Rectangle(gc,0,0,startPoint);
    }

    @Override
    public String getName() {
        return "Прямоугольник";
    }

}

