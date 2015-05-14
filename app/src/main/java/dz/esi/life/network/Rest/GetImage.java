package dz.esi.life.network.Rest;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import dz.esi.life.Model.Contenu;
import dz.esi.life.Model.PubImag;
import dz.esi.life.network.RestClient;


/**
 * Created by MEFTAH on 29/04/2015.
 */
public class GetImage extends AsyncTask<Void, Void, List<PubImag>> {

        private Exception exception;
    List<PubImag> list;

    protected List<PubImag> doInBackground(Void... urls) {
            try {
                list = RestClient.get().images();

            } catch (Exception e) {
                Log.e("exception", e.getMessage());
                return null;
            }
            return list;
        }

    protected void onPostExecute(String chaine) {
            // TODO: check this.exception
            // TODO: do something with the feed
        }

}
