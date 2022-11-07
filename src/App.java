public class App
{
    public static void main(String[] args)
    {
        Raytracer raytracer = new Raytracer(100, 100, 100);
        raytracer.createCoordinateImage("E:\\PixelBild.png");
        raytracer = new Raytracer(250, 250, 250);
        raytracer.createDirectionImage("E:\\RichtungBild.png");
        raytracer.createLengthImage("E:\\LaengeBild.png");
    }
}
