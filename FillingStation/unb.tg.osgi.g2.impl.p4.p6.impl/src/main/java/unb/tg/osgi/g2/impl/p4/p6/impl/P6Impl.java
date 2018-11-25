package unb.tg.osgi.g2.impl.p4.p6.impl;

import unb.tg.osgi.g2.impl.p4.p6.api.IP6;

public class P6Impl implements IP6 {

	public String getDistance() {
		return "P6: Calculating distance to empty";
	}
	
	protected void activate() {
		System.out.println("P6 Activated");
	}
	
	protected void deactivate() {
		System.out.println("P6 Deactivated");
	}

}
