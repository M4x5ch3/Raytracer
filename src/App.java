import Geometry.Sphere;
import Raytracer.*;
import Scene.Camera;
import Scene.Scene;
import Tuple.Color;
import Tuple.Point;
import Tuple.Vector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class App
{
    public static void main(String[] args)
    {
        Raytracer raytracer = new Raytracer(500, 500, 500);
        //raytracer.createImage("E:\\PixelBild.png", ImageMode.COORDINATE);
        //raytracer.createImage("E:\\RichtungBild.png", ImageMode.DIRECTION);
        //raytracer.createImage("E:\\LaengeBild.png", ImageMode.LENGTH);
        //raytracer.createImage("E:\\SchnittBild.png", ImageMode.INTERSECT);
        //raytracer.createImage("E:\\T-Wert.png", ImageMode.T_VALUE);
        //raytracer.createImage("E:\\NormalenBild.png", ImageMode.NORMAL);

        raytracer.createCameraIntersectImage("E:\\KameraSchnittBild.png", new Camera(
                new Point(0, 0, -10),
                new Point(0, 0, 0),
                7.7,
                600,
                800));
        raytracer.createCameraIntersectImage("E:\\KameraSchnittBild2.png", new Camera(
                new Point(0, 0, -10),
                new Point(1, 1, 0),
                11,
                600,
                600));
        raytracer.createCameraIntersectImage("E:\\KameraSchnittBild3.png", new Camera(
                new Point(10, 10, -10),
                new Point(0, 0, 0),
                3.3,
                600,
                600));

        Scene testScene = new Scene(
                new Camera(
                        new Point(0, 0, -10),
                        new Point(0, 0, 0),
                        90,
                        960,
                        540));

        testScene.addGeometry(new Sphere(new Point(0, -66.8, 0), 60))
                .addGeometry(new Sphere(new Point(0, 0, 0), 1))
                .addGeometry(new Sphere(new Point(3, 0, 0), 1))
                .addGeometry(new Sphere(new Point(-3, 0, 0), 1))
                .addGeometry(new Sphere(new Point(-1.5, 2.25, 0), 2))
                .addGeometry(new Sphere(new Point(1.5, 2.25, 0), 2));


        File firstOutputFile = new File("E:\\SzenenSchnittBild.png");
        BufferedImage intersectImage = new BufferedImage(
                testScene.getCamera().getWidth(),
                testScene.getCamera().getHeight(),
                BufferedImage.TYPE_INT_RGB);

        File secondOutputFile = new File("E:\\SzenenNormalenBild.png");
        BufferedImage normalImage = new BufferedImage(
                testScene.getCamera().getWidth(),
                testScene.getCamera().getHeight(),
                BufferedImage.TYPE_INT_RGB);

        for(int x = 0; x < testScene.getCamera().getWidth(); x++)
        {
            for(int y = 0; y < testScene.getCamera().getHeight(); y++)
            {
                Color intersectColor;
                Color normalColor;
                Ray ray = testScene.getCamera().generateRay(x, y);
                if(raytracer.trace(testScene, ray))
                {
                    intersectColor = new Color(0, 0, 255, 0);
                    Vector normal = ray.getHit().normal(ray.getOrigin().add(ray.getDirection().multiply(ray.getT())));
                    normalColor = new Color(Math.abs(normal.getX() * 100),
                                    Math.abs(normal.getY() * 100),
                                    Math.abs(normal.getZ() * 100),
                                    Math.abs(normal.getW()));
                }
                else
                {
                    intersectColor = new Color(255, 0, 0, 0);
                    normalColor = new Color(255, 0, 0, 0);
                }
                intersectImage.setRGB(x, testScene.getCamera().getHeight() - y - 1, intersectColor.getRGB());
                normalImage.setRGB(x, testScene.getCamera().getHeight() - y - 1, normalColor.getRGB());
            }
        }

        try
        {
            ImageIO.write(intersectImage, "png", firstOutputFile);
            ImageIO.write(normalImage, "png", secondOutputFile);
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        testScene.serializeScene("testScene");
    }
}
