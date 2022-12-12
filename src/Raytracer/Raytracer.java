package Raytracer;

import Raytracer.Geometry.*;
import Raytracer.LightSource.Lighting;
import Raytracer.Scene.Scene;
import Tuple.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;

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

    public void pictureScene(Scene scene)
    {
        pictureScene(scene, "");
    }

    public void pictureScene(Scene scene, String fileName)
    {
        File outputFile = new File("./images/" + new Date(System.currentTimeMillis()) + fileName + ".png");
        BufferedImage image = new BufferedImage(
                scene.getCamera().getWidth(),
                scene.getCamera().getHeight(),
                BufferedImage.TYPE_INT_RGB);

        for(int x = 0; x < scene.getCamera().getWidth(); x++)
        {
            for(int y = 0; y < scene.getCamera().getHeight(); y++)
            {
                Color color = this.superSample(scene, x, y);
                if(!color.equals(Color.BLACK))
                {
                    image.setRGB(x, scene.getCamera().getHeight() - y - 1, color.getRGB());
                }
                else
                {
                    image.setRGB(x, scene.getCamera().getHeight() - y - 1, Color.BLACK.getRGB());
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

    private Color superSample(Scene scene, int pixelX, int pixelY)
    {
        Color color = Color.BLACK;
        for(double x = -0.5; x < 0.5; x += 0.5)
        {
            for(double y = -0.5; y < 0.5; y += 0.5)
            {
                Ray ray = scene.getCamera().generateRay(pixelX + x, pixelY + y);
                this.trace(scene, ray);
                Color lighting = this.calculateLighting(scene, ray);

                if(lighting != null)
                {
                    color = color.add(new Color(lighting.getR() / 9, lighting.getG() / 9, lighting.getB() / 9));
                }
                else
                {
                    color = color.add(new Color(
                            Color.SKY_BLUE.getR() / 9,
                            Color.SKY_BLUE.getG() / 9,
                            Color.SKY_BLUE.getB() / 9));
                }
            }
        }

        return new Color(color.getR(), color.getG(), color.getB());
    }

    public boolean trace(Scene scene, Ray ray)
    {
        return scene.traceRay(ray);
    }

    public Color calculateLighting(Scene scene, Ray ray)
    {
        return Lighting.calculateLightingBlinnPhong(scene, ray);
    }
}
