package Raytracer.LightSource;

import Tuple.Color;
import Tuple.Point;

public class LightSource
{
    private Point position;
    private Color color;
    private double intensity;

    public Point getPosition()
    {
        return this.position;
    }

    public Color getColor()
    {
        return this.color;
    }

    public double getIntensity()
    {
        return this.intensity;
    }

    public LightSource(Point position, Color color, double intensity)
    {
        this.position = position;
        this.color = color;
        this.intensity = intensity;
    }
}
