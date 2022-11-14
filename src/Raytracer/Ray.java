package Raytracer;

import Geometry.Geometry;
import Tuple.*;

public class Ray
{
    //region private member
    private Point origin;
    private Vector direction;
    private double t = Float.NaN;
    private Geometry hit = null;
    //endregion

    //region getter & setter
    public Point getOrigin()
    {
        return this.origin;
    }

    public Vector getDirection()
    {
        return this.direction;
    }

    public double getT()
    {
        return this.t;
    }

    public void setT(double t)
    {
        this.t = t;
    }

    public Geometry getHit()
    {
        return this.hit;
    }

    public void setHit(Geometry hit)
    {
        this.hit = hit;
    }
    //endregion

    public Ray(Point origin, Vector direction)
    {
        this.origin = origin;
        this.direction = direction.normalized();
    }
}
