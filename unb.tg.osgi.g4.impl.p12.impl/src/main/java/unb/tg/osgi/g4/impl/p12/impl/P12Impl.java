package unb.tg.osgi.g4.impl.p12.impl;

import unb.tg.osgi.g4.impl.p12.api.P12;

public class P12Impl implements P12 {

	public String getConstraints() {
		return "P12: Getting Constraints";
	}
	
	protected void activate() {
		System.out.println("P12 Activated");
	}
	
	protected void deactivate() {
		System.out.println("P12 Deactivated");
	}

}
