package Geometry;

import Material.Material;
import Raytracer.Ray;
import Tuple.*;

public class Sphere extends Geometry
{
    private final Point center;
    private final double radius;
    private Material material;

    public Point getCenter()
    {
        return this.center;
    }

    public double getRadius()
    {
        return this.radius;
    }

    public Material getMaterial()
    {
        return this.material;
    }

    public Sphere(Point centre, double radius)
    {
        this.center = centre;
        this.radius = radius;
        this.material = new Material(
                new Color(1, 0.2, 1, 0),
                0,
                1,
                0,
                0);
    }

    public Sphere(Point center, double radius, Material material)
    {
        this.center = center;
        this.radius = radius;
        this.material = material;
    }

    public boolean intersect(Ray ray)
    {
        Vector centerToOrigin = center.subtract(ray.getOrigin());
        double tc = centerToOrigin.dot(ray.getDirection());

        //tc will be smaller 0, if ray does not intersect with the sphere
        if(tc < 0.0)
        {
            return false;
        }

        double d = Math.sqrt(Math.abs((tc * tc) - (centerToOrigin.magnitude() * centerToOrigin.magnitude())));

        //point would not be in our sphere
        if(d > radius)
        {
            return false;
        }

        double t1c = Math.sqrt((radius * radius) - (d * d));
        ray.setT(tc - t1c);
        ray.setHit(this);
        return true;
    }

    public Vector normal(Point hit)
    {
        return center.subtract(hit).normalized();
    }
}
