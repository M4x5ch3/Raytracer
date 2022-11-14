package Raytracer;

import Geometry.*;
import Tuple.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Raytracer
{
    //region private member
    private Point camera = new Point(0, 0, -100);
    private final int coordinateSystemX;
    private final int coordinateSystemY;
    private final int coordinateSystemZ;
    private final Geometry sphere = new Sphere(new Point(0, 0, 100), 150);
    //endregion

    //region getter
    public Point getCamera()
    {
        return this.camera;
    }

    public int getCoordinateSystemX()
    {
        return this.coordinateSystemX;
    }

    public int getCoordinateSystemY()
    {
        return this.coordinateSystemY;
    }

    public int getCoordinateSystemZ()
    {
        return this.coordinateSystemZ;
    }

    public Geometry getSphere()
    {
        return this.sphere;
    }

    //endregion

    //region constructor
    public Raytracer(int coordinateSystemX, int coordinateSystemY, int coordinateSystemZ)
    {
        this.coordinateSystemX = coordinateSystemX;
        this.coordinateSystemY = coordinateSystemY;
        this.coordinateSystemZ = coordinateSystemZ;
    }

    public Raytracer(Point camera, int coordinateSystemX, int coordinateSystemY, int coordinateSystemZ)
    {
        this.camera = camera;
        this.coordinateSystemX = coordinateSystemX;
        this.coordinateSystemY = coordinateSystemY;
        this.coordinateSystemZ = coordinateSystemZ;
    }
    //endregion

    //region image creation
    public void createImage(String path, ImageMode mode)
    {
        File outputFile = new File(path);
        BufferedImage image = new BufferedImage(
                this.getCoordinateSystemX() * 2 + 1,
                this.getCoordinateSystemY() * 2 + 1,
                BufferedImage.TYPE_INT_RGB);
        for(int x = getCoordinateSystemX() * -1; x <= getCoordinateSystemX(); x++)
        {
            for(int y = getCoordinateSystemY() * -1; y <= getCoordinateSystemY(); y++)
            {
                switch(mode)
                {
                    case COORDINATE:
                        createCoordinateImage(image, x, y);
                        break;

                    case DIRECTION:
                        createDirectionImage(image, x, y);
                        break;

                    case LENGTH:
                        createLengthImage(image, x, y);
                        break;

                    case INTERSECT:
                        createIntersectImage(image, x, y);
                        break;

                    case T_VALUE:
                        createTValueImage(image, x, y);
                        break;

                    case NORMAL:
                        createNormalImage(image, x, y);
                        break;

                    default:
                        //should never be reached
                        break;
                }
            }
        }

        try
        {
            ImageIO.write(image, "png", outputFile);
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    private void createCoordinateImage(BufferedImage image, int x, int y)
    {
        Color color = new Color((x + getCoordinateSystemX() + 0.5)*255/(getCoordinateSystemX() * 2 + 1),
                (y + getCoordinateSystemY() + 0.5)*255/(getCoordinateSystemY() * 2 + 1), 0, 0);
        image.setRGB(x + getCoordinateSystemX(), y + getCoordinateSystemY(), color.getRGB());
    }

    private void createDirectionImage(BufferedImage image, int x, int y)
    {
        Color color = new Color(
                Math.abs(x + 0.5),
                Math.abs(y + 0.5),
                Math.abs(100),
                1);
        image.setRGB(x + getCoordinateSystemX(), y + getCoordinateSystemY(), color.getRGB());
    }

    private void createLengthImage(BufferedImage image, int x, int y)
    {
        Vector vector = new Vector(x + 0.5, y + 0.5, 100);
        Color color = new Color(
                vector.magnitude(),
                vector.magnitude(),
                vector.magnitude(),
                1);
        image.setRGB(x + getCoordinateSystemX(), y + getCoordinateSystemY(), color.getRGB());
    }

    private void createIntersectImage(BufferedImage image, int x, int y)
    {
        Ray ray = new Ray(this.getCamera(), new Point(x + 0.5, y + 0.5, 100)
                .subtract(this.getCamera()));
        if(this.getSphere().intersect(ray))
        {
            Color color = new Color(0, 0, 255, 0);
            image.setRGB(x + getCoordinateSystemX(), y + getCoordinateSystemY(), color.getRGB());
        }
        else
        {
            Color color = new Color(255, 0, 0, 0);
            image.setRGB(x + getCoordinateSystemX(), y + getCoordinateSystemY(), color.getRGB());
        }
    }

    private void createTValueImage(BufferedImage image, int x, int y)
    {
        Ray ray = new Ray(this.getCamera(), new Vector(x + 0.5,
                y + 0.5,
                100));

        if(sphere.intersect(ray))
        {
            Color color = new Color(255, 255, ray.getT(), 1);
            image.setRGB(x + getCoordinateSystemX(), y + getCoordinateSystemY(), color.getRGB());
        }
        else
        {
            Color color = new Color(0, 255, 0, 1);
            image.setRGB(x + getCoordinateSystemX(), y + getCoordinateSystemY(), color.getRGB());
        }
    }

    private void createNormalImage(BufferedImage image, int x, int y)
    {
        Ray ray = new Ray(this.getCamera(), new Point(x + 0.5, y + 0.5, 100)
                .subtract(this.getCamera()));
        if(sphere.intersect(ray))
        {
            Vector normal = sphere.normal(ray.getOrigin().add(ray.getDirection().multiply(ray.getT())));
            image.setRGB(x + getCoordinateSystemX(), y + coordinateSystemY,
                    new Color(Math.abs(normal.getX()), Math.abs(normal.getY()), Math.abs(normal.getZ()), normal.getW()).getRGB());
        }
        else
        {
            Color color = new Color(0, 255, 0, 1);
            image.setRGB(x + getCoordinateSystemX(), y + getCoordinateSystemY(), color.getRGB());
        }
    }
    //endregion
}
