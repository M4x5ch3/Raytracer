package Raytracer.LightSource;

import Tuple.Color;
import Tuple.Point;
import Tuple.Vector;

public class DirectionalLightSource extends LightSource
{
    public DirectionalLightSource(Vector direction, Color color, double intensity)
    {
        super(color, intensity);
        this.direction = direction;
    }
}
