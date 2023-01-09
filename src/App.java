import Raytracer.Geometry.Plane;
import Raytracer.Geometry.Sphere;
import Raytracer.LightSource.DirectionalLightSource;
import Raytracer.LightSource.PointLightSource;
import Raytracer.Material;
import Raytracer.*;
import Raytracer.Scene.Camera;
import Raytracer.Scene.Scene;
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
        //testSceneWithMultipleObjects(raytracer);
        //testSceneWithLightSource(raytracer);
        //testSceneWithMultipleObjectsAndLightsource(raytracer);
        testSceneWithMulipleObjectsAndDirectionalLightsource(raytracer);
    }

    private static void testSceneWithMultipleObjects(Raytracer raytracer)
    {
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


        File firstOutputFile = new File("./images/SzenenSchnittBild.png");
        BufferedImage intersectImage = new BufferedImage(
                testScene.getCamera().getWidth(),
                testScene.getCamera().getHeight(),
                BufferedImage.TYPE_INT_RGB);

        File secondOutputFile = new File("./images/SzenenNormalenBild.png");
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
                    Vector normal = ray.getHit().normal(ray.getOrigin().add(ray.getDirection().multiply(ray.getT())))
                            .normalized();
                    normalColor = new Color(Math.abs(normal.getX() * 255),
                            Math.abs(normal.getY() * 255),
                            Math.abs(normal.getZ() * 255),
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

    private static void testSceneWithLightSource(Raytracer raytracer)
    {
        Scene testScene = new Scene(
                new Camera(
                        new Point(0, 0, -2),
                        new Point(0, 0, 0),
                        90,
                        800,
                        800));

        testScene.addGeometry(
                new Sphere(
                        new Point(0, 0, 0),
                        1,
                        new Material(
                                new Color(1, 0.2, 1, 0),
                                0.1,
                                0.9,
                                0.9,
                                200,
                                false,
                                0.5)));

        testScene.addLightSource(
                new PointLightSource(
                        new Point(-10, 10, -1),
                        new Color(1, 1, 1),
                        1000));

        raytracer.pictureScene(testScene);
    }

    private static void testSceneWithMultipleObjectsAndLightsource(Raytracer raytracer)
    {
        Scene testScene = new Scene(
                new Camera(
                        new Point(0, 0, -10),
                        new Point(0, 0, 0),
                        90,
                        960,
                        540));

        testScene.addGeometry(new Sphere(new Point(0, -66.8, 0), 60,
                        new Material(new Color(0.6, 0.18, 0.18, 0),
                                0,
                                1,
                                0.9,
                                200,
                                false,
                                0)))
                .addGeometry(new Sphere(new Point(0, 0, 0), 1,
                        new Material(new Color(0.1, 1, 0.1, 0),
                                0,
                                1,
                                0.9,
                                200,
                                false,
                                0)))
                .addGeometry(new Sphere(new Point(3, 0, 0), 1,
                        new Material(new Color(0.6, 0.6, 0, 0),
                                0,
                                1,
                                0.9,
                                200,
                                false,
                                0)))
                .addGeometry(new Sphere(new Point(-3, 0, 0), 1,
                        new Material(new Color(0.6, 0.6, 0, 0),
                                0,
                                1,
                                0.9,
                                200,
                                false,
                                0)))
                .addGeometry(new Sphere(new Point(-1.5, 2.25, 0), 2,
                        new Material(new Color(1, 0, 1, 0),
                                0,
                                1,
                                0.9,
                                200,
                                false,
                                0.1)))
                .addGeometry(new Sphere(new Point(1.5, 2.25, 0), 2,
                        new Material(new Color(1, 0, 1, 0),
                                0,
                                1,
                                0.9,
                                200,
                                false,
                                0.1)));


        testScene.addLightSource(
                new PointLightSource(
                        new Point(10, 10, -10),
                        new Color(1, 1, 1, 0),
                        1000))
                .addLightSource(
                new PointLightSource(
                        new Point(-10, 10, -10),
                        new Color(1, 1, 1),
                        1000));

        raytracer.pictureScene(testScene);
    }

    private static void testSceneWithMulipleObjectsAndDirectionalLightsource(Raytracer raytracer)
    {
        Scene testScene = new Scene(
                new Camera(
                        new Point(0, 0, -10),
                        new Point(0, 0, 0),
                        90,
                        960,
                        540));

        testScene.addGeometry(
                new Plane(
                        new Vector(0, 1, 1),
                        new Point(0, -8, 0),
                        11,
                        new Material(
                                Color.WHITE,
                                1,
                                1,
                                1,
                                200,
                                true,
                                0
                        ))
        ).addGeometry(
                new Sphere(
                        new Point(0, 1, 0),
                        1,
                        new Material(
                                Color.GRASS_GREEN,
                                1,
                                1,
                                0.9,
                                200,
                                false,
                                0.5))
        ).addGeometry(
                new Sphere(
                        new Point(0, 2, 5),
                        2,
                        new Material(
                                new Color(0.49, 0.001, 0.52),
                                1,
                                1,
                                1,
                                300,
                                false,
                                0.1
                        )
                )
        );

        testScene.addLightSource(
                new DirectionalLightSource(new Vector(0, -10, 10), Color.WHITE, 0.1)
        );

        raytracer.pictureScene(testScene);
    }
}
