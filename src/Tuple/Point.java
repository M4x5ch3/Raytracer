package Tuple;

public class Point extends Tuple
{
    public Point(double x, double y, double z)
    {
        super(x, y, z, 1.0);
    }

    public Point add(Vector vector)
    {
        return new Point(
                this.getX() + vector.getX(),
                this.getY() + vector.getY(),
                this.getZ() + vector.getZ());
    }

    public Vector subtract(Point point)
    {
        return new Vector(
                this.getX() - point.getX(),
                this.getY() - point.getY(),
                this.getZ() - point.getZ());
    }
}
