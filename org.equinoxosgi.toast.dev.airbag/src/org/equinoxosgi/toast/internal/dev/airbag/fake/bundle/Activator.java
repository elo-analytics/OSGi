package org.equinoxosgi.toast.internal.dev.airbag.fake.bundle;

import org.equinoxosgi.toast.dev.airbag.IAirbag;
import org.equinoxosgi.toast.internal.dev.airbag.fake.FakeAirbag;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	private ServiceRegistration registration;

	@Override
	public void start(BundleContext context) throws Exception {
		FakeAirbag airbag = new FakeAirbag();
		registration = context.registerService(
				IAirbag.class.getName(), airbag, null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		registration.unregister();
	}

}
