package Geometry;

import com.google.gson.*;

import java.lang.reflect.Type;

public class GeometryAdapter implements JsonDeserializer<Geometry>, JsonSerializer<Geometry>
{

    @Override
    public JsonElement serialize(Geometry geometry, Type geometryType, JsonSerializationContext context)
    {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(geometry.getClass().getSimpleName()));
        result.add("properties", context.serialize(geometry, geometry.getClass()));

        return result;
    }

    @Override
    public Geometry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException
    {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");

        try
        {
            return context.deserialize(element, Class.forName("geometry." + type));
        }
        catch (ClassNotFoundException ex)
        {
            throw new JsonParseException("Unknown element type: " + type, ex);
        }
    }
}
