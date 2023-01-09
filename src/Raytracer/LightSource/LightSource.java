package Raytracer.LightSource;

import Tuple.Color;
import Tuple.Point;
import Tuple.Vector;

public abstract class LightSource
{
    private Point position;
    private Color color;
    private double intensity;
    protected Vector direction = null;

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

    public boolean isDirectional() { return this.direction != null; }

    public LightSource(Color color, double intensity)
    {
        this.color = color;
        this.intensity = intensity;
    }

    public LightSource(Point position, Color color, double intensity)
    {
        this.position = position;
        this.color = color;
        this.intensity = intensity;
    }

    public LightSource(Vector direction, Color color, double intensity)
    {
        this.direction = direction.normalized();
        this.color = color;
        this.intensity = intensity;
    }

    public Color colorAtPoint(Point point)
    {
        if(isDirectional())
        {
            return this.color.multiply(this.intensity);
        }

        return this.color.multiply(this.intensity)
                .multiply(1 / (this.distanceFromPoint(point) * this.distanceFromPoint(point)));
    }

    public double distanceFromPoint(Point point)
    {
        if(this.isDirectional())
        {
            return Double.NaN;
        }

        return this.position.subtract(point).magnitude();
    }

    public Vector directionFromPoint(Point point)
    {
        if(this.isDirectional())
        {
            return this.direction.negate();
        }

        return this.position.subtract(point).normalized();
    }
}
