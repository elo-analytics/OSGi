package unb.tg.osgi.g5.impl.p18;

import unb.tg.osgi.g5.api.G5;

public class P18 implements G5 {

	public String getNotification() {
		return "P18: Alerting by visual sign";
	}
	
	protected void activate() {
		System.out.println("P18 Activated");
	}
	
	protected void deactivate() {
		System.out.println("P18 Deactivated");
	}

}
