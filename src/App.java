import Raytracer.*;

public class App
{
    public static void main(String[] args)
    {
        Raytracer raytracer = new Raytracer(500, 500, 500);
        raytracer.createImage("E:\\PixelBild.png", ImageMode.COORDINATE);
        raytracer.createImage("E:\\RichtungBild.png", ImageMode.DIRECTION);
        raytracer.createImage("E:\\LaengeBild.png", ImageMode.LENGTH);
        raytracer.createImage("E:\\SchnittBild.png", ImageMode.INTERSECT);
        raytracer.createImage("E:\\T-Wert.png", ImageMode.T_VALUE);
        raytracer.createImage("E:\\NormalenBild.png", ImageMode.NORMAL);
    }
}
