package dz.esi.life.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

import dz.esi.life.R;
import dz.esi.life.network.RestClient;

/**
 * Created by MEFTAH on 12/05/2015.
 */
public class PubImag {
    String name;
    String comment;
    User user;
    String _id;

    public Bitmap getImage() {
        String info = this.name.substring(22);
        Log.d("base64", info);
        byte[] decodedString = Base64.decode(info, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return bitmap;
    }

    public String setImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        return this.name = "data:image/png;base64," + Base64.encodeToString(b, Base64.DEFAULT);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PubImag publie() {
        AsyncTask<Void, Void, PubImag> task = new AsyncTask<Void, Void, PubImag>() {
            PubImag pubImag;

            protected PubImag doInBackground(Void... urls) {
                try {
                    pubImag = RestClient.get().PubImag_publier(PubImag.this);

                } catch (Exception e) {
                    Log.e("exception", e.getMessage());
                    return null;
                }
                return pubImag;
            }
        };
        try {
            return task.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return this;
    }

    public PubImag recuperer() {
        List<PubImag> list;
        AsyncTask<Void, Void, List<PubImag>> task = new AsyncTask<Void, Void, List<PubImag>>() {

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
        };
        try {
            list = task.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return this;
    }
}
