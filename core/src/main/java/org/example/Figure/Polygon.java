package org.example.Figure;

import javafx.scene.canvas.GraphicsContext;
import org.example.Figures;
import org.example.Serialize.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polygon extends Figures {

    private List<Coordinates> listOfPoints;

    public Polygon(GraphicsContext gc, Coordinates... listOfPoints){
        super(gc);
        polyFigure = true;
        this.listOfPoints = new ArrayList<>();
        this.listOfPoints.addAll(Arrays.asList(listOfPoints));

    }

    @Override
    public void paint(GraphicsContext gc){
        figureStyle(gc);

        double[] pointsX = new double[listOfPoints.size()];
        double[] pointsY = new double[listOfPoints.size()];

        for (int count = 0; count < listOfPoints.size();count++){
            Coordinates tmp = listOfPoints.get(count);
            pointsX[count] = tmp.getX();
            pointsY[count] = tmp.getY();
        }
        gc.fillPolygon(pointsX, pointsY, listOfPoints.size());
        gc.strokePolygon(pointsX, pointsY, listOfPoints.size());

    }
    @Override
    public void addPoint(Coordinates newPoint){
        listOfPoints.add(newPoint);
    }

    @Override
    public void saveLastPoint(Coordinates newPoint){
        listOfPoints.set(listOfPoints.size() - 1,newPoint);
    }

    @Override
    public void deleteLastPoint(){
        listOfPoints.remove(listOfPoints.size()-1);
    }
}