package Raytracer;

import Tuple.Color;

public record Material(
        Color color,
        double ambientReflectionCoefficient,
        double diffuseReflectionCoefficient,
        double specularReflectionCoefficient,
        double shininess,
        boolean isMetallic,
        double reflectivity)
{
    public static Material DEFAULT_NON_METALLIC = new Material(
            new Color(1, 0, 1, 0),
            0,
            1,
            0,
            0,
            false,
            0);

    public static Material DEFAULT_METALLIC = new Material(
            new Color(1, 0, 1, 0),
            0,
            1,
            0,
            0,
            true,
            0);
}
