package dz.esi.life.Model;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

import dz.esi.life.DataSaver;
import dz.esi.life.network.RestClient;

/**
 * Created by MEFTAH on 12/05/2015.
 */
public class User {
    public String __v;
    public String _id;
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
    public ProviderData providerData;

    public User(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    public User() {

    }

    public static User signIn(final String username, final String password) {
        AsyncTask task = new AsyncTask() {
            User user;

            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    user = new User(username, password);
                    user = RestClient.get().singIn(user);
                } catch (Exception e) {
                    Log.e("exception", e.getMessage());
                    return null;
                }
                return user;
            }
        };
        try {
            User user = (User) task.execute().get();
            DataSaver.user = user;
            Log.d("User", user.displayName);
            return user;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User signInWithGoogle(final String code) {
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    RestClient.get().signInWithGoogle(code);
                    User user = RestClient.get().me();
                    DataSaver.user = user;
                    Log.d("User", user.displayName + " est Connecte");
                    return user;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        try {
            User user = (User) task.execute().get();
            Log.d("OAuth", "finish");
            return user;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Drawable getUserImage() {
        final Drawable drawable;
        try {
            return (Drawable) new AsyncTask() {
                @Override
                protected Object doInBackground(Object[] params) {
                    InputStream is = null;
                    try {
                        is = (InputStream) new URL(providerData.picture).getContent();
                        Drawable drawable = Drawable.createFromStream(is, username + "img");
                        return drawable;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;

    }
}
