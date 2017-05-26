package org.equinoxosgi.toast.client.emergency;

import org.equinoxosgi.toast.dev.airbag.Airbag;
import org.equinoxosgi.toast.dev.gps.FakeGps;
import org.equinoxosgi.toast.dev.gps.IGps;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private Airbag airbag;
	private IGps gps;
	private EmergencyMonitor monitor;
	
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Launching");
		gps = new FakeGps();
		airbag = new Airbag();
		monitor = new EmergencyMonitor();
		
		monitor.setGps(gps);
		monitor.setAirbag(airbag);
		monitor.startup();
		
		airbag.deploy();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		monitor.shutdown();
		System.out.println("Terminating");

	}

}
