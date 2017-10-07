package unb.tg.osgi.g0;
import unb.tg.osgi.g2.api.G2;
import unb.tg.osgi.g3.api.G3;
import unb.tg.osgi.g4.api.G4;
import unb.tg.osgi.g5.api.G5;
import unb.tg.osgi.g1.api.G1;

public class FillingStation {
	
	G1 position;
	G2 distance;
	G3 location;
	G4 decision;
	G5 notification;
	
	public void setPosition (G1 pos) {
		position = pos;
	}
	
	public void setDistance (G2 dis) {
		distance = dis;
	}
	
	public void setLocation (G3 loc) {
		location = loc;
	}
	
	public void setDecision (G4 dec) {
		decision = dec;
	}
	
	public void setNotification (G5 not) {
		notification = not;
	}
	
	protected void activate () {
		System.out.println("\n\n--------------------------------\n"
				+ 	"Starting Filling Station Advisor\n"
				+ 	"--------------------------------\n\n");
		
		System.out.println("(G1) Get position :\n	" + position.getPosition());
		System.out.println("(G2) Assess distance to empty: \n	" + distance.getDistance2Empty());
		System.out.println("(G3) Recover info about nearby filling station: \n	" + location.getNearbyStations());
		System.out.println("(G4) Decide more convenient:\n	" + decision.makeDecision());
		System.out.println("(G5) Driver is notified:\n	" + notification.getNotification());
	}
	
	protected void deactivate () {
		System.out.println("\n\n--------------------------------\n"
				+ 	"Stopping Filling Station Advisor\n"
				+ 	"--------------------------------\n\n");
	}
}
