package org.example;

import javafx.scene.canvas.GraphicsContext;
import org.example.Figures;
import java.util.ArrayList;


public class FigureHistory {

    private static ArrayList<Figures> figuresList = new ArrayList<>();
    private static int FigureCount = 0;
    private static int maxFigureCount = 0;

    public static ArrayList<Figures> getFiguresList() {
        if (FigureCount != maxFigureCount) {
            ArrayList<Figures> getFiguresList = new ArrayList<>();
            for (int i = 0; i < FigureCount; i++) {
                getFiguresList.add(figuresList.get(i));
            }
            return getFiguresList;
        } else {
            return figuresList;
        }
    }

    public static void setFiguresList(ArrayList<Figures> figureList) {
        FigureHistory.figuresList = figureList;
    }

    public static void setCountOfDrawnFigures(int countOfDrawnFigures) {
        FigureHistory.FigureCount = countOfDrawnFigures;
        maxFigureCount = countOfDrawnFigures;
    }

    public static void drawFigures(GraphicsContext gc) {
        for (int i =0;i< figuresList.size();i++) {
            figuresList.get(i).paint(gc);
        }
    }

    public static void addFigure(Figures figure) {
        if (FigureCount == figuresList.size()) {
            figuresList.add(figure);
        } else {
            figuresList.set(FigureCount, figure);
        }
        FigureCount++;
        maxFigureCount = FigureCount;
    }

    public static void undo(GraphicsContext gc, double width, double height) {
        if (FigureCount == 0) {
            return;
        }
        FigureCount--;
        gc.clearRect(0, 0, width, height);
        for (int i = 0; i < FigureCount; i++) {
            figuresList.get(i).paint(gc);
        }
    }

    public static void redo(GraphicsContext gc) {
        if (FigureCount >= maxFigureCount) {
            return;
        }
        FigureCount++;
        figuresList.get(FigureCount - 1).paint(gc);
    }
}
