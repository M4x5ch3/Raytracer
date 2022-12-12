package Raytracer.LightSource;

import Tuple.Color;
import Tuple.Point;

public class Spotlight extends LightSource
{
    public Spotlight(Point position, Color color, double intensity)
    {
        super(position, color, intensity);
    }
}
