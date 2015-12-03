package it.vertyze.vertyzebeaconbatteryscanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kontakt.sdk.android.common.model.Beacon;
import com.kontakt.sdk.android.common.profile.IBeaconDevice;

import java.util.List;

/**
 * Created by thomasprosser on 19.11.15.
 */
public class BeaconArrayAdapter extends ArrayAdapter<IBeaconDevice> {

    private final Context context;
    private final List<IBeaconDevice> listValues;

    public BeaconArrayAdapter(Context context, List<IBeaconDevice> values){
        super(context, R.layout.beacon_row_layout, values);
        this.context = context;
        this.listValues = values;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.beacon_row_layout, parent, false);
        TextView beaconIdTextView = (TextView) rowView.findViewById(R.id.beacon_row_layout_beacon_id);
        TextView beaconBatteryPowerTextView = (TextView) rowView.findViewById(R.id.beacon_row_layout_beacon_power);

        IBeaconDevice beacon = listValues.get(position);
        beaconIdTextView.setText(beacon.getUniqueId());
        beaconBatteryPowerTextView.setText(beacon.getBatteryPower());

        return rowView;
    }

}
