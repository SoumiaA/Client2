package dz.esi.life.network.Rest;

import android.os.AsyncTask;
import android.util.Log;

import dz.esi.life.Model.User;
import dz.esi.life.Utilisateur;
import dz.esi.life.network.RestClient;


/**
 * Created by MEFTAH on 29/04/2015.
 */
public class SignIn extends AsyncTask<String, User, User> {

        private Exception exception;
    User utilisateur;

    protected User doInBackground(String... urls) {
            try {
                User user = new User("root", "meftah93");
                utilisateur = RestClient.get().singIn(user);
            } catch (Exception e) {
                return null;
            }
            return utilisateur;
        }

    protected void onPostExecute(User utilisateur) {
            // TODO: check this.exception
            // TODO: do something with the feed
            utilisateur = this.utilisateur;
        }

}
