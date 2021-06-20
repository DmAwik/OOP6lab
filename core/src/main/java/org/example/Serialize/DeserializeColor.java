package org.example.Serialize;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import javafx.scene.paint.Color;

import java.lang.reflect.Type;

public class DeserializeColor implements JsonDeserializer<Color> {
    @Override
    public Color deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        double r = jsonElement.getAsJsonObject().get("Red").getAsDouble();
        double g = jsonElement.getAsJsonObject().get("Green").getAsDouble();
        double b = jsonElement.getAsJsonObject().get("Blue").getAsDouble();
        double o = jsonElement.getAsJsonObject().get("Opacity").getAsDouble();
        return new Color(r, g, b, o);
    }
}
