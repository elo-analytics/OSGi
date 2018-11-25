package unb.tg.osgi.g3.impl.p11;

import unb.tg.osgi.g3.api.G3;

public class P11 implements G3 {

	public String getNearbyStations() {
		return "P11: Querying Offline";
	}
	
	protected void activate() {
		System.out.println("P11 Activated");
	}
	
	protected void deactivate() {
		System.out.println("P11 Deactivated");
	}

	
}
