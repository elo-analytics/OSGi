package org.equinoxosgi.toast.internal.client.emergency.bundle;

import org.equinoxosgi.toast.dev.airbag.IAirbag;
import org.equinoxosgi.toast.dev.gps.IGps;
import org.equinoxosgi.toast.internal.client.emergency.EmergencyMonitor;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class Activator implements BundleActivator {

	private IAirbag airbag;
	private ServiceTracker airbagTracker;
	private BundleContext context;
	private IGps gps;
	private ServiceTracker gpsTracker;
	private EmergencyMonitor monitor;
	
	private void bind(){
		if (gps == null){
			gps = (IGps) gpsTracker.getService();
			if (gps == null) {
				return; // No IGps service
			}
		}
		if (airbag == null) {
			airbag = (IAirbag) airbagTracker.getService();
			if (airbag == null)
				return; // No IAirbag service
		}
		monitor.setGps(gps);
		monitor.setAirbag(airbag);
		monitor.startup();
	}
	
	/* adding lifecycle functionality to the trackers.
	 * react to IAirbag and IGpsservices coming and going by 
	 * triggering the binding or unbinding of the emergency monitor 
	 * as appropriate.*/
	private ServiceTrackerCustomizer createAirbagCustomizer() {
		return new ServiceTrackerCustomizer() {
			public Object addingService (ServiceReference reference) {
				Object service = context.getService(reference);
				synchronized(Activator.this) {
					/* When the required airbag and GPS services are available,
					 * the trackers call bind and the emergency monitor’s setGps, 
					 * setAirbag, and startup methods.*/
					if (Activator.this.airbag == null) {
						Activator.this.airbag = (IAirbag) service;
						Activator.this.bind();
					}
				}
				return service;
			}

			@Override
			public void modifiedService(ServiceReference reference, Object service) {
				// No service property modifications to handle.
			}
			
			/* handles multiple instances of the same service type.*/
			@Override
			public void removedService(ServiceReference reference, Object service) {
				synchronized (Activator.this) {
					if (service != Activator.this.airbag)
						return;
					/* To be safe, bind and unbind are called from inside synchronized 
					 * blocks to make sure that service changes are handled atomically. */
					Activator.this.unbind();
					Activator.this.bind();
				}
			}
		};
	}
	
	private ServiceTrackerCustomizer createGpsCustomizer() {
		return new ServiceTrackerCustomizer() {
			public Object addingService (ServiceReference reference) {
				Object service = context.getService(reference);
				synchronized (Activator.this) {
					/* When the required airbag and GPS services are available,
					 * the trackers call bind and the emergency monitor’s setGps, 
					 * setAirbag, and startup methods.*/
					if (Activator.this.gps == null) {
						Activator.this.gps = (IGps) service;
						Activator.this.bind();
					}
				}
				return service;
			}
			
			public void modifiedService (ServiceReference reference, Object service) {
				// No service property modifications to handle
			}
			
			public void removedService (ServiceReference reference, Object service){
				synchronized (Activator.this) {
					if (service != Activator.this.gps)
						return;
					/* When unbinding one service instance, there may be alternative service 
					 * implementations of the same type—the customizer should try to rebind 
					 * to ensure uninterrupted execution.*/
					Activator.this.unbind();
					Activator.this.bind();
				}
			}
		};
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		this.context = context;
		monitor = new EmergencyMonitor();
		/*instead of trying to acquire the GPS and airbag services directly, it creates a ServiceTracker for each.*/
		ServiceTrackerCustomizer gpsCustomizer = createGpsCustomizer();
		gpsTracker = new ServiceTracker (context, IGps.class.getName(), gpsCustomizer);
		ServiceTrackerCustomizer airbagCustomizer = createAirbagCustomizer();
		airbagTracker = new ServiceTracker(context, IAirbag.class.getName(), airbagCustomizer);
		gpsTracker.open();
		airbagTracker.open();
	}

	/* simply closes the two Service Trackers. 
	 * Since a Service Tracker is implemented using service event listeners, 
	 * it is important that they be closed. 
	 * Failure to close the trackers results in listener leaks.*/
	@Override
	public void stop(BundleContext context) throws Exception {
		/* closing a tracker causes its tracked services to be removed 
		 * and thus the emergency monitor to be unbound.*/
		airbagTracker.close();
		gpsTracker.close();
	}
	
	/* it must ensure that it deactivates and unbinds the emergency monitor 
	 * only when the service in use is removed, not just when services are 
	 * removed.*/
	private void unbind() {
		if (gps == null || airbag == null)
			return;
		monitor.shutdown();
		gps = null;
		airbag = null;
	}

}
