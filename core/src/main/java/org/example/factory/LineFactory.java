package org.example.factory;

import javafx.scene.canvas.GraphicsContext;
import org.example.Figure.Line;
import org.example.Serialize.Coordinates;

public class LineFactory extends FiguresFactory {
    @Override
    public Line newFigure(GraphicsContext gc, Coordinates startPoint) {
        return new Line(gc,startPoint);
    }

    @Override
    public String getName() {
        return "Отрезок";
    }
}
