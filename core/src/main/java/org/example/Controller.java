package org.example;

import java.io.File;
import java.io.IOException;
import java.util.*;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.example.factory.*;
import org.example.Serialize.Coordinates;
import javafx.scene.layout.VBox;

import static org.example.Serialize.Serializer.*;

public class Controller {

    @FXML
    private Canvas canvasDraw;
    @FXML
    private Canvas canvasPreview;

    @FXML
    private Button btnRedo;

    @FXML
    private Button btnUndo;

    @FXML
    private TextField PenSize;

    @FXML
    private Button btnNew;

    @FXML
    private ColorPicker strokeColor;

    @FXML
    private ColorPicker brushColor;

    @FXML
    private ComboBox<String> choiceOfFigure;

    private GraphicsContext gcDraw,gcPreview;
    private final List<FiguresFactory> figuresFactoryList = new ArrayList<>(Arrays.asList(new LineFactory(), new PolylineFactory(), new PolygonFactory(), new RectangleFactory(), new CircleFactory()) );
    private Figures currentFigure;
    private boolean isDraw = false;

    @FXML
    public void onButtonClicked(MouseEvent mouseEvent){

        btnUndo.setOnMouseClicked(event -> {
            FigureHistory.undo(gcDraw, canvasDraw.getWidth(), canvasDraw.getHeight());
        });
        btnRedo.setOnMouseClicked(event -> {

            FigureHistory.redo(gcDraw);
        });

    }

    @FXML
    public void initialize() {
        PenSize.setText("1");
        List<FiguresFactory> factories = new PluginLoader().getAllFactories();
        ObservableList<String> figureList = FXCollections.observableArrayList();

        for (FiguresFactory factory: factories) {
            figuresFactoryList.add(factory);
        }
        for (FiguresFactory factory: figuresFactoryList){
            figureList.add(factory.getName());
        }
        choiceOfFigure.getItems().setAll(figureList);
        choiceOfFigure.setValue(figureList.get(0));

        gcDraw = canvasDraw.getGraphicsContext2D();
        gcPreview = canvasPreview.getGraphicsContext2D();
        gcDraw.setFill(brushColor.getValue());
        gcDraw.setStroke(strokeColor.getValue());
        gcDraw.setLineWidth(Double.parseDouble(PenSize.getText()));
        canvasPreview.setVisible(false);
        updateCanvas();

    }

    public void brushColorChange(ActionEvent actionEvent) {
        gcDraw.setFill(brushColor.getValue());
    }

    public void strokeColorChange(ActionEvent actionEvent) {
        gcDraw.setStroke(strokeColor.getValue());
    }

    public void penSizeChange(ActionEvent actionEvent) {
        gcDraw.setLineWidth(Double.parseDouble(PenSize.getText()));
    }

    public void updateCanvas() {
        gcDraw.setFill(brushColor.getValue());
        gcDraw.setStroke(strokeColor.getValue());
        gcDraw.setLineWidth(Double.parseDouble(PenSize.getText()));
    }

    public void onCanvasClicked(MouseEvent mouseEvent) {

        if (!isDraw) {
            FiguresFactory figuresFactory = figuresFactoryList.get(choiceOfFigure.getSelectionModel().getSelectedIndex());
            currentFigure = figuresFactory.newFigure(canvasDraw.getGraphicsContext2D(), new Coordinates(mouseEvent.getX(), mouseEvent.getY()));
            isDraw = true;
            FigureHistory.addFigure(currentFigure);}
        else {
            if (currentFigure.isPolyFigure()) {
                currentFigure.addPoint(new Coordinates(mouseEvent.getX(), mouseEvent.getY()));

            } else {
                currentFigure.paint(gcDraw);
                canvasPreview.setVisible(false);
                isDraw = false;
            }
        }

    }

    public void onCanvasMouseMoved(MouseEvent mouseEvent) {
        if (isDraw) {
            canvasPreview.setVisible(true);
            gcPreview.clearRect(0, 0, canvasPreview.getWidth(), canvasPreview.getHeight());
            currentFigure.saveLastPoint(new Coordinates(mouseEvent.getX(), mouseEvent.getY()));
            currentFigure.paint(gcPreview);
        }
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE && currentFigure.isPolyFigure()) {
            currentFigure.deleteLastPoint();
            currentFigure.paint(gcDraw);
            canvasPreview.setVisible(false);
            isDraw = false;
        }
    }


    public void openFile(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
        File file = fileChooser.showOpenDialog(null);
        if (file !=null) {
            FigureHistory.setFiguresList(deserialize(file));
            gcDraw.clearRect(0, 0, canvasPreview.getWidth(), canvasPreview.getHeight());
            FigureHistory.drawFigures(gcDraw);
            updateCanvas();
        }

    }
    public void saveAsFile(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
        File file = fileChooser.showSaveDialog(null);
        if (file !=null) {
            serialize(FigureHistory.getFiguresList(), file);
        }
    }


}
