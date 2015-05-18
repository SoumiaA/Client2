package dz.esi.life;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import dz.esi.life.Model.Contenu;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Messages.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Messages#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Messages extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Messages() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Messages.
     */
    // TODO: Rename and change types and number of parameters
    public static Messages newInstance(String param1, String param2) {
        Messages fragment = new Messages();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_messages, container, false);
        final TextView contenuText = (TextView) v.findViewById(R.id.contenu);
        Button publier = (Button) v.findViewById(R.id.publier);
        publier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contenu contenu = new Contenu();
                contenu.texte = contenuText.getText().toString();
                contenu.post();
            }
        });
        List<Contenu> list = Contenu.get();
        ListView l = (ListView) v.findViewById(R.id.listviewmsg);
        // list.setVisibility(View.INVISIBLE);
        String[] values = new String[list.size()];
        /*values= new String[] { "Meftah : Salut !", "Ada Hanifi : Take care Bro !", "Bourabaa redaa : where are you ? are you here",
                "Flag : wow!!!", "Said : Salem ", "Soumia Alem : il fait chaud aujourd hui",
                "Michel bound : cc !", "Diego Delaviga : eh té là ? ","Mostfai : salam 3alaykom" ,"Obama : what a great day man !"};
*/
        for (int i = 0; i < list.size(); i++) {
            Contenu c = list.get(i);
            values[i] = c.user.displayName + " : " + c.texte + ", le: " + c.created;
        }
        //Infos User

        //GetUtilisateur user = new GetUtilisateur(infos,getApplicationContext());
        //user.execute();

        NotifAdapt adapter = new NotifAdapt(v.getContext(), values);
        l.setAdapter(adapter);
        l.setClickable(true);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
