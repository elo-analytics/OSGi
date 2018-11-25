package unb.tg.osgi.g3.impl.p10;

import unb.tg.osgi.g3.api.G3;

public class P10 implements G3 {

	public String getNearbyStations() {
		return "P10: Querying Online";
	}
	
	protected void activate() {
		System.out.println("P10 Activated");
	}
	
	protected void deactivate() {
		System.out.println("P10 Deactivated");
	}

	
}
