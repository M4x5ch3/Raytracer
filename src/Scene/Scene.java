package Scene;

import Geometry.*;
import Raytracer.Ray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.util.HashSet;

public class Scene
{
    private Camera camera;
    private HashSet<Geometry> geometries = new HashSet<Geometry>();
    private HashSet<LightSources> lightSources = new HashSet<LightSources>();
    private HashSet<Material> materials = new HashSet<Material>();
    private final Gson GSON = new GsonBuilder().registerTypeAdapter(Geometry.class, new GeometryAdapter()).create();

    //region getter
    public Camera getCamera()
    {
        return this.camera;
    }

    public HashSet<Geometry> getGeometries()
    {
        return this.geometries;
    }

    public HashSet<LightSources> getLightSources()
    {
        return this.lightSources;
    }

    public HashSet<Material> getMaterials()
    {
        return this.materials;
    }
    //endregion

    //region constructor
    public Scene(Camera camera)
    {
        this.camera = camera;
    }

    public Scene(Camera camera, HashSet<Geometry> geometries)
    {
        this.camera = camera;
        this.geometries = geometries;
    }

    public Scene(Camera camera, HashSet<Geometry> geometries, HashSet<LightSources> lightSources)
    {
        this.camera = camera;
        this.geometries = geometries;
        this.lightSources = lightSources;
    }

    public Scene(Camera camera, HashSet<Geometry> geometries, HashSet<LightSources> lightSources, HashSet<Material> materials)
    {
        this.camera = camera;
        this.geometries = geometries;
        this.lightSources = lightSources;
        this.materials = materials;
    }
    //endregion

    public Scene addGeometry(Geometry geometry)
    {
        this.geometries.add(geometry);
        return this;
    }

    public boolean traceRay(Ray ray)
    {
        for(Geometry geometry : this.geometries)
        {
            Ray copy = new Ray(ray);
            if(geometry.intersect(copy))
            {
                if(Double.isNaN(ray.getT()) || ray.getT() > copy.getT())
                {
                    ray.setT(copy.getT());
                    ray.setHit(copy.getHit());
                }
            }
        }

        if(Double.isNaN(ray.getT()))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void serializeScene(String name){
        try {
            FileWriter writer = new FileWriter("scenes/"+name+".json");
            writer.write(GSON.toJson(this));
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
