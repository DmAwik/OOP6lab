package org.example.Trapezoid;

import org.example.Figures;
import org.example.factory.FiguresFactory;

import org.example.Serialize.Coordinates;

import javafx.scene.canvas.GraphicsContext;

public class TrapezoidFactory extends FiguresFactory {

    @Override
    public Figures newFigure(GraphicsContext graphicsContext, Coordinates startPoint) {
        return new Trapezoid(graphicsContext, startPoint, 0, 0);
    }

    @Override
    public String getName() {
        return "Трапеция";
    }

}
