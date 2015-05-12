package dz.esi.life.Model;

import java.util.List;

/**
 * Created by MEFTAH on 12/05/2015.
 */
public class Emploi {
    public String __v;
    public String typeC;
    public String texte;
    public List<User> jaimes;
    public List<Emploi> commentaires;
    public PubImag pubImag;
    public PubVideo pubVideo;
    public Offre offre;
    public Evenement evenement;
    public Cours cours;
    public Emploi emploi;
    public List<String> tags;
    public String created;
    public User user;
    public Moderation moderation;
    public Emploi pere;
    public List<Categorie> categories;
    public List<Affectation> affectations;
}
