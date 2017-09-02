package org.equinoxosgi.toastND.internal.dev.airbag.fake.bundle;

import org.equinoxosgi.toastND.dev.airbag.IAirbag;
import org.equinoxosgi.toastND.internal.dev.airbag.fake.FakeAirbag;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	private FakeAirbag airbag;
	private ServiceRegistration registration;
	public void start(BundleContext context) {
		airbag = new FakeAirbag();
		airbag.startup();
		registration = context.registerService(
				IAirbag.class.getName(), airbag, null);
	}
	public void stop(BundleContext context) {
		registration.unregister();
		airbag.shutdown();
	}
}
