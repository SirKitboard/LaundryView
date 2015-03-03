package io.github.adibalwani03.laundryview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aditya on 10/3/2014.
 */
public class MachineAdapter extends ArrayAdapter<Machine> {
	public MachineAdapter (Context context, int textViewResourceId) {
		super(context,textViewResourceId);
	}
	public MachineAdapter(Context context,int textViewResourceId, List<Machine> items) {
		super(context,textViewResourceId,items);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		if(v==null) {
			LayoutInflater vi;
			vi = LayoutInflater.from(getContext());
			v = vi.inflate(R.layout.machine_row, null);
		}

		Machine p = getItem(position);

		if(p!=null) {
			ImageView iv = (ImageView)v.findViewById(R.id.statusImage);
			TextView tv1 = (TextView)v.findViewById(R.id.machineNo);
			TextView tv2 = (TextView)v.findViewById(R.id.machineTime);
			if(iv!=null) {
				iv.setImageResource(p.getImageid());
			}
			if(tv1!=null) {
				if(p.getWasher())
					tv1.setText("Washer #" + p.getMachineNo());
				else {
					tv1.setText("Dryer #" + p.getMachineNo());
				}
			}
			if(tv2!=null) {
				tv2.setText(p.toString());
			}
		}

		return v;
	}
}
