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

    public Color(double r, double g, double b, double a)
    {
        super(normRGB(r), normRGB(g), normRGB(b),normRGB(a));
    }

    public int getRGB()
    {
        return (((int)this.getR() & 0xFF) << 16) +
                (((int)this.getG() & 0xFF) << 8) +
                ((int)this.getB() & 0xFF);
    }

    public int getRGBA()
    {
        return (((int)this.getA() & 0xFF) << 24) +
                (((int)this.getR() & 0xFF) << 16) +
                (((int)this.getG() & 0xFF) << 8) +
                ((int)this.getB() & 0xFF);
    }

    private static double normRGB(double x){
        return Math.min(255, Math.max(0, x));
    }
}
