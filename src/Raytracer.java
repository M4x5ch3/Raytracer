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

    public void createCoordinateImage(String path)
    {
        File outputFile = new File(path);
        BufferedImage image = new BufferedImage(
                this.getCoordinateSystemX() * 2 + 1,
                this.getCoordinateSystemY() * 2 + 1,
                BufferedImage.TYPE_INT_RGB);
        for(int x = 0; x < getCoordinateSystemX() * 2 + 1; x++)
        {
            for(int y = 0; y < getCoordinateSystemY() * 2 + 1; y++)
            {
                Color color = new Color((x + 0.5)*255/(getCoordinateSystemX() * 2 + 1), (y + 0.5)*255/(getCoordinateSystemY() * 2 + 1), 0, 0);
                image.setRGB(x, y, color.getRGB());
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

    public void createDirectionImage(String path)
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
                Ray ray = new Ray(this.getCamera(), new Vector(x + 0.5,
                        y + 0.5,
                        100));
                Color color = new Color(
                        Math.abs(ray.getDirection().getX()),
                        Math.abs(ray.getDirection().getY()),
                        Math.abs(ray.getDirection().getZ()),
                        1);
                image.setRGB(x + getCoordinateSystemX(), y + getCoordinateSystemY(), color.getRGB());
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

    public void createLengthImage(String path)
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
                Ray ray = new Ray(this.getCamera(), new Point(x + 0.5, y + 0.5, 0)
                        .subtract(this.getCamera()));
                Color color = new Color(
                        ray.getDirection().magnitude(),
                        ray.getDirection().magnitude(),
                        ray.getDirection().magnitude(),
                        1);
                image.setRGB(x + getCoordinateSystemX(), y + getCoordinateSystemY(), color.getRGB());
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
}
