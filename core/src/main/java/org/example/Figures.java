package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.Serialize.Coordinates;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;


public abstract class Figures {

    private double penSize;
    private Color brushColor;
    private Color strokeColor;
    public boolean polyFigure;


    public Figures(GraphicsContext gc){
        strokeColor = (Color) gc.getStroke();
        brushColor = (Color) gc.getFill();
        penSize= gc.getLineWidth();
    }
    public static List<Figures> getServices(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, Figures.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
    public Figures() {}
    public abstract void paint(GraphicsContext gc);

    public boolean isPolyFigure() {
        return polyFigure;
    }

    public abstract void saveLastPoint(Coordinates newPoint);

    public void addPoint(Coordinates newPoint){};

    public void deleteLastPoint(){};

    public void figureStyle (GraphicsContext gc){
        gc.setStroke(strokeColor);
        gc.setFill(brushColor);
        gc.setLineWidth(penSize);
    }
}
