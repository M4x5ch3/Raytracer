package LightSource;

import Geometry.*;
import Raytracer.Ray;
import Scene.Scene;
import Tuple.Color;

/**
 * Lighting class should only hold and provide methods to calculate the lighting in a scene.
 */
public class Lighting
{
    public static Color calculateLighting(Scene scene, Ray ray)
    {
        if(ray.getHit() == null)
        {
            return null;
        }

        Sphere hit = (Sphere) ray.getHit();
        Color ambientColorPart = calculateAmbientPart(hit);
        Color diffuseColorPart = calculateDiffusePart(hit, ray, scene);
        Color specularColorPart = calculateSpecularPart(hit, ray, scene);

        return ambientColorPart.add(diffuseColorPart).add(specularColorPart);
    }

    private static Color calculateAmbientPart(Sphere hit)
    {
        return hit.getMaterial().color()
                .multiply(hit.getMaterial().ambientReflectionCoefficient());
    }

    private static Color calculateDiffusePart(Sphere hit, Ray ray, Scene scene)
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
                        .multiply(lightSource.getColor().multiply(lightSource.getIntensity()).multiply(angle)));
            }
        }

        return result;
    }

    private static Color calculateSpecularPart(Sphere hit, Ray ray, Scene scene)
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
                    result = result.add(lightSource.getColor().multiply(hit.getMaterial().color())
                            .multiply(hit.getMaterial().specularReflectionCoefficient())
                            .multiply(Math.pow(angle, hit.getMaterial().shininess())));
                }
                else
                {
                    result = result.add(lightSource.getColor()
                            .multiply(hit.getMaterial().specularReflectionCoefficient())
                            .multiply(Math.pow(angle, hit.getMaterial().shininess())));
                }
            }
        }

        return result;
    }
}
