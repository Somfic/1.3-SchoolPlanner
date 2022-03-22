package Saving;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.scene.paint.Color;

import java.io.IOException;

public class ColorDeserializer extends JsonDeserializer<Color> {

    @Override
    public Color deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        double red = node.get("red").doubleValue();
        double green = node.get("green").doubleValue();
        double blue = node.get("blue").doubleValue();
        double opacity = node.get("opacity").doubleValue();

        return new Color(red, green, blue, opacity);
    }
}