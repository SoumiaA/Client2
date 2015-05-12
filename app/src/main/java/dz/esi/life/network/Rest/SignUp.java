package dz.esi.life.network.Rest;

import android.os.AsyncTask;
import android.util.Log;

import dz.service.delivery.Models.Utilisateur;
import dz.service.delivery.network.RestClient;

/**
 * Created by MEFTAH on 29/04/2015.
 */
public class SignUp extends AsyncTask<String, Utilisateur, Utilisateur> {

        private Exception exception;
        public Utilisateur utilisateur;
        protected Utilisateur doInBackground(String... urls) {

            try {
                utilisateur = new Utilisateur();
                utilisateur.email="guemar93@gmail.com";
                utilisateur.photo="hello photo";
                utilisateur.lastName="Lakhdar";
                utilisateur.firstName="Meftah";
                utilisateur.sexe="0";
                utilisateur.address="Bouraoui";
                utilisateur.password="Hello93";
                utilisateur.type="Utilisateur";
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

        protected void onPostExecute(Utilisateur utilisateur) {
            // TODO: check this.exception
            // TODO: do something with the feed
            this.utilisateur = utilisateur;
        }

}
