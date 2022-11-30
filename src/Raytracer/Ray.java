package Raytracer;

import Raytracer.Geometry.Geometry;
import Tuple.*;

public class Ray
{
    //region private member
    private Point origin;
    private Vector direction;
    private double t = Double.NaN;
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
    public Point getHitPoint()
    {
        return this.origin.add(this.direction.multiply(this.t));
    }
    //endregion

    public Ray(Point origin, Vector direction)
    {
        this.origin = origin;
        this.direction = direction.normalized();
    }

    public Ray(Ray ray)
    {
        this.origin = ray.getOrigin();
        this.direction = ray.getDirection();
        this.t = ray.getT();
        this.hit = ray.getHit();
    }
}
