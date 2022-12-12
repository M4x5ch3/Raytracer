package Raytracer.Geometry;

import Raytracer.Ray;
import Tuple.Point;
import Tuple.Vector;

public class Plane extends Geometry
{
    @Override
    public boolean intersect(Ray ray)
    {
        return false;
    }

    @Override
    public Vector normal(Point hit)
    {
        return null;
    }
}
