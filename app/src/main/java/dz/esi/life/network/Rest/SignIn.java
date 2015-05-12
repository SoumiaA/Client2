package dz.esi.life.network.Rest;

import android.os.AsyncTask;
import android.util.Log;

import dz.service.delivery.DataSaver;
import dz.service.delivery.Models.Utilisateur;
import dz.service.delivery.Models.Vendeur;
import dz.service.delivery.network.RestClient;

/**
 * Created by MEFTAH on 29/04/2015.
 */
public class SignIn extends AsyncTask<String, Utilisateur, Utilisateur> {

        private Exception exception;
        Utilisateur utilisateur;
        protected Utilisateur doInBackground(String... urls) {
            try {
                utilisateur = RestClient.get().singIn(new Utilisateur(DataSaver.login,DataSaver.password));
                DataSaver.displayName = utilisateur.displayName;
                DataSaver.utilisateur = utilisateur;
                DataSaver.vendeur = new Vendeur(utilisateur);
                Log.d("Retrofit", utilisateur.displayName);
            } catch (Exception e) {
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
