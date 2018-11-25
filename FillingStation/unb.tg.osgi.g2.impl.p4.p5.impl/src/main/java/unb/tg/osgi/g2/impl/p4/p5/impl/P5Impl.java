package unb.tg.osgi.g2.impl.p4.p5.impl;

import unb.tg.osgi.g2.impl.p4.p5.api.IP5;

public class P5Impl implements IP5 {

	public String getData() {
		return "P5: Accessing fuel level and mileage";
	}
	
	protected void activate() {
		System.out.println("P5 Activated");
	}
	
	protected void deactivate() {
		System.out.println("P5 Deactivated");
	}
	
}
