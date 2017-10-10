package unb.tg.osgi.g1.impl.p1;

import unb.tg.osgi.g1.api.G1;

public class P1 implements G1 {

	public String getPosition() {
		return "P1: Using GPS signal";
	}
	
	protected void activate() {
		System.out.println("P1 Activated");
	}
	
	protected void deactivate() {
		System.out.println("P1 Deactivated");
	}
}
