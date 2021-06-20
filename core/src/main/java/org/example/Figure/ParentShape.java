package org.example.Figure;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.Serialize.Coordinates;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public abstract class ParentShape {

    protected Color strokeColour;
    protected Color fillColour;
    protected double lineWidth;

    public static List<ParentShape> getServices(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, ParentShape.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }

    protected boolean multipoint;

    public ParentShape() {}

    public ParentShape(GraphicsContext graphicsContext) {
        strokeColour = (Color) graphicsContext.getStroke();
        fillColour = (Color) graphicsContext.getFill();
        lineWidth = graphicsContext.getLineWidth();
    }

    public abstract void draw(GraphicsContext graphicsContext);



    protected void updateGraphics(GraphicsContext graphicsContext) {
        graphicsContext.setStroke(strokeColour);
        graphicsContext.setFill(fillColour);
        graphicsContext.setLineWidth(lineWidth);
    }

}
