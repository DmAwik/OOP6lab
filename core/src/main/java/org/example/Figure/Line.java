package org.example.Figure;
import org.example.Figures;
import javafx.scene.canvas.GraphicsContext;
import org.example.Serialize.Coordinates;

public class Line extends Figures {
    private Coordinates startPoint,lastPoint;

    public Line(GraphicsContext gc, Coordinates startPoint){
        super(gc);
        polyFigure=false;
        this.startPoint=startPoint;
        this.lastPoint=startPoint;
        polyFigure=false;
    }

    @Override
    public void paint(GraphicsContext gc){
        figureStyle(gc);
        gc.strokeLine(startPoint.getX(),startPoint.getY(),lastPoint.getX(),lastPoint.getY());
    }

    @Override
    public void saveLastPoint(Coordinates newPoint) {
        this.lastPoint = newPoint;
    }
}