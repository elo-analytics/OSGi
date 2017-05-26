package org.equinoxosgi.toast.internal.client.emergency.bundle;

import org.equinoxosgi.toast.dev.airbag.IAirbag;
import org.equinoxosgi.toast.dev.gps.IGps;
import org.equinoxosgi.toast.internal.client.emergency.EmergencyMonitor;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	private IAirbag airbag;
	private ServiceReference airbagRef;	
	private IGps gps;
	private ServiceReference gpsRef;
	private EmergencyMonitor monitor;
	
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Launching");
		monitor = new EmergencyMonitor();	//instantiates monitor
		
		/* The BundleContext supplied to start is the bundle’s means of 
		 * interacting with the OSGi framework. 
		 * A ServiceReference is a handle to a service object rather than 
		 * the service itself. The activator fails to acquire it if the 
		 * service has not yet been registered. Given a ServiceReference, 
		 * you can use the BundleContext’s getService method to dereference it 
		 * and get the service object it represents
		 */
		gpsRef = context.getServiceReference(IGps.class.getName());	//injects dependencies
		airbagRef = context.getServiceReference(IAirbag.class.getName());
		if (gpsRef == null || airbagRef == null) {
			System.err.println("Unable to acquire GPS or airbag!");
			return;
		}
		
		gps = (IGps) context.getService(gpsRef);
		airbag = (IAirbag) context.getService(airbagRef);
		if (gps == null || airbag == null) {
			System.err.println("Unable to acquire GPS or airbag!");
			return;
		}
		
		monitor.setGps(gps);
		monitor.setAirbag(airbag);
		monitor.startup();
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		monitor.shutdown();
		/*Ungetting the service when you're done keeps the system running smoothly*/
		if (gpsRef != null)
			context.ungetService(gpsRef);
		if (airbagRef != null)
			context.ungetService(airbagRef);
		System.out.println("Terminating");

	}

}
