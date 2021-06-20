package org.example.Figure;
import javafx.scene.canvas.GraphicsContext;
import org.example.Figures;
import org.example.Serialize.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polyline extends Figures {

    private List<Coordinates> listOfPoints;

    public Polyline(GraphicsContext gc, Coordinates... listOfPoints){
        super(gc);
        polyFigure = true;
        this.listOfPoints =new ArrayList<>();
        this.listOfPoints.addAll(Arrays.asList(listOfPoints));
    }
    @Override
    public void paint(GraphicsContext gc){
        figureStyle(gc);
        for (int count = 0; count < listOfPoints.size() - 1; count++) {
            Coordinates startPoint = listOfPoints.get(count);
            Coordinates secondPoint = listOfPoints.get(count + 1);
            gc.strokeLine(startPoint.getX(), startPoint.getY(), secondPoint.getX(), secondPoint.getY());
        }
    }
    @Override
    public void saveLastPoint(Coordinates newPoint){
        listOfPoints.set(listOfPoints.size()-1,newPoint);
    }
    @Override
    public void addPoint(Coordinates newPoint){
        listOfPoints.add(newPoint);
    }
    @Override
    public void deleteLastPoint(){
        listOfPoints.remove(listOfPoints.size()-1);
    }
}