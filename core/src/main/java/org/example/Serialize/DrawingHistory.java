package org.example.Serialize;

import javafx.scene.canvas.GraphicsContext;
import org.example.Figure.ParentShape;

import java.util.ArrayList;

public class DrawingHistory {

    private static ArrayList<ParentShape> shapesList = new ArrayList<>();
    private static int drawnShapesCount = 0;
    private static int maxCount = 0;

    public static ArrayList<ParentShape> getShapesList() {
        if (drawnShapesCount != maxCount) {
            ArrayList<ParentShape> newShapesList = new ArrayList<>();
            for (int i = 0; i < drawnShapesCount; i++) {
                newShapesList.add(shapesList.get(i));
            }
            return newShapesList;
        } else {
            return shapesList;
        }
    }

    public static void setShapesList(ArrayList<ParentShape> shapesList) {
        DrawingHistory.shapesList = shapesList;
    }

    public static void setDrawnShapesCount(int drawnShapesCount) {
        DrawingHistory.drawnShapesCount = drawnShapesCount;
        maxCount = drawnShapesCount;
    }

    public static void drawShapes(GraphicsContext gc) {
        for (ParentShape shape : shapesList) {
            shape.draw(gc);
        }
    }

    public static void addShape(ParentShape shape) {
        if (drawnShapesCount == shapesList.size()) {
            shapesList.add(shape);
        } else {
            shapesList.set(drawnShapesCount, shape);
        }
        drawnShapesCount++;
        maxCount = drawnShapesCount;
    }

    public static void undo(GraphicsContext gc, double width, double height) {
        if (drawnShapesCount == 0) {
            return;
        }
        drawnShapesCount--;
        gc.clearRect(0, 0, width, height);
        for (int i = 0; i < drawnShapesCount; i++) {
            shapesList.get(i).draw(gc);
        }
    }

    public static void redo(GraphicsContext gc) {
        if (drawnShapesCount >= maxCount) {
            return;
        }
        drawnShapesCount++;
        shapesList.get(drawnShapesCount - 1).draw(gc);
    }
}
