package com.example.user.testab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by USER on 10/05/2015.
 */
public class AmisAdapt extends BaseAdapter {
    private Context context;
    private final String[] amis;

    public AmisAdapt(Context context, String[] amis) {
        this.context = context;
        this.amis = amis;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.row_layout_ami, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(amis[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String mobile = amis[position];
//"Meftah Lakhder", "Ada Hanifi", "Bourabaa " "Flag ", "Said", "Soumia Alem ","Michel bound ", "Diego Delaviga ","Mostfai " ,"Obama"
            if (mobile.equals("Meftah Lakhder")) {
                imageView.setImageResource(R.drawable.guy);
            } else if (mobile.equals("Ada Hanifi")) {
                imageView.setImageResource(R.drawable.child);
            } else if (mobile.equals("Soumia Alem")) {
                imageView.setImageResource(R.drawable.woman);
            } else if (mobile.equals("Michel bound")) {
                imageView.setImageResource(R.drawable.woman2);
            }else if (mobile.equals("Diego Delaviga")) {
                imageView.setImageResource(R.drawable.ic_launcher);
            }else if (mobile.equals("Bourabaa ")) {
                imageView.setImageResource(R.drawable.guy);
            }else if (mobile.equals("Flag")) {
                imageView.setImageResource(R.drawable.child);
            }else if (mobile.equals("Obama")) {
                imageView.setImageResource(R.drawable.woman2);
            }
            else {
                imageView.setImageResource(R.drawable.ic_launcher);
            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;

    }

    @Override
    public int getCount() {
        return amis.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
