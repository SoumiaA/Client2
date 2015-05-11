package com.example.user.testab;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.testab.Utilisateur;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by USER on 26/04/2015.
 */
public class GetUtilisateur extends AsyncTask<Void, Void, Utilisateur> {
    private Context c;
    private TextView infos;

    public GetUtilisateur(TextView inf, Context cont){
        this.infos=inf;
        this.c= cont;
    }
    @Override
    protected Utilisateur doInBackground(Void... params) {
        try {
            final String url = "http://frozen-peak-9792.herokuapp.com/statuses/";
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(get);
            HttpEntity httpEntity = httpResponse.getEntity();
            String response = EntityUtils.toString(httpEntity);
            JSONArray jsonArray = new JSONArray(response);
            //String displayName = jsonArray.getJSONObject(Integer.valueOf(matricule)).getString("displayName");
            String dnam =jsonArray.getJSONObject(0).getJSONObject("user").getString("displayName")+":"+jsonArray.getJSONObject(0).getString("name");
            for (int i=1;i<jsonArray.length();i++){
                dnam+="\n"+jsonArray.getJSONObject(i).getJSONObject("user").getString("displayName")+":"+jsonArray.getJSONObject(i).getString("name");
            }
            Log.d("data",dnam);
            return new Utilisateur(dnam,"");
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Utilisateur user) {
        infos=(TextView) Profil.rootView.findViewById(R.id.InfosUser);
        infos.setText(user.utilisateurToString());
    }
}
