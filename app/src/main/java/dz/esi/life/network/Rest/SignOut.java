package dz.esi.life.network.Rest;

import android.os.AsyncTask;
import android.util.Log;

import dz.service.delivery.Models.Utilisateur;
import dz.service.delivery.network.RestClient;

/**
 * Created by MEFTAH on 29/04/2015.
 */
public class SignOut extends AsyncTask<String, Utilisateur, Utilisateur> {

        private Exception exception;
        Utilisateur utilisateur;
        protected Utilisateur doInBackground(String... urls) {
            try {
               RestClient.get().signOut();
            } catch (Exception e) {
                this.exception = e;
                Log.e("exception", e.getMessage());
                return null;
            }
            return utilisateur;
        }

        protected void onPostExecute(Utilisateur utilisateur) {
            // TODO: check this.exception
            // TODO: do something with the feed
            utilisateur = this.utilisateur;
        }

}
