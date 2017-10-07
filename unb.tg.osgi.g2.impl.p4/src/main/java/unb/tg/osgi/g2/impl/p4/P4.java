package unb.tg.osgi.g2.impl.p4;

import unb.tg.osgi.g2.api.G2;
import unb.tg.osgi.g2.impl.p4.p5.api.IP5;

public class P4 implements G2 {
	
	private IP5 p5; 

	void setP5 (IP5 p5) {
		this.p5 = p5;
	}
	
	public String getDistance2Empty() {
		return "P4: Calculate based on fuel level\n	" +
					"	" + p5.getData();
	}

	protected void activate() {
		System.out.println("P4 Activated");
	}

	protected void deactivate() {
		System.out.println("P4 Deactivated");
	}
}
