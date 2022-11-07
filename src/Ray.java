import Tuple.*;

public class Ray
{
    private Point origin;
    private Vector direction;

    public Point getOrigin()
    {
        return this.origin;
    }

    public Vector getDirection()
    {
        return this.direction;
    }

    public Ray(Point origin, Vector direction)
    {
        this.origin = origin;
        this.direction = direction;
    }
}
