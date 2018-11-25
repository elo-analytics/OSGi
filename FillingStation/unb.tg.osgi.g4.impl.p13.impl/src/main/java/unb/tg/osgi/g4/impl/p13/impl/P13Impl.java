package unb.tg.osgi.g4.impl.p13.impl;

import unb.tg.osgi.g4.impl.p13.api.P13;

public class P13Impl implements P13 {

	public String chooseStation() {
		return "P13: Choosing Filling Station";
	}
	
	protected void activate() {
		System.out.println("P13 Activated");
	}
	
	protected void deactivate() {
		System.out.println("P13 Deactivated");
	}

}
