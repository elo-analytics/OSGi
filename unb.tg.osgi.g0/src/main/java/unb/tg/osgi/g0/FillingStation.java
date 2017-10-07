package unb.tg.osgi.g0;
import unb.tg.osgi.g2.api.G2;
import unb.tg.osgi.g3.api.G3;
import unb.tg.osgi.g1.api.G1;

public class FillingStation {
	
	G1 pos;
	G2 distance;
	G3 location;
	
	public void setPosition (G1 position) {
		pos = position;
	}
	
	public void setDistance (G2 dis) {
		distance = dis;
	}
	
	public void setLocation (G3 loc) {
		location = loc;
	}
	
	protected void activate () {
		System.out.println("\n\n--------------------------------\n"
				+ 	"Starting Filling Station Advisor\n"
				+ 	"--------------------------------\n\n");
		
		System.out.println("G1: Get position - " + pos.getPosition());
		System.out.println("G2: Assess distance to empty - " + distance.getDistance2Empty());
		System.out.println("G3: Recover info about nearby filling station - " + location.getNearbyStations());
		System.out.println("G4: Decide more convenient - ");
		System.out.println("G5: Driver is notified - ");
	}
	
	protected void deactivate () {
		System.out.println("\n\n--------------------------------\n"
				+ 	"Stopping Filling Station Advisor\n"
				+ 	"--------------------------------\n\n");
	}
}
