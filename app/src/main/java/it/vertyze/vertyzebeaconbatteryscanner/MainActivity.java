package it.vertyze.vertyzebeaconbatteryscanner;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.kontakt.sdk.android.ble.configuration.ActivityCheckConfiguration;
import com.kontakt.sdk.android.ble.configuration.ForceScanConfiguration;
import com.kontakt.sdk.android.ble.configuration.ScanPeriod;
import com.kontakt.sdk.android.ble.configuration.scan.IBeaconScanContext;
import com.kontakt.sdk.android.ble.configuration.scan.ScanContext;
import com.kontakt.sdk.android.ble.connection.OnServiceReadyListener;
import com.kontakt.sdk.android.ble.discovery.BluetoothDeviceEvent;
import com.kontakt.sdk.android.ble.discovery.EventType;
import com.kontakt.sdk.android.ble.discovery.ibeacon.IBeaconDeviceEvent;
import com.kontakt.sdk.android.ble.filter.ibeacon.IBeaconFilters;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.ble.rssi.RssiCalculators;
import com.kontakt.sdk.android.ble.util.BluetoothUtils;
import com.kontakt.sdk.android.common.profile.IBeaconDevice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MainActivity extends ActionBarActivity implements ProximityManager.ProximityListener {

    private static final int REQUEST_CODE_ENABLE_BLUETOOTH = 1;
    private ProximityManager proximityManager;
    private ScanContext scanContext;
    private List<IBeaconDevice> deviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.deviceList = new ArrayList<IBeaconDevice>();

        proximityManager = new ProximityManager(this);
        IBeaconScanContext iBeaconScanContext = new IBeaconScanContext.Builder()
                .setEventTypes(EnumSet.of(EventType.DEVICE_DISCOVERED, EventType.DEVICE_LOST))
                .setRssiCalculator(RssiCalculators.newLimitedMeanRssiCalculator(5))
                .setIBeaconFilters(Arrays.asList(
                        IBeaconFilters.newProximityUUIDFilter(UUID.fromString(getString(R.string.valora_uuid_string)))
                ))
                .build();

        scanContext = new ScanContext.Builder()
                .setScanMode(ProximityManager.SCAN_MODE_BALANCED)
                .setIBeaconScanContext(iBeaconScanContext)
                .setActivityCheckConfiguration(ActivityCheckConfiguration.DEFAULT)
                .setForceScanConfiguration(ForceScanConfiguration.DEFAULT)
                .setScanPeriod(new ScanPeriod(TimeUnit.SECONDS.toMillis(5), 0))
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume(){
        super.onResume();
        if(!BluetoothUtils.isBluetoothEnabled()){
            final Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_CODE_ENABLE_BLUETOOTH);
        } else {
            initializeScan();
        }

    }

    @Override
    protected void onPause(){
        super.onPause();
        proximityManager.finishScan();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        proximityManager.disconnect();
        proximityManager = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case REQUEST_CODE_ENABLE_BLUETOOTH:
                if(resultCode == RESULT_OK){
                    initializeScan();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * Called when scan starts.
     */
    @Override
    public void onScanStart() {

    }

    /**
     * Called when scan stops.
     */
    @Override
    public void onScanStop() {

    }

    /**
     * Called whenever specific event occurs.
     *
     * @param event the event
     */
    @Override
    public void onEvent(BluetoothDeviceEvent event) {
        IBeaconDeviceEvent iBeaconDeviceEvent = (IBeaconDeviceEvent) event;
        deviceList = iBeaconDeviceEvent.getDeviceList();
    }

    private void initializeScan(){
        proximityManager.initializeScan(scanContext, new OnServiceReadyListener(){

            /**
             * Called when Object implementing ServiceConnector interface successfully
             * connected to the corresponding service.
             */
            @Override
            public void onServiceReady() {
                proximityManager.attachListener(MainActivity.this);
            }

            /**
             * Called when an error occurs during establishing connection to the corresponding service.
             */
            @Override
            public void onConnectionFailure() {
            }
        });
    }

    public List<IBeaconDevice> getDeviceList(){
        return this.deviceList;
    }

}
