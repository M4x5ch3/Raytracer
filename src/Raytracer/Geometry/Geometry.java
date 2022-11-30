package Raytracer.Geometry;

import Raytracer.Ray;
import Tuple.*;

public abstract class Geometry
{
    public abstract boolean intersect(Ray ray);
    public abstract Vector normal(Point hit);
}
