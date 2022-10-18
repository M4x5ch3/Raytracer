package Tuple;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TupleTest
{
    @Test
    public void tupleIsAPoint()
    {
        Tuple tuple = new Tuple(4.3, -4.2, 3.1, 1.0);
        assertEquals(tuple, (Tuple) new Point(4.3, -4.2, 3.1));
        assertNotEquals(tuple, (Tuple) new Vector(4.3, -4.2, 3.1));
    }

    @Test
    public void tupleIsAVector()
    {
        Tuple tuple = new Tuple(4.3, -4.2, 3.1, 0.0);
        assertEquals(tuple, (Tuple) new Vector(4.3, -4.2, 3.1));
        assertNotEquals(tuple, (Tuple) new Point(4.3, -4.2, 3.1));
    }

    @Test
    public void wValueIsSetCorrectly()
    {
        //point should set value 'w' to 1.0
        Point point = new Point(4, -4, 3);
        assertEquals(4.0, point.getX());
        assertEquals(-4.0, point.getY());
        assertEquals(3.0, point.getZ());
        assertEquals(1.0, point.getW());

        //vector should set value 'w' to 0.0
        Vector vector = new Vector(4, -4, 3);
        assertEquals(4.0, vector.getX());
        assertEquals(-4.0, vector.getY());
        assertEquals(3.0, vector.getZ());
        assertEquals(0.0, vector.getW());
    }

    @Test
    public void addingTuples()
    {
        Tuple firstTuple = new Tuple(3, -2, 5, 1);
        Tuple secondTuple = new Tuple(-2, 3, 1, 0);
        Tuple resultTuple = firstTuple.add(secondTuple);
        assertEquals(1.0, resultTuple.getX());
        assertEquals(1.0, resultTuple.getY());
        assertEquals(6.0, resultTuple.getZ());
        assertEquals(1.0, resultTuple.getW());
    }

    @Test
    public void subtractingTwoPoints()
    {
        Point firstPoint = new Point(3, 2, 1);
        Point secondPoint = new Point(5, 6, 7);
        Tuple resultPoint = firstPoint.subtract(secondPoint);
        assertEquals(resultPoint, (Tuple) new Vector(-2, -4, -6));
    }

    @Test
    public void subtractingAVectorFromAPoint()
    {
        Point point = new Point(3, 2, 1);
        Vector vector = new Vector(5, 6, 7);
        Tuple resultTuple = point.subtract(vector);
        assertEquals(resultTuple, (Tuple) new Point(-2, -4, -6));
    }

    @Test
    public void subtractingTwoVectors()
    {
        Vector firstVector = new Vector(3, 2, 1);
        Vector secondVector = new Vector(5, 6, 7);
        Tuple resultVector = firstVector.subtract(secondVector);
        assertEquals(resultVector, (Tuple) new Vector(-2, -4, -6));
    }

    @Test
    public  void subtractingAVectorFromTheZeroVector()
    {
        Vector zeroVector = new Vector(0, 0, 0);
        Vector vector = new Vector(1, -2, 3);
        assertEquals(zeroVector.subtract(vector), (Tuple) new Vector(-1, 2, -3));
    }

    @Test
    public void negatingATuple()
    {
        Tuple tuple = new Tuple(1, -2, 3, -4);
        assertEquals(tuple.negate(), new Tuple(-1, 2, -3, 4));
    }

    @Test
    public void multiplyingATupleByAScalar()
    {
        Tuple tuple = new Tuple(1, -2, 3, -4);
        assertEquals(tuple.multiply(3.5), new Tuple(3.5, -7, 10.5, -14));
    }

    @Test
    public void multiplyingATupleByAFraction()
    {
        Tuple tuple = new Tuple(1, -2, 3, -4);
        assertEquals(tuple.multiply(0.5), new Tuple(0.5, -1, 1.5, -2));
    }

    @Test
    public void dividingATupleByAScalar()
    {
        Tuple tuple = new Tuple(1, -2, 3, -4);
        assertEquals(tuple.divide(2), new Tuple(0.5, -1, 1.5, -2));
    }

    @Test
    public void computingTheMagnitudeOfVector()
    {
        Vector vector = new Vector(1, 0, 0);
        assertEquals(vector.magnitude(), 1);
        vector = new Vector(0, 1, 0);
        assertEquals(vector.magnitude(), 1);
        vector = new Vector(0, 0, 1);
        assertEquals(vector.magnitude(), 1);
        vector = new Vector(1, 2, 3);
        assertEquals(vector.magnitude(), Math.sqrt(14));
        vector = new Vector(-1, -2, -3);
        assertEquals(vector.magnitude(), Math.sqrt(14));
    }

    @Test
    public void normalizingVector()
    {
        Vector vector = new Vector(4, 0, 0);
        assertEquals(vector.normalized(), new Vector(1, 0, 0));
        vector = new Vector(1, 2, 3);
        assertEquals(vector.normalized(), new Vector(1/Math.sqrt(14), 2/Math.sqrt(14), 3/Math.sqrt(14)));
    }

    @Test
    public void magnitudeOfANormalizedVector()
    {
        Vector vector = new Vector(1, 2, 3);
        assertEquals(vector.normalized().magnitude(), 1);
    }

    @Test
    public void theDotProductOfTwoTuples()
    {
        Vector firstVector = new Vector(1, 2, 3);
        Vector secondVector = new Vector(2, 3, 4);
        assertEquals(firstVector.dot(secondVector), 20);
    }

    @Test
    public void crossProductOfTwoVectors()
    {
        Vector firstVector = new Vector(1, 2, 3);
        Vector secondVector = new Vector(2, 3, 4);
        assertEquals(firstVector.cross(secondVector), new Vector(-1, 2, -1));
        assertEquals(secondVector.cross(firstVector), new Vector(1, -2, 1));
    }
}