package org.equinoxosgi.toastDS.internal.client.emergency;

import org.equinoxosgi.toastDS.dev.airbag.IAirbag;
import org.equinoxosgi.toastDS.dev.airbag.IAirbagListener;
import org.equinoxosgi.toastDS.dev.gps.IGps;

public class EmergencyMonitor implements IAirbagListener {
	private IAirbag airbag;
	private IGps gps;
	
	public void deployed() {
		System.out.println("Emergency occurred at lat=" + gps.getLatitude()
		+ " lon=" + gps.getLongitude() + " heading=" +	gps.getHeading()
		+ " speed=" + gps.getSpeed());
	}
	public void setAirbag(IAirbag value) {
		airbag = value;
	}
	public void setGps(IGps value) {
		gps = value;
	}
	public void shutdown() {
		airbag.removeListener(this);
	}
	public void startup() {
		airbag.addListener(this);
	}
}