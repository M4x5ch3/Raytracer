package Tuple;

public class Color
{
    private final double r;
    private final double g;
    private final double b;

    public double getR()
    {
        return r;
    }

    public double getG()
    {
        return g;
    }

    public double getB()
    {
        return b;
    }

    public Color(double r, double g, double b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color subtract(Color color)
    {
        return new Color(
                this.getR() - color.getR(),
                this.getG() - color.getG(),
                this.getB() - color.getB());
    }

    public Color multiply(Color color)
    {
        return new Color(
                this.getR() * color.getR(),
                this.getG() * color.getG(),
                this.getB() * color.getB());
    }
}
