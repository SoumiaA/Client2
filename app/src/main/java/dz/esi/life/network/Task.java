package dz.esi.life.network;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import dz.service.delivery.Models.Utilisateur;
import dz.service.delivery.Models.Vendeur;

/**
 * Created by MEFTAH on 29/04/2015.
 */
public class Task extends AsyncTask<String, Void, String> {

        private Exception exception;
        List<Vendeur> list;
        protected String doInBackground(String... urls) {

            try {
                //Drawable image = MainActivity.main.getResources().getDrawable(R.drawable.ic_action);
               // RestClient.get().ajouter("hello","5540021e51df375032167951",image);

                /*list = RestClient.get().vendeurs();
                Iterator<Vendeur> it = list.iterator();
                while(it.hasNext()){
                    it.next().sayIt();
                }*/
                /*Utilisateur utilisateur = new Utilisateur();
                utilisateur.email="guemar93@gmail.com";
                utilisateur.photo="hello photo";
                utilisateur.lastName="Lakhdar";
                utilisateur.firstName="Meftah";
                utilisateur.sexe="0";
                utilisateur.address="Bouraoui";
                utilisateur.password="Hello93";
                utilisateur.type="Utilisateur";
                utilisateur.username = "Meftah";
                utilisateur = RestClient.get().singUp(utilisateur);
                Log.d("Retrofit",utilisateur.displayName);*/
                /*Vendeur vendeur = RestClient.get().ajouter(new Vendeur("Yow 1 Million de centime","55425e87f08f730e007c37e0","and a photo"));
                Log.d("vendeur",vendeur.name+vendeur.photo);*/

                Utilisateur utilisateur = RestClient.get().singIn(new Utilisateur("Mefktah","Hello93"));
                Log.d("Retrofit", utilisateur.displayName);

            } catch (Exception e) {
                this.exception = e;
                Log.e("exception", e.getMessage());
                return null;
            }
            return null;
        }

        protected void onPostExecute(String chaine) {
            // TODO: check this.exception
            // TODO: do something with the feed
        }

}
