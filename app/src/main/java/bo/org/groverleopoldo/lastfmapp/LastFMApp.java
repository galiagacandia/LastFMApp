package bo.org.groverleopoldo.lastfmapp;

import android.app.Application;
import android.os.SystemClock;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Grover Leopoldo on 17/9/2017.
 */

public class LastFMApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        SystemClock.sleep(3000);
    }
}
