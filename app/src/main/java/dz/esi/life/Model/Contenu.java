package dz.esi.life.Model;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutionException;

import dz.esi.life.network.RestClient;

/**
 * Created by MEFTAH on 12/05/2015.
 */
public class Contenu extends CRUD {
    public String __v;
    public String[] typeC;
    public String texte;
    public String name;
    public List<User> jaimes;
    public List<Contenu> commentaires;
    public PubImag pubImag;
    public PubVideo pubVideo;
    public Offre offre;
    public Evenement evenement;
    public Cours cours;
    public Emploi emploi;
    public List<String> tags;
    public User user;
    public Moderation moderation;
    public Contenu pere;
    public List<Categorie> categories;
    public List<Affectation> affectations;

    public static List<Contenu> get() {
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    return RestClient.get().contenu();
                } catch (Exception e) {
                    Log.e("exception", e.getMessage());
                    return null;
                }
            }
        };
        try {
            return (List<Contenu>) task.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CRUD post() {
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    return RestClient.get().contenu(Contenu.this);
                } catch (Exception e) {
                    Log.e("exception", e.getMessage());
                    return null;
                }
            }
        };
        try {
            return (CRUD) task.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public CRUD put() {
        return null;
    }

    @Override
    public CRUD delete() {
        return null;
    }
}
