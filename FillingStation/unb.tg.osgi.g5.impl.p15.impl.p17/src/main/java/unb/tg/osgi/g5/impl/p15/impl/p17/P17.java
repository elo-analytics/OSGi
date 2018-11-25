package unb.tg.osgi.g5.impl.p15.impl.p17;

import unb.tg.osgi.g5.impl.p15.api.IP15;

public class P17 implements IP15 {

	public String getVoice() {
		return "P16: Using pre-recorded voice";
	}
	
	protected void activate() {
		System.out.println("P17 Activated");
	}
	
	protected void deactivate() {
		System.out.println("P17 Deactivated");
	}

}
