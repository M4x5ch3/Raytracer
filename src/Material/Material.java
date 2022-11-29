package Material;

import Tuple.Color;

public record Material(
        Color color,
        double ambientReflectionCoefficient,
        double diffuseReflectionCoefficient,
        double specularReflectionCoefficient,
        double shininess,
        boolean isMetallic)
{
    public static Material DEFAULT_NON_METALLIC = new Material(
            new Color(1, 0, 1, 0),
            0,
            1,
            0,
            0,
            false);

    public static Material DEFAULT_METALLIC = new Material(
            new Color(1, 0, 1, 0),
            0,
            1,
            0,
            0,
            true);
}
