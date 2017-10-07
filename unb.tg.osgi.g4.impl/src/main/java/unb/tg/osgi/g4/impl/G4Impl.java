package unb.tg.osgi.g4.impl;

import unb.tg.osgi.g4.api.G4;
import unb.tg.osgi.g4.impl.p12.api.P12;
import unb.tg.osgi.g4.impl.p13.api.P13;

public class G4Impl implements G4 {
	
	private P12 p12;
	private P13 p13;
	
	public void setP12 (P12 p12) {
		this.p12 = p12;
	}
	
	public void setP13 (P13 p13) {
		this.p13 = p13;
	}

	public String makeDecision() {
		return p12.getConstraints() + " AND " + p13.chooseStation();
	}
	
	protected void activate() {
		System.out.println("G4 Activated");
	}
	
	protected void deactivate() {
		System.out.println("G4 Deactivated");
	}
	
}
