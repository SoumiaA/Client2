package dz.esi.life;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import dz.esi.life.Model.Contenu;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FileAct.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FileAct#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FileAct extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FileAct() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FileAct.
     */
    // TODO: Rename and change types and number of parameters
    public static FileAct newInstance(String param1, String param2) {
        FileAct fragment = new FileAct();
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
    View v=  inflater.inflate(R.layout.fragment_file, container, false);

        /**modification**/
        ImageView statut = (ImageView) v.findViewById(R.id.statut);
        ImageView ajouterimg = (ImageView) v.findViewById(R.id.ajouterimg);
        ImageView cam = (ImageView) v.findViewById(R.id.cam);
        ImageView video = (ImageView) v.findViewById(R.id.video);
        Button pub = (Button) v.findViewById(R.id.buttonPublier);
        final TextView texte = (TextView) v.findViewById(R.id.publication);

        /*Publier Statut file act*/
        statut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

          /*Publier Img file act*/
        ajouterimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

         /*Publier video file act*/
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

         /*Publier Imgcam file act*/
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /*bouton publier*/
        pub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***********************************/
                Contenu contenu = new Contenu();
                contenu.name = texte.getText().toString();
                contenu.post();
                /*********************************/
            }
        });
        List<Contenu> l = Contenu.get();
        ListView list = (ListView)v.findViewById(R.id.listviewfile);

        String[] values = new String[l.size()];/* { "                                ", "", "",
                "", " ", "", "", "",
                "", "" };*/
        Integer [] tab = new Integer[]{
                R.drawable.place4, R.drawable.place3, R.drawable.place1, R.drawable.place2, R.drawable.photo
                , R.drawable.place4, R.drawable.place5, R.drawable.android, R.drawable.photo, R.drawable.ic_launcher

        };

        for (int i = 0; i < l.size(); i++) {
            Contenu c = l.get(i);
            if (c.texte == null) c.texte = c.name;
            values[i] = c.user.displayName + " : " + c.texte + ", le: " + c.created;
        }

        //Infos User

        //GetUtilisateur user = new GetUtilisateur(infos,getApplicationContext());
        //user.execute();
       /* NotifAdapt adapter = new NotifAdapt(v.getContext(),values);
        l.setAdapter(adapter);
        l.setClickable(true);*/
        FileAdapt adapter = new
                FileAdapt(v.getContext(),values,tab);
        list.addHeaderView(new View(getActivity()));
        list.addFooterView(new View(getActivity()));
        list.setAdapter(adapter);
        list.setClickable(true);
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
