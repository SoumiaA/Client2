package dz.esi.life.network.Rest;

import android.os.AsyncTask;
import android.util.Log;

import dz.esi.life.Model.Contenu;
import dz.esi.life.network.RestClient;


/**
 * Created by MEFTAH on 29/04/2015.
 */
public class AddVendeur extends AsyncTask<String, Contenu, Contenu> {

        private Exception exception;
    Contenu contenu;

    protected Contenu doInBackground(String... urls) {
            try {
                // contenu = RestClient.get().ajouter(new Contenu("Mohamed","hello photo"));

            } catch (Exception e) {
                this.exception = e;
                Log.e("exception", e.getMessage());
                return null;
            }
        return contenu;
        }

        protected void onPostExecute(String chaine) {
            // TODO: check this.exception
            // TODO: do something with the feed
        }

}
