package org.example.Serialize;

import com.google.gson.*;
import javafx.scene.paint.Color;
import org.example.FigureHistory;
import org.example.Figures;
import org.example.PluginLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Serializer {

    public static void serialize(ArrayList<Figures> figures, File file) throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Color.class, new SerializeColor())
                .create();
        FileWriter fileWriter = new FileWriter(file, false);
        JsonObject classOfFigure = new JsonObject();

        for (int i =0;i< figures.size();i++){
            classOfFigure.addProperty("class", figures.get(i).getClass().getName());
            fileWriter.write(gson.toJson(classOfFigure) + "\n");
            fileWriter.write(gson.toJson(figures.get(i)) + "\n");
        }
        fileWriter.close();

    }

    public static ArrayList<Figures> deserialize(File file) throws IOException {

        List<Figures> pluginType = new PluginLoader().getAllShapes();
        ArrayList<Figures> figureList = new ArrayList<>();
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(Color.class, new DeserializeColor());
        Gson gson = gsonBuilder.create();
        String figureType = null;
        Figures figure;
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int count = 1;
        JsonStreamParser jsonParser = new JsonStreamParser(bufferedReader);
        while (jsonParser.hasNext()) {
            JsonElement gsonElement = jsonParser.next();
            if (gsonElement.isJsonObject()) {
                if (count % 2 != 0) {
                    figureType = gsonElement.getAsJsonObject().get("class").getAsString();
                } else {
                    try{
                        figure=null;
                        for(int i=0; i<pluginType.size(); i++){
                            if(pluginType.get(i).getClass().getName().equals(figureType)) {
                                ClassLoader loader = pluginType.get(i).getClass().getClassLoader();
                                figure = (Figures) gson.fromJson(gsonElement, Class.forName(figureType, false, loader));
                                break;
                            }
                        }
                        if(figure==null){
                            figure = (Figures) gson.fromJson(gsonElement, Class.forName(figureType));}
                        figureList.add(figure);}
                    catch (Exception a){}
                }
            }
            count++;
        }
        FigureHistory.setCountOfDrawnFigures(figureList.size());

        return figureList;
    }
}
