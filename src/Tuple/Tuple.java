package Tuple;

public class Tuple
{
    //region private member
    private static final double DELTA = 0.000001;
    private final double x;
    private final double y;
    private final double z;
    private final double w;
    //endregion

    //region getter
    public double getX()
    {
        return this.x;
    }

    public double getY()
    {
        return this.y;
    }

    public double getZ()
    {
        return this.z;
    }

    public double getW()
    {
        return this.w;
    }
    //endregion

    public Tuple(double x, double y, double z, double w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Tuple add(Tuple tuple)
    {
        return new Tuple(
                this.getX() + tuple.getX(),
                this.getY() + tuple.getY(),
                this.getZ() + tuple.getZ(),
                this.getW() + tuple.getW());
    }

    public Tuple subtract(Tuple tuple)
    {
        return new Tuple(
                this.getX() - tuple.getX(),
                this.getY() - tuple.getY(),
                this.getZ() - tuple.getZ(),
                this.getW() - tuple.getW());
    }

    public Tuple multiply(double scale)
    {
        return new Tuple(
                this.getX() * scale,
                this.getY() * scale,
                this.getZ() * scale,
                this.getW() * scale);
    }

    public Tuple divide(double scale)
    {
        return new Tuple(
                this.getX() / scale,
                this.getY() / scale,
                this.getZ() / scale,
                this.getW() / scale);
    }

    public double dot(Tuple tuple)
    {
        return (this.getX() * tuple.getX() +
                this.getY() * tuple.getY() +
                this.getZ() * tuple.getZ() +
                this.getW() * tuple.getW());
    }
    public Tuple negate()
    {
        return new Tuple(
                this.getX() * -1,
                this.getY() * -1,
                this.getZ() * -1,
                this.getW() * -1);
    }

    public Tuple normalized()
    {
        double magnitude = this.magnitude();
        return new Tuple(
                this.getX() / magnitude,
                this.getY() / magnitude,
                this.getZ() / magnitude,
                this.getW() / magnitude);
    }

    @Override
    public boolean equals(Object tuple)
    {
        if(!(tuple instanceof Tuple) || tuple == null)
        {
            return false;
        }

        return Math.abs(this.getX() - ((Tuple)tuple).getX()) <= DELTA
                && Math.abs(this.getY() - ((Tuple)tuple).getY()) <= DELTA
                && Math.abs(this.getZ() - ((Tuple)tuple).getZ()) <= DELTA
                && Math.abs(this.getW() - ((Tuple)tuple).getW()) <= DELTA;
    }

    @Override
    public String toString()
    {
        return "(X: " + this.getX() + ", Y: " + this.getY() + ", Z: " + this.getZ() + ", W: " + this.getW() + ")";
    }

    public double magnitude()
    {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
    }
}
