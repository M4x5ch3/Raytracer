package Tuple;

public class Point extends Tuple
{
    public Point(double x, double y, double z)
    {
        super(x, y, z, 1.0);
    }

    @Override
    public Point add(Tuple tuple)
    {
        return new Point(
                this.getX() + tuple.getX(),
                this.getY() + tuple.getY(),
                this.getZ() + tuple.getZ());
    }
}
