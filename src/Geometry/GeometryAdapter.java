package Geometry;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class GeometryAdapter extends TypeAdapter<Geometry>
{
    @Override
    public void write(JsonWriter jsonWriter, Geometry geometry) throws IOException
    {

    }

    @Override
    public Geometry read(JsonReader jsonReader) throws IOException
    {
        return null;
    }
}
