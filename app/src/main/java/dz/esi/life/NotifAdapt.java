package dz.esi.life;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by USER on 26/04/2015.
 */
public class NotifAdapt extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;

    public NotifAdapt(Context context, String[] values) {
        super(context, R.layout.row_layout_notif_msg, values);
        this.context = context;
        this.values = values;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout_notif_msg, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.firstLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        //Bitmap bitmap = ((BitmapDrawable).getDrawable(R.drawable.circle)).getBitmap();
       // imageView.setImageBitmap(getRoundedShape(bitmap));
        textView.setText(values[position]);
        // change the icon for Windows and iPhone
        String s = values[position];

        return rowView;
    }

    public Bitmap getRoundedShape(Bitmap bitmap) {

        Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        //smooth out the edges
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(Color.parseColor("#BBBBBB"));
        paint.setStrokeWidth(5);

        Canvas c = new Canvas(circleBitmap);
        c.drawARGB(0, 0, 0, 0);
        //This draw a circle of Gerycolor which will be the border of image.
        c.drawCircle(bitmap.getWidth()/2, bitmap.getHeight()/2, bitmap.getWidth()/2, paint);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setAntiAlias(true);
        paint.setShader(shader);
        // This will draw the image.
        c.drawCircle(bitmap.getWidth()/2, bitmap.getHeight()/2, bitmap.getWidth()/2-6, paint);
        return circleBitmap;
    }

}
