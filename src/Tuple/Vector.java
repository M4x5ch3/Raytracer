package Tuple;

public class Vector extends Tuple
{
    public Vector(double x, double y, double z)
    {
        super(x, y, z, 0.0);
    }

    public Vector cross(Vector vector)
    {
        return new Vector(
                this.getY() * vector.getZ() - this.getZ() * vector.getY(),
                this.getZ() * vector.getX() - this.getX() * vector.getZ(),
                this.getX() * vector.getY() - this.getY() * vector.getX());
    }
}
