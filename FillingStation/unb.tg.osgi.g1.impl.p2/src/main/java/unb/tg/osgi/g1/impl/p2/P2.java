package unb.tg.osgi.g1.impl.p2;

import unb.tg.osgi.g1.api.G1;

public class P2 implements G1 {

	public String getPosition() {
		return "P2: Using Antenna tringulation";
	}

	protected void activate() {
		System.out.println("P2 Activated");
	}

	protected void deactivate() {
		System.out.println("P2 Deactivated");
	}
}
