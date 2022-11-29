package Environment;

import Tuple.Point;
import Tuple.Vector;

public class Main
{
    public static void main(String[] args)
    {
        Projectile projectile = new Projectile(new Point(0, 1, 0),
                new Vector(1, 1, 0).normalized());
        Environment environment = new Environment(new Vector(0, -0.1, 0),
                new Vector(-0.01, 0, 0));

        while(projectile.getPosition().getY() > 0)
        {
            System.out.println(projectile);
            projectile = tick(environment, projectile);
        }

        System.out.println(projectile);
    }

    private static Projectile tick(Environment environment, Projectile projectile)
    {
        Point position = projectile.getPosition().add(projectile.getVelocity());
        Vector velocity = projectile.getVelocity().add(environment.getGravity()).add(environment.getWind());
        return new Projectile(position, velocity);
    }
}
