package unb.tg.osgi.g2.impl.p7.p8.impl;

import unb.tg.osgi.g2.impl.p7.p8.api.IP8;

public class P8Impl implements IP8 {

	public String getUserInput() {
		return "P8: Getting user input on tank capacity, mileage and fuel level";
	}
	
	protected void activate() {
		System.out.println("P8 Activated");
	}
	
	protected void deactivate() {
		System.out.println("P8 Deactivated");
	}

}
