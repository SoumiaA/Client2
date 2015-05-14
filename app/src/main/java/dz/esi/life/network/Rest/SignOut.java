package dz.esi.life.network.Rest;

import android.os.AsyncTask;
import android.util.Log;

import dz.esi.life.Model.User;
import dz.esi.life.network.RestClient;


/**
 * Created by MEFTAH on 29/04/2015.
 */
public class SignOut extends AsyncTask<String, User, User> {

        private Exception exception;
    User utilisateur;

    protected User doInBackground(String... urls) {
            try {
               RestClient.get().signOut();
            } catch (Exception e) {
                this.exception = e;
                Log.e("exception", e.getMessage());
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
