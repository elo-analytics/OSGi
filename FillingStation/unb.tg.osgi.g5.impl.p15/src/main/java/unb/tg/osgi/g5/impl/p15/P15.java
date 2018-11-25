package unb.tg.osgi.g5.impl.p15;

import unb.tg.osgi.g5.api.G5;
import unb.tg.osgi.g5.impl.p15.api.IP15;

public class P15 implements G5 {
	
	private IP15 p15;
	
	void setP15 (IP15 p15) {
		this.p15 = p15;
	}

	public String getNotification() {
		return "P15: Alerting by sound - " + p15.getVoice();
	}
	
	protected void activate() {
		System.out.println("P15 Activated");
	}
	
	protected void deactivate() {
		System.out.println("P15 Deactivated");
	}

}
