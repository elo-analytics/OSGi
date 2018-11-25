package unb.tg.osgi.g2.impl.p7;

import unb.tg.osgi.g1.api.G1;
import unb.tg.osgi.g2.api.G2;
import unb.tg.osgi.g2.impl.p7.p8.api.IP8;

public class P7 implements G2 {
	
	private IP8 p8; 
	private G1 pos;

	void setP8 (IP8 p8) {
		this.p8 = p8;
	}
	
	void setPos (G1 pos) {
		this.pos = pos;
	}
	
	public String getDistance2Empty() {
		return "P7: Using user input and distance track\n" +
					"		" + p8.getUserInput() +
					"\n		" + pos.getPosition();
	}

	protected void activate() {
		System.out.println("P7 Activated");
	}

	protected void deactivate() {
		System.out.println("P7 Deactivated");
	}
}
