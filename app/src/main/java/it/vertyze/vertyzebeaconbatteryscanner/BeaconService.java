package it.vertyze.vertyzebeaconbatteryscanner;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by thomasprosser on 19.11.15.
 */
public class BeaconService extends Service {

    private static final String SERVICE_NAME = BeaconService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

