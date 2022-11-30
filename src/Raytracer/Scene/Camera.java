package Raytracer.Scene;

import Raytracer.Ray;
import Tuple.Point;
import Tuple.Vector;

public class Camera
{
    //region private member
    private Point position;
    private Point lookAt;
    private double fieldOfView;
    private int width;
    private int height;
    private double pixelSize;
    private Vector viewPlaneNormal;
    private Vector right;
    private Vector up;
    //endregion

    //region getter
    public Point getPosition()
    {
        return this.position;
    }

    public Point getLookAt()
    {
        return this.lookAt;
    }

    public double getFieldOfView()
    {
        return this.fieldOfView;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public double getPixelSize()
    {
        return this.pixelSize;
    }

    public Vector getViewPlaneNormal()
    {
        return this.viewPlaneNormal;
    }

    public Vector getRight()
    {
        return this.right;
    }

    public Vector getUp()
    {
        return this.up;
    }

    public double getAspectRatio()
    {
        return width/height;
    }
    //endregion

    public Camera(Point position, Point lookAt, double fieldOfViewInDegree, int width, int height)
    {
        this.position = position;
        this.lookAt = lookAt;
        this.fieldOfView = Math.toRadians(fieldOfViewInDegree);
        this.width = width;
        this.height =  height;

        double halfHeight = Math.tan(this.fieldOfView/2);
        this.pixelSize = getAspectRatio() < 1 ?
                2 * halfHeight / this.height : 2 * (halfHeight / getAspectRatio()) / this.height;
        this.viewPlaneNormal = lookAt.subtract(position).normalized();

        this.right = new Vector(0, 1, 0).cross(this.viewPlaneNormal).normalized();
        this.up = viewPlaneNormal.cross(right).normalized();
    }

    /**
     * Creates ray which is created through the pixels center.
     * @param pixelX Coordinate X
     * @param pixelY Coordinate Y
     * @return generated ray
     */
    public Ray generateRay(double pixelX, double pixelY)
    {
        double xOffset = (pixelX + 0.5 - (this.width / 2)) * this.pixelSize;
        double yOffset = (pixelY + 0.5 - (this.height / 2)) * this.pixelSize;
        return new Ray(this.position,
                this.right.multiply(xOffset).add(this.up.multiply(yOffset)).add(this.viewPlaneNormal));
    }
}
