package dz.esi.life.network.Rest;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import dz.service.delivery.Models.Vendeur;
import dz.service.delivery.network.RestClient;

/**
 * Created by MEFTAH on 29/04/2015.
 */
public class ListVendeur extends AsyncTask<String, List<Vendeur>, List<Vendeur>> {

        private Exception exception;
        public List<Vendeur> list;
        protected List<Vendeur> doInBackground(String... urls) {

            try {
                list = RestClient.get().vendeurs();
            } catch (Exception e) {
                this.exception = e;
                Log.e("exception", e.getMessage());
                return null;
            }
            return list;
        }

        protected void onPostExecute(List<Vendeur> vendeurs) {
            // TODO: check this.exception
            // TODO: do something with the feed
            vendeurs = this.list;
        }

}
