package org.equinoxosgi.toastDS.dev.airbag;

public interface IAirbag {

	void addListener(IAirbagListener listener);

	void removeListener(IAirbagListener listener);

}