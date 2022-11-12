public class App
{
    public static void main(String[] args)
    {
        Raytracer raytracer = new Raytracer(1000, 1000, 1000);
        raytracer.createCoordinateImage("E:\\PixelBild.png");
        raytracer.createDirectionImage("E:\\RichtungBild.png");
        raytracer.createLengthImage("E:\\LaengeBild.png");
    }
}
