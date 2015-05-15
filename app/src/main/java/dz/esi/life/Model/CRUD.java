package dz.esi.life.Model;

import java.util.List;

/**
 * Created by MEFTAH on 15/05/2015.
 */
public abstract class CRUD {
    public String _id;
    public String created;

    public abstract CRUD post();

    public abstract CRUD put();

    public abstract CRUD delete();

}
