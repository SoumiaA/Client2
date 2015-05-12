package dz.esi.life.network.Rest;

import android.os.AsyncTask;
import android.util.Log;

import dz.service.delivery.Models.Vendeur;
import dz.service.delivery.network.RestClient;

/**
 * Created by MEFTAH on 29/04/2015.
 */
public class AddVendeur extends AsyncTask<String, Vendeur,Vendeur> {

        private Exception exception;
        Vendeur vendeur;

        protected Vendeur doInBackground(String... urls) {
            try {
                 vendeur = RestClient.get().ajouter(new Vendeur("Mohamed","hello photo"));
                Log.d("vendeur", vendeur.name + vendeur.photo);

            } catch (Exception e) {
                this.exception = e;
                Log.e("exception", e.getMessage());
                return null;
            }
            return vendeur;
        }

        protected void onPostExecute(String chaine) {
            // TODO: check this.exception
            // TODO: do something with the feed
        }

}
