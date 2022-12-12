package Raytracer.LightSource;

import Raytracer.Geometry.*;
import Raytracer.Ray;
import Raytracer.Scene.Scene;
import Tuple.Color;
import Tuple.Vector;

/**
 * Lighting class should only hold and provide methods to calculate the lighting in a scene.
 */
public class Lighting
{
    public static Color calculateLightingPhong(Scene scene, Ray ray)
    {
        if(ray.getHit() == null)
        {
            return null;
        }

        Sphere hit = (Sphere) ray.getHit();
        Color ambientColorPart = calculateAmbientPart(hit);
        Color diffuseColorPart = calculateDiffusePart(hit, ray, scene);
        Color specularColorPart = calculateSpecularPartPhong(hit, ray, scene);

        return ambientColorPart.add(diffuseColorPart).add(specularColorPart);
    }

    public static Color calculateLightingBlinnPhong(Scene scene, Ray ray)
    {
        if(ray.getHit() == null)
        {
            return null;
        }

        Geometry hit = ray.getHit();
        Color ambientColorPart = calculateAmbientPart(hit);
        Color diffuseColorPart = calculateDiffusePart(hit, ray, scene);
        Color specularColorPart = calculateSpecularPartBlinnPhong(hit, ray, scene);

        return ambientColorPart.add(diffuseColorPart).add(specularColorPart);
    }

    private static Color calculateAmbientPart(Geometry hit)
    {
        return hit.getMaterial().color()
                .multiply(hit.getMaterial().ambientReflectionCoefficient());
    }

    private static Color calculateDiffusePart(Geometry hit, Ray ray, Scene scene)
    {
        Color result = new Color(0, 0, 0);
        for(LightSource lightSource : scene.getLightSources())
        {
            double angle = hit.normal(ray.getHitPoint())
                    .dot(ray.getHitPoint().subtract(lightSource.getPosition()).normalized());

            if(angle > 0)
            {
                result = result.add(hit.getMaterial().color()
                        .multiply(hit.getMaterial().diffuseReflectionCoefficient())
                        .multiply(lightSource.colorAtPoint(ray.getHitPoint())).multiply(angle));
            }
        }

        return result;
    }

    private static Color calculateSpecularPartPhong(Geometry hit, Ray ray, Scene scene)
    {
        Color result = new Color(0, 0, 0);
        for(LightSource lightSource : scene.getLightSources())
        {
            double angle = ray.getHitPoint().subtract(scene.getCamera().getPosition()).normalized()
                            .dot(lightSource.getPosition().subtract(ray.getHitPoint())
                                    .reflect(hit.normal(ray.getHitPoint())).normalized());

            if(angle > 0)
            {
                if(hit.getMaterial().isMetallic())
                {
                    result = result.add(lightSource.getColor()
                            .multiply(lightSource.getIntensity())
                            .multiply(hit.getMaterial().color())
                            .multiply(hit.getMaterial().specularReflectionCoefficient())
                            .multiply(Math.pow(angle, hit.getMaterial().shininess())));
                }
                else
                {
                    result = result.add(lightSource.getColor()
                            .multiply(lightSource.getIntensity())
                            .multiply(hit.getMaterial().specularReflectionCoefficient())
                            .multiply(Math.pow(angle, hit.getMaterial().shininess())));
                }
            }
        }

        return result;
    }

    private static Color calculateSpecularPartBlinnPhong(Geometry hit, Ray ray, Scene scene)
    {
        Color result = new Color(0, 0, 0);
        for(LightSource lightSource : scene.getLightSources())
        {
            Vector h = scene.getCamera().getPosition().subtract(ray.getHitPoint()).normalized()
                    .add(lightSource.getPosition().subtract(ray.getHitPoint()).normalized()).normalized();

            double angle = hit.normal(ray.getHitPoint()).dot(h);

            if(hit.getMaterial().isMetallic())
            {
                result = result.add(lightSource.getColor()
                        .multiply(lightSource.getIntensity())
                        .multiply(hit.getMaterial().color())
                        .multiply(hit.getMaterial().specularReflectionCoefficient())
                        .multiply(Math.pow(angle, 4 * hit.getMaterial().shininess())));
            }
            else
            {
                result = result.add(lightSource.getColor()
                        .multiply(lightSource.getIntensity())
                        .multiply(hit.getMaterial().specularReflectionCoefficient())
                        .multiply(Math.pow(angle, 4 * hit.getMaterial().shininess())));
            }
        }

        return result;
    }
}
