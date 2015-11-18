package it.vertyze.vertyzebeaconbatteryscanner;

import android.app.Application;

import com.kontakt.sdk.android.common.KontaktSDK;
import com.kontakt.sdk.android.common.log.LogLevel;

/**
 * Created by thomasprosser on 18.11.15.
 */
public class App extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        KontaktSDK.initialize(this)
                .setDebugLoggingEnabled(BuildConfig.DEBUG)
                .setLogLevelEnabled(LogLevel.DEBUG, true)
                .setCrashlyticsLoggingEnabled(true);
    }
}
