package Tuple;

public class Vector extends Tuple
    {
    public Vector(double x, double y, double z)
        {
            super(x, y, z, 0.0);
        }

        public Vector cross(Vector vector)
        {
            return new Vector(
                    this.getY() * vector.getZ() - this.getZ() * vector.getY(),
                    this.getZ() * vector.getX() - this.getX() * vector.getZ(),
                    this.getX() * vector.getY() - this.getY() * vector.getX());
        }

        public Vector add(Vector vector)
        {
            return new Vector(
                    this.getX() + vector.getX(),
                    this.getY() + vector.getY(),
                    this.getZ() + vector.getZ());
        }

        public Vector subtract(Vector vector)
        {
            return new Vector(
                    this.getX() - vector.getX(),
                    this.getY() - vector.getY(),
                    this.getZ() - vector.getZ());
        }

        public Vector multiply(double scale)
        {
            return new Vector(
                    this.getX() * scale,
                    this.getY() * scale,
                    this.getZ() * scale);
        }

        @Override
        public Vector normalized()
        {
            double magnitude = this.magnitude();
            return new Vector(
                    this.getX() / magnitude,
                    this.getY() / magnitude,
                    this.getZ() / magnitude);
        }

        public Vector reflect(Vector normal)
        {
            return this.subtract(normal.multiply(2 * this.dot(normal)));
        }
}
