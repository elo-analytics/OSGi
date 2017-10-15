package org.tg.osgi.intelligence.dev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.osgi.framework.BundleException;

public class Main {

	public static void main(String[] args) throws InterruptedException, BundleException {
		
		Launcher launcher = new Launcher();
		
		launcher.addScenarioResource("antenna_triangulation");
		launcher.addScenarioResource("protocol_get_fuel_level_and_mileage");
		launcher.addScenarioResource("storage");
		launcher.addScenarioResource("sound");
		
		launcher.executeGoal("Filling Station");
		
		//stop bundle
		//launcher.stop("unb.tg.osgi.fillingStation");
		//launcher.shutdown();
		System.out.println("End of execution!");

	}

}
