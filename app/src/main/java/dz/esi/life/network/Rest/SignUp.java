package dz.esi.life.network.Rest;

import android.os.AsyncTask;
import android.util.Log;

import dz.esi.life.Model.User;
import dz.esi.life.network.RestClient;


/**
 * Created by MEFTAH on 29/04/2015.
 */
public class SignUp extends AsyncTask<String, User, User> {

        private Exception exception;
    public User utilisateur;

    protected User doInBackground(String... urls) {

            try {
                utilisateur = new User();
                utilisateur.email="guemar93@gmail.com";
                utilisateur.lastName="Lakhdar";
                utilisateur.firstName="Meftah";
                utilisateur.sexe="0";
                utilisateur.password="Hello93";
                utilisateur.type = "User";
                utilisateur.username = "Meftah93";
                utilisateur = RestClient.get().singUp(utilisateur);
                Log.d("Retrofit", utilisateur.displayName);

            } catch (Exception e) {
                this.exception = e;
                Log.e("exception", e.getMessage());
                return utilisateur;
            }
            return utilisateur;
        }

    protected void onPostExecute(User utilisateur) {
            // TODO: check this.exception
            // TODO: do something with the feed
            this.utilisateur = utilisateur;
        }

}
