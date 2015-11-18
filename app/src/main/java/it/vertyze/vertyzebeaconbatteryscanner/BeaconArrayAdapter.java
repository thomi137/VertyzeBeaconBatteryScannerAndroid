package it.vertyze.vertyzebeaconbatteryscanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kontakt.sdk.android.common.profile.IBeaconDevice;

/**
 * Created by thomasprosser on 19.11.15.
 */
public class BeaconArrayAdapter extends ArrayAdapter<IBeaconDevice> {

    private final Context context;
    private final IBeaconDevice[] values;

    public BeaconArrayAdapter(Context context, IBeaconDevice[] values){
        super(context, R.layout.beacon_row_layout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.beacon_row_layout, parent, false);
        TextView beaconIdTextView = (TextView) rowView.findViewById(R.id.beacon_row_layout_beacon_id);
        TextView beaconBatteryPowerTextView = (TextView) rowView.findViewById(R.id.beacon_row_layout_beacon_power);

        IBeaconDevice beacon = values[position];
        beaconIdTextView.setText(beacon.getUniqueId());
        beaconBatteryPowerTextView.setText(beacon.getBatteryPower());

        return rowView;
    }

}
