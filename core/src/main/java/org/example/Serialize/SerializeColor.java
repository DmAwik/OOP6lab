package org.example.Serialize;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import javafx.scene.paint.Color;
import java.lang.reflect.Type;

public class SerializeColor implements JsonSerializer<Color> {
    @Override
    public JsonElement serialize(Color color, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();

        result.addProperty("Red", color.getRed());
        result.addProperty("Green", color.getGreen());
        result.addProperty("Blue", color.getBlue());
        result.addProperty("Opacity", color.getOpacity());
        return result;
    }

}
