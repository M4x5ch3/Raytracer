package Tuple;

public class Color extends Tuple
{
    public double getR()
    {
        return this.getX();
    }

    public double getG()
    {
        return this.getY();
    }

    public double getB()
    {
        return this.getZ();
    }

    public double getA()
    {
        return this.getW();
    }

    public Color(int r, int g, int b, int a)
    {
        super(r, g, b, a);
    }

    public int getRGBA()
    {
        return (((int)this.getA() & 0xFF) << 24) +
                (((int)this.getR() & 0xFF) << 16) +
                (((int)this.getG() & 0xFF) << 8) +
                ((int)this.getB() & 0xFF);
    }
}
