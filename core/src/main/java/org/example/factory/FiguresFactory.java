package org.example.factory;

import javafx.scene.canvas.GraphicsContext;
import org.example.Figures;
import org.example.Serialize.Coordinates;
import org.example.Figure.ParentShape;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public abstract class FiguresFactory {

    public abstract Figures newFigure(GraphicsContext graphicsContext, Coordinates firstPoint);

    public static List<FiguresFactory> getServices(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, FiguresFactory.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
    public abstract String  getName();
}
