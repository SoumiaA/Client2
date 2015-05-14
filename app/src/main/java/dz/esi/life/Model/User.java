package dz.esi.life.Model;

import java.util.List;

/**
 * Created by MEFTAH on 12/05/2015.
 */
public class User {
    public String __v;
    public String firstName;
    public String lastName;
    public String displayName;
    public String email;
    public String created;
    public String username;
    public String password;
    public String type;
    public String sexe;
    public String age;
    public String note;
    public Boolean bloque;
    public List<User> suit;
    public List<User> estSuivi;
    public List<Notification> notifications;
    public List<Categorie> categories;
    public List<Affectation> affectations;
    public PubImag pubImag;

    public User(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    public User() {

    }
}
