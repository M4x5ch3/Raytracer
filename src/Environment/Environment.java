package Environment;

import Tuple.Vector;

public class Environment
{
    //region private member
    private Vector gravity;
    private Vector wind;
    //endregion

    //region getter and setter
    public Vector getGravity()
    {
        return this.gravity;
    }

    public void setGravity(Vector gravity)
    {
        this.gravity = gravity;
    }

    public Vector getWind()
    {
        return this.wind;
    }

    public void setWind(Vector wind)
    {
        this.wind = wind;
    }
    //endregion

    public Environment(Vector gravity, Vector wind)
    {
        this.gravity = gravity;
        this.wind = wind;
    }
}
