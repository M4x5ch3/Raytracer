package Material;

import Tuple.Color;

public class Material
{
    private Color color;
    private double ambientReflectionCoefficient;
    private double diffuseReflectionCoefficient;
    private double specularReflectionCoefficient;
    private double shininess;

    public Color getColor()
    {
        return this.color;
    }

    public double getAmbientReflectionCoefficient()
    {
        return this.ambientReflectionCoefficient;
    }

    public double getDiffuseReflectionCoefficient()
    {
        return this.diffuseReflectionCoefficient;
    }

    public double getSpecularReflectionCoefficient()
    {
        return this.specularReflectionCoefficient;
    }

    public double getShininess()
    {
        return this.shininess;
    }

    public Material(Color color, double ambientReflectionCoefficient, double diffuseReflectionCoefficient, double specularReflectionCoefficient, double shininess)
    {
        this.color = color;
        this.ambientReflectionCoefficient = ambientReflectionCoefficient;
        this.diffuseReflectionCoefficient = diffuseReflectionCoefficient;
        this.specularReflectionCoefficient = specularReflectionCoefficient;
        this.shininess = shininess;
    }
}
