package dz.esi.life;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity2 extends ActionBarActivity implements Notifications.OnFragmentInteractionListener,
        Messages1.OnFragmentInteractionListener, FileAct.OnFragmentInteractionListener, Amis.OnFragmentInteractionListener,
        Profil.OnFragmentInteractionListener,Recherche.OnFragmentInteractionListener,parametrees.OnFragmentInteractionListener
{

    private TextView infos;
    private ListView list;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
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
            Fragment newFragment = new Messages1();
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
}
