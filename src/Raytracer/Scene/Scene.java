package Raytracer.Scene;

import Raytracer.Geometry.*;
import Raytracer.LightSource.LightSource;
import Raytracer.Material;
import Raytracer.Ray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.util.HashSet;

public class Scene
{
    private Camera camera;
    private Geometry[] geometries = {};
    private LightSource[] lightSources = {};
    private HashSet<Material> materials = new HashSet<Material>();
    private final Gson GSON = new GsonBuilder().registerTypeAdapter(Geometry.class, new GeometryAdapter()).create();

    //region getter
    public Camera getCamera()
    {
        return this.camera;
    }

    public Geometry[] getGeometries()
    {
        return this.geometries;
    }

    public LightSource[] getLightSources()
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

    public Scene(Camera camera, Geometry[] geometries)
    {
        this.camera = camera;
        this.geometries = geometries;
    }

    public Scene(Camera camera, Geometry[] geometries, LightSource[] lightSources)
    {
        this.camera = camera;
        this.geometries = geometries;
        this.lightSources = lightSources;
    }

    public Scene(Camera camera, Geometry[] geometries, LightSource[] lightSources, HashSet<Material> materials)
    {
        this.camera = camera;
        this.geometries = geometries;
        this.lightSources = lightSources;
        this.materials = materials;
    }
    //endregion

    public Scene addGeometry(Geometry geometry)
    {
        Geometry[] temp = new Geometry[this.geometries.length + 1];
        for(int i = 0; i < this.geometries.length; i++)
        {
            temp[i] = this.geometries[i];
        }
        temp[this.geometries.length] = geometry;
        this.geometries = temp;
        return this;
    }

    public Scene addLightSource(LightSource lightSource)
    {
        LightSource[] temp = new LightSource[this.lightSources.length + 1];
        for(int i = 0; i < this.lightSources.length; i++)
        {
            temp[i] = this.lightSources[i];
        }
        temp[this.lightSources.length] = lightSource;
        this.lightSources = temp;
        return this;
    }

    /**
     *
     * @param ray
     * @return true if ray hits an object
     */
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
            FileWriter writer = new FileWriter("scenes/"+ name +".json");
            writer.write(GSON.toJson(this));
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
