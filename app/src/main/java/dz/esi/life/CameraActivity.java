package dz.esi.life;

/**
 * Created by MEFTAH on 13/05/2015.
 */

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * This activity assists in trapping the camera's "State" e.g. where the camera plans
 * on saving it's resulting file and URI. This activity saves this information to the bundle
 * and retrieves it on resume. This is necessary because when the Android external camera starts,
 * the file path anf URI get collected and won't be available on resume, resulting in a crash.
 * <p/>
 * Samsung devices, in particular may crash if you don't do this:
 * Reference: http://stackoverflow.com/questions/8248327/my-android-camera-uri-is-returning-a-null-value-but-the-samsung-fix-is-in-place
 * <p/>
 * Created by Rex St. John (on behalf of AirPair.com) on 3/4/14.
 */
public class CameraActivity extends ActionBarActivity {


    /**
     * Getters and setters.
     */


}