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
    private static int recursionDepth = 5;

    public static Color calculateLightingPhong(Scene scene, Ray ray)
    {
        if(ray.getHit() == null)
        {
            return null;
        }

        Geometry hit = ray.getHit();
        Color result = Color.BLACK;

        for(LightSource lightSource : scene.getLightSources())
        {
            result.add(calculateAmbientPart(hit));
            result.add(calculateDiffusePart(hit, ray, lightSource));
            result.add(calculateSpecularPartPhong(hit, ray, scene, lightSource));
        }

        return result;
    }

    public static Color calculateLightingBlinnPhong(Scene scene, Ray ray)
    {
        if(ray.getHit() == null)
        {
            return null;
        }

        Geometry hit = ray.getHit();
        Color result = Color.BLACK;

        if (hit instanceof Plane)
        {
            int count = 2;
        }

        for(LightSource lightSource : scene.getLightSources())
        {
            if(!scene.traceRay(new Ray(ray.getHitPoint(), lightSource.directionFromPoint(ray.getHitPoint()))))
            {
                result = result.add(calculateDiffusePart(hit, ray, lightSource));
                result = result.add(calculateSpecularPartBlinnPhong(hit, ray, scene, lightSource));
            }
        }
        Color reflectedPart = calculateReflectedPart(scene, ray, hit);
        recursionDepth = 5;
        Color ambientPart = calculateAmbientPart(hit);
        return hit.getMaterial().color().add(result.add(ambientPart).add(reflectedPart));
    }

    public static Color calculatePhysicalBasedLighting()
    {
        return Color.BLACK;
    }

    private static Color calculateAmbientPart(Geometry hit)
    {
        return hit.getMaterial().color()
                .multiply(hit.getMaterial().ambientReflectionCoefficient());
    }

    private static Color calculateDiffusePart(Geometry hit, Ray ray, LightSource lightSource)
    {
        double angle = hit.normal(ray.getHitPoint())
                .dot(lightSource.directionFromPoint(ray.getHitPoint()).negate());

        if(angle >= 0)
        {
            return  lightSource.colorAtPoint(ray.getHitPoint()).multiply(angle)
                    .multiply(hit.getMaterial().diffuseReflectionCoefficient());
        }

        return Color.BLACK;
    }

    private static Color calculateSpecularPartPhong(Geometry hit, Ray ray, Scene scene, LightSource lightSource)
    {
        Color result = Color.BLACK;

        double angle = ray.getHitPoint().subtract(scene.getCamera().getPosition()).normalized()
                .dot(lightSource.getPosition().subtract(ray.getHitPoint())
                        .reflect(hit.normal(ray.getHitPoint())).normalized());

        if(angle > 0)
        {
            if(hit.getMaterial().isMetallic())
            {
                result = result.add(lightSource.colorAtPoint(ray.getHitPoint())
                        .multiply(hit.getMaterial().color())
                        .multiply(hit.getMaterial().specularReflectionCoefficient())
                        .multiply(Math.pow(angle, hit.getMaterial().shininess())));
            }
            else
            {
                result = result.add(lightSource.colorAtPoint(ray.getHitPoint())
                        .multiply(hit.getMaterial().specularReflectionCoefficient())
                        .multiply(Math.pow(angle, hit.getMaterial().shininess())));
            }
        }

        return result;
    }

    private static Color calculateSpecularPartBlinnPhong(Geometry hit, Ray ray, Scene scene, LightSource lightSource)
    {
        Color result = Color.BLACK;
        Vector h = scene.getCamera().getPosition().subtract(ray.getHitPoint()).normalized()
                .add(lightSource.directionFromPoint(ray.getHitPoint()).normalized()).normalized();

        double angle = hit.normal(ray.getHitPoint()).dot(h);

        if(hit.getMaterial().isMetallic())
        {
            result = result.add(lightSource.colorAtPoint(ray.getHitPoint())
                    .multiply(hit.getMaterial().color())
                    .multiply(hit.getMaterial().specularReflectionCoefficient())
                    .multiply(Math.pow(angle, 4 * hit.getMaterial().shininess())));
        }
        else
        {
            result = result.add(lightSource.colorAtPoint(ray.getHitPoint())
                    .multiply(hit.getMaterial().specularReflectionCoefficient())
                    .multiply(Math.pow(angle, 4 * hit.getMaterial().shininess())));
        }

        return result;
    }

    private static Color calculateReflectedPart(Scene scene, Ray ray, Geometry hit)
    {
        Ray newRay = new Ray(ray.getHitPoint(), ray.getDirection().reflect(hit.normal(ray.getHitPoint())));
        if(scene.traceRay(newRay))
        {
            --recursionDepth;
            if(recursionDepth == 0)
            {
                return Color.BLACK;
            }
            return calculateLightingBlinnPhong(scene, ray).multiply(hit.getMaterial().reflectivity());
        }
        return Color.BLACK;
    }
}
