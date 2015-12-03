package it.vertyze.vertyzebeaconbatteryscanner;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    public void updateBeaconList(List<IBeaconDevice> deviceList){
        BeaconArrayAdapter adapter = new BeaconArrayAdapter(getActivity().getBaseContext(), deviceList);
        setListAdapter(adapter);
    }
}
