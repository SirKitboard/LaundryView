package io.github.adibalwani03.laundryview;

import android.graphics.Bitmap;

/**
 * Created by Aditya on 9/11/2014.
 */
public class Machine {
	int machineNo;
	int machineStatus;
	int minsLeft;
	int imageid;
	boolean washer;

	public Machine(int machineNo, int machineStatus, int minsLeft, boolean washer) {
		this.machineNo = machineNo;
		this.machineStatus = machineStatus;
		this.minsLeft = minsLeft;
		this.washer = washer;
		generateImage();
	}

	public void setMachineStatus(int machineStatus) {
		this.machineStatus = machineStatus;
		generateImage();
	}

	public void generateImage() {
		if (washer) {
			if (machineStatus == 0 || machineStatus==1) {
				imageid = R.drawable.washer_available;
			} else if (machineStatus == 2) {
				imageid = R.drawable.washer_busy;
			} else {
				imageid = R.drawable.washer_broken;
			}
		} else {
			if (machineStatus == 0 || machineStatus==1) {
				imageid = R.drawable.drier_available;
			} else if (machineStatus==2) {
				imageid = R.drawable.drier_busy;
			} else {
				imageid = R.drawable.drier_broken;
			}

		}
	}

	public void setMachineNo(int machineNo) {
		this.machineNo = machineNo;
	}

	public void setMinsLeft(int minsLeft) {
		this.minsLeft = minsLeft;
	}

	public int getMachineNo() {

		return machineNo;
	}

	public int getMachineStatus() {
		return machineStatus;
	}

	public int getMinsLeft() {
		return minsLeft;
	}

	public int getImageid() {
		return imageid;
	}
	public boolean getWasher() {
		return washer;
	}
	public String toString() {
		String ret = "";
		if(machineStatus==0) {
			ret = "Available";
		}
		else if(machineStatus ==1) {
			ret = "Cycle ended "+minsLeft+" minutes ago";
		}
		else if (machineStatus == 2) {
			ret = minsLeft + " Minutes till completion";
		}
		else {
			ret = "Machine out of order";
		}
		return ret;
	}
}
