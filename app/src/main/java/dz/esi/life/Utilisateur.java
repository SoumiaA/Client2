package dz.esi.life;

/**
 * Created by USER on 27/04/2015.
 */
public class Utilisateur {
    private String displayName;
    private String name;

    public Utilisateur (String dname, String nm){
        this.displayName = dname;
        this.name= nm;
    }

    public String getDisplayName(){
        return this.displayName;
    }

    public String getName(){
        return this.name;
    }

    public String utilisateurToString(){
        return this.displayName+"\nName : "+this.name;
    }
}
