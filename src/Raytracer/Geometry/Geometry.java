package Raytracer.Geometry;

import Raytracer.Material;
import Raytracer.Ray;
import Tuple.*;

public abstract class Geometry
{
    public Material getMaterial()
    {
        return Material.DEFAULT_METALLIC;
    }
    public abstract boolean intersect(Ray ray);
    public abstract Vector normal(Point hit);
}
