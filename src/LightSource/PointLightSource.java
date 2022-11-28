package LightSource;

import Tuple.Color;
import Tuple.Point;

public class PointLightSource extends LightSource
{
    public PointLightSource(Point position, Color color, double intensity)
    {
        super(position, color, intensity);
    }
}
