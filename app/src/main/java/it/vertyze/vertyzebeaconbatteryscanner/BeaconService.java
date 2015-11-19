package it.vertyze.vertyzebeaconbatteryscanner;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

/**
 * Created by thomasprosser on 19.11.15.
 */
public class BeaconService extends IntentService {

    private static final String SERVICE_NAME = BeaconService.class.getSimpleName();

    public BeaconService() {
        super(SERVICE_NAME);
    }

    /**
     * This method is invoked on the worker thread with a request to process.
     * Only one Intent is processed at a time, but the processing happens on a
     * worker thread that runs independently from other application logic.
     * So, if this code takes a long time, it will hold up other requests to
     * the same IntentService, but it will not hold up anything else.
     * When all requests have been handled, the IntentService stops itself,
     * so you should not call {@link #stopSelf}.
     *
     * @param intent The value passed to {@link
     *               Context#startService(Intent)}.
     */
    @Override
    protected void onHandleIntent(Intent intent) {

    }

}
