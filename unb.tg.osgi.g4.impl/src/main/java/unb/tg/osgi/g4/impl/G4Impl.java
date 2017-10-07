package unb.tg.osgi.g4.impl;

import unb.tg.osgi.g4.api.G4;
import unb.tg.osgi.g4.impl.p12.api.P12;

public class G4Impl implements G4 {
	
	private P12 p12;
	
	public void setP12 (P12 p) {
		p12 = p;
	}

	public String makeDecision() {
		return p12.getConstraints() + " AND ";
	}
	
	protected void activate() {
		System.out.println("G4 Activated");
	}
	
	protected void deactivate() {
		System.out.println("G4 Deactivated");
	}
	
}
