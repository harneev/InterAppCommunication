package singh.harneev.atc.app;

import android.app.Application;

/**
 * Created by harneev on 22/06/17.
 */

public class ATCApp extends Application {

    private static ATCApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
    }

    public static synchronized ATCApp getInstance() {
        return sInstance;
    }
}
