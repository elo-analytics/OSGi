package unb.tg.osgi.g4.impl.p12;

import unb.tg.osgi.g4.api.G4;

public class P12 implements G4 {

	public String makeDecision() {
		return "P12: Get Constraints";
	}
	
	protected void activate() {
		System.out.println("P12 Activated");
	}
	
	protected void deactivate() {
		System.out.println("P12 Deactivated");
	}
	
}
