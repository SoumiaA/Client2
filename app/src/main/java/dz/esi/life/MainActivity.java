package dz.esi.life;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements Notifications.OnFragmentInteractionListener,
        Messages.OnFragmentInteractionListener, FileAct.OnFragmentInteractionListener, Amis.OnFragmentInteractionListener,
        Profil.OnFragmentInteractionListener,Recherche.OnFragmentInteractionListener,parametrees.OnFragmentInteractionListener
{

    private TextView infos;
    private ListView list;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setTitle(DataSaver.user.displayName);
            if (DataSaver.user.providerData != null) {
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setIcon(DataSaver.user.getUserImage());
            }
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff009688));
            //ab.setSubtitle("sub-title");
        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);

            return true;
        }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

            if (id == R.id.action_notif) {
                 // Create new fragment and transaction
                Fragment newFragment = new Notifications();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.frag, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
                return true;
            }

        else if  (id == R.id.action_msg){
            // Create new fragment and transaction
                Fragment newFragment = new Messages();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.frag, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
            return true;
        }
        else if  (id == R.id.action_profil){
            // Create new fragment and transaction
            //ListView list=(ListView)findViewById(R.id.listviewnotif);
            //list.setVisibility(View.INVISIBLE);
            Fragment newFragment = new Profil();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();


            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.frag, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
           /* list.setVisibility(View.INVISIBLE);
            GetUtilisateur user = new GetUtilisateur(infos,getApplicationContext());
            user.execute();
            try {
                TextView tv = (TextView) Profil.rootView.findViewById(R.id.textView2);
                tv.setText("Contenu");
            }catch(Exception e){
                e.printStackTrace();
            }*/
            return true;
        }
        else if  (id == R.id.action_amis){
            // Create new fragment and transaction
            Fragment newFragment = new Amis();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.frag, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();

            return true;
        }
        else if  (id == R.id.action_file){
            // Create new fragment and transaction
            Fragment newFragment = new FileAct();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.frag, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
            return true;
        }
        else if  (id == R.id.action_rech){
            // Create new fragment and transaction
            Fragment newFragment = new Recherche();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.frag, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
            return true;
        }
        else if  (id == R.id.action_param){
            // Create new fragment and transaction
            Fragment newFragment = new parametrees();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.frag, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
            return true;



        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    // Storage for camera image URI components
    private final static String CAPTURED_PHOTO_PATH_KEY = "mCurrentPhotoPath";
    private final static String CAPTURED_PHOTO_URI_KEY = "mCapturedImageURI";

    // Required for camera operations in order to save the image file on resume.
    private String mCurrentPhotoPath = null;
    private Uri mCapturedImageURI = null;

    public String getCurrentPhotoPath() {
        return mCurrentPhotoPath;
    }

    public void setCurrentPhotoPath(String mCurrentPhotoPath) {
        this.mCurrentPhotoPath = mCurrentPhotoPath;
    }

    public Uri getCapturedImageURI() {
        return mCapturedImageURI;
    }

    public void setCapturedImageURI(Uri mCapturedImageURI) {
        this.mCapturedImageURI = mCapturedImageURI;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        if (mCurrentPhotoPath != null) {
            savedInstanceState.putString(CAPTURED_PHOTO_PATH_KEY, mCurrentPhotoPath);
        }
        if (mCapturedImageURI != null) {
            savedInstanceState.putString(CAPTURED_PHOTO_URI_KEY, mCapturedImageURI.toString());
        }
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(CAPTURED_PHOTO_PATH_KEY)) {
            mCurrentPhotoPath = savedInstanceState.getString(CAPTURED_PHOTO_PATH_KEY);
        }
        if (savedInstanceState.containsKey(CAPTURED_PHOTO_URI_KEY)) {
            mCapturedImageURI = Uri.parse(savedInstanceState.getString(CAPTURED_PHOTO_URI_KEY));
        }
        super.onRestoreInstanceState(savedInstanceState);
    }
}
