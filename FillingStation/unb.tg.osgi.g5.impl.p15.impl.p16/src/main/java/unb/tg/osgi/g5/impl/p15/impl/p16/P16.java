package unb.tg.osgi.g5.impl.p15.impl.p16;

import unb.tg.osgi.g5.impl.p15.api.IP15;

public class P16 implements IP15 {

	public String getVoice() {
		return "P16: Using synthesized voice";
	}
	
	protected void activate() {
		System.out.println("P16 Activated");
	}
	
	protected void deactivate() {
		System.out.println("P16 Deactivated");
	}

}
