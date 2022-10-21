package Environment;

import Tuple.Point;
import Tuple.Vector;

public class Projectile
{
    //region private member
    private final Point position;
    private final Vector velocity;
    //endregion

    //region getter and setter
    public Point getPosition()
    {
        return this.position;
    }

    public Vector getVelocity()
    {
        return this.velocity;
    }
    //endregion

    public Projectile(Point position, Vector velocity)
    {
        this.position = position;
        this.velocity = velocity;
    }

    @Override
    public String toString()
    {
        return "Projectile: \n\tPosition: " + this.position.toString();
    }
}
