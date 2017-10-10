package unb.tg.osgi.g2.impl.p3;

import unb.tg.osgi.g2.api.G2;

public class P3 implements G2 {

	public String getDistance2Empty() {
		return "P3: Using data from onboard computer";
	}

	protected void activate() {
		System.out.println("P3 Activated");
	}

	protected void deactivate() {
		System.out.println("P3 Deactivated");
	}
}
