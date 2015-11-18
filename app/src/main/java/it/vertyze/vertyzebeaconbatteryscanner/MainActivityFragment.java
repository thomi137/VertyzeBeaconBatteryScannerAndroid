package it.vertyze.vertyzebeaconbatteryscanner;

import android.app.ListFragment;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.kontakt.sdk.android.common.profile.IBeaconDevice;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends ListFragment {

    public MainActivityFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        MainActivity mainActivity = (MainActivity) getActivity();
        List<IBeaconDevice> beaconDeviceList = mainActivity.getDeviceList();
        IBeaconDevice[] values = new IBeaconDevice[0];
        beaconDeviceList.toArray(values);

        BeaconArrayAdapter adapter = new BeaconArrayAdapter(getActivity().getBaseContext(), values);
        setListAdapter(adapter);
    }

    /*
    Commented out IDE provided code to implement from list fragment.


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    */
}
