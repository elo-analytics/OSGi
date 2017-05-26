package org.equinoxosgi.toast.internal.dev.airbag.fake;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.equinoxosgi.toast.dev.airbag.IAirbag;
import org.equinoxosgi.toast.dev.airbag.IAirbagListener;

public class FakeAirbag implements IAirbag {
	private List listeners;
	
	public FakeAirbag() {
		super();
		listeners = new ArrayList();
	}
	
	@Override
	public synchronized void addListener(IAirbagListener listener){
		listeners.add(listener);
	}
	
	public synchronized void deploy(){
		for (Iterator i = listeners.iterator(); i.hasNext();)
			((IAirbagListener) i.next()).deployed();
	}
	
	@Override
	public synchronized void removeListener(IAirbagListener listener){
		listeners.remove(listener);
	}
}
