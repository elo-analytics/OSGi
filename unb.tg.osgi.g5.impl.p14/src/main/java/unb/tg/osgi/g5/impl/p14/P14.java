package unb.tg.osgi.g5.impl.p14;

import unb.tg.osgi.g5.api.G5;

public class P14 implements G5 {

	public String getNotification() {
		return "P14: Using Navigation System";
	}
	
	protected void activate() {
		System.out.println("P14 Activated");
	}
	
	protected void deactivate() {
		System.out.println("P14 Deactivated");
	}

}
