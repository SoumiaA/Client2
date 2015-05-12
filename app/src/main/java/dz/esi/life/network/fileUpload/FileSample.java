package dz.esi.life.network.fileUpload;

import android.app.Activity;
import android.content.res.AssetManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by MEFTAH on 07/05/2015.
 */
public class FileSample {

    private final String ASSET_FILE_NAME="assets/android.png";
    private final String FILE_STORAGE_NAME="android.png";
    public File FileSample(Activity activity) {
        AssetManager am = activity.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = am.open(ASSET_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return createFileFromInputStream(inputStream);
    }

    private File createFileFromInputStream(InputStream inputStream) {

        try{
            File f = new File(FILE_STORAGE_NAME);
            OutputStream outputStream = new FileOutputStream(f);
            byte buffer[] = new byte[1024];
            int length = 0;

            while((length=inputStream.read(buffer)) > 0) {
                outputStream.write(buffer,0,length);
            }

            outputStream.close();
            inputStream.close();

            return f;
        }catch (IOException e) {
            //Logging exception
            e.printStackTrace();
        }

        return null;
    }
}
