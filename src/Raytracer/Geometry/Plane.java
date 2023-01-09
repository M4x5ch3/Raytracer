package Raytracer.Geometry;

import Raytracer.Material;
import Raytracer.Ray;
import Tuple.Point;
import Tuple.Vector;

public class Plane extends Geometry{
    private Vector normal;
    private Point position;
    private double radius;
    private Material material;

    static double DRAW_DISTANCE = 10000;

    public Plane(Vector normal, Point position)
    {
        this.normal = normal.normalized();
        this.position = position;
        this.radius = 0;
        this.material = Material.DEFAULT_NON_METALLIC;
    }

    public Plane(Vector normal, Point position, Material material)
    {
        this.normal = normal.normalized();
        this.position = position;
        this.radius = 0;
        this.material = material;
    }

    public Plane(Vector normal, Point position, double radius, Material material)
    {
        this.normal = normal.normalized();
        this.position = position;
        this.radius = radius;
        this.material = material;
    }

    public boolean intersect(Ray ray)
    {
        double t = normal.dot(position.subtract(ray.getOrigin())) / normal.dot(ray.getDirection());
        double l = position.subtract(ray.getOrigin().add(ray.getDirection().multiply(t))).magnitude();
        if(t > 0 && (radius != 0 && l < radius || radius == 0 && l < DRAW_DISTANCE))
        {
            ray.setHit(this);
            ray.setT(t);
            return true;
        }
        return false;
    }

    public Vector normal(Point hit) { return normal; }
}
