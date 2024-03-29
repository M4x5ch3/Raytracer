package Tuple;

public class Color extends Tuple
{
    //region default color values
    public static Color SKY_BLUE = new Color(0, 0.49, 0.69);

    public static Color GRASS_GREEN = new Color(0.31, 0.44, 0.22);

    public static Color BLACK = new Color(0, 0, 0);

    public static Color WHITE = new Color(1, 1, 1);

    public static Color PINK = new Color(1, 0, 1);
    //endregion

    //region getter
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
    //endregion

    public Color(double r, double g, double b)
    {
        super(r, g, b, 0);
    }

    public Color(double r, double g, double b, double a)
    {
        super(normRGB(r), normRGB(g), normRGB(b),normRGB(a));
    }

    public Color add(Color color)
    {
        return new Color(
                this.getX() + color.getX(),
                this.getY() + color.getY(),
                this.getZ() + color.getZ(),
                this.getW() + color.getW());
    }

    public Color multiply(Color color)
    {
        return new Color(
                this.getR() * color.getR(),
                this.getG() * color.getG(),
                this.getB() * color.getB(),
                this.getA() * color.getA());
    }

    public Color multiply(double scale)
    {
        return new Color(
                this.getX() * scale,
                this.getY() * scale,
                this.getZ() * scale,
                this.getW() * scale);
    }

    public Color normalized()
    {
        double magnitude = this.magnitude();
        return new Color(
                this.getX() / magnitude,
                this.getY() / magnitude,
                this.getZ() / magnitude,
                this.getW() / magnitude);
    }

    public int getRGB()
    {
        int res = 0;
        res += (int)(Math.min(1,Math.max(0, this.getR()))*255) << 16;
        res += (int)(Math.min(1,Math.max(0, this.getG()))*255) << 8;
        res += (int)(Math.min(1,Math.max(0, this.getB()))*255);
        return res;
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
