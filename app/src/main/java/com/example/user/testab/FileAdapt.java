package com.example.user.testab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by USER on 04/05/2015.
 */
public class FileAdapt  extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    private final Integer[] tab;

    public FileAdapt(Context context, String[] values, Integer [] tab) {
        super(context, R.layout.row_card_file, values);
        this.context = context;
        this.values = values;
        this.tab=tab;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_card_file, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imagepub);
        imageView.setImageResource(tab[position]);
        textView.setText(values[position]);
        // change the icon for Windows and iPhone
        String s = values[position];

        return rowView;
    }
}
