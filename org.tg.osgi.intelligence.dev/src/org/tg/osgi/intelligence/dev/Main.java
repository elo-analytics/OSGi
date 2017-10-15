package org.tg.osgi.intelligence.dev;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleException;

public class Main {
	private static void pressAnyKeyToContinue() {
		System.out.println("Press any key to continue execution...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException, BundleException {
		
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		
		Launcher launcher = new Launcher();
		
		// S1
		System.out.println("--------- Starting test case S1 ---------");
		
		launcher.addScenarioResource("antenna_triangulation", "unb.tg.osgi.g1");
		launcher.addScenarioResource("protocol_get_fuel_level_and_mileage", "unb.tg.osgi.g2.impl.p4.p5");
		launcher.addScenarioResource("storage", "unb.tg.osgi.g3");
		launcher.addScenarioResource("sound", "unb.tg.osgi.g5.impl.p15");
		
		launcher.executeGoal("unb.tg.osgi.g0");
		
		pressAnyKeyToContinue();
		
		// S2
		System.out.println("--------- Starting test case S2 ---------");
		launcher.cleanScenario();
		launcher.addScenarioResource("gps_capability", "unb.tg.osgi.g1");
		launcher.addScenarioResource("protocol_on_board_computer_get_distante_to_empty", "unb.tg.osgi.g2");
		launcher.addScenarioResource("internet_connection", "unb.tg.osgi.g3");
		launcher.addScenarioResource("synthesized_voice", "unb.tg.osgi.g5.impl.p15");
		
		launcher.executeGoal("unb.tg.osgi.g0");
		
		pressAnyKeyToContinue();
		
		// S3
		System.out.println("--------- Starting test case S3 ---------");
		launcher.cleanScenario();
		launcher.addScenarioResource("gps_capability", "unb.tg.osgi.g1");
		launcher.addScenarioResource("internet_connection", "unb.tg.osgi.g3");
		launcher.addScenarioResource("synthesized_voice", "unb.tg.osgi.g5.impl.p15");
		
		launcher.executeGoal("unb.tg.osgi.g0");
			
		pressAnyKeyToContinue();

		// S4
		System.out.println("--------- Starting test case S4 ---------");
		launcher.cleanScenario();
		launcher.addScenarioResource("gps_capability", "unb.tg.osgi.g1");
		launcher.addScenarioResource("protocol_on_board_computer_get_distante_to_empty", "unb.tg.osgi.g2");
		launcher.addScenarioResource("storage", "unb.tg.osgi.g3");
		launcher.addScenarioResource("visible_graphical_interface", "unb.tg.osgi.g5");
		
		launcher.executeGoal("unb.tg.osgi.g0");
		
		pressAnyKeyToContinue();
		
		// S5
		System.out.println("--------- Starting test case S5 ---------");
		launcher.cleanScenario();
		launcher.addScenarioResource("gps_capability", "unb.tg.osgi.g1");
		launcher.addScenarioResource("protocol_on_board_computer_get_distante_to_empty", "unb.tg.osgi.g2");
		launcher.addScenarioResource("internet_connection", "unb.tg.osgi.g3");
		launcher.addScenarioResource("interface_navigation_system", "unb.tg.osgi.g5");
		
		launcher.executeGoal("unb.tg.osgi.g0");
		
		pressAnyKeyToContinue();
		
		// S6
		System.out.println("--------- Starting test case S6 ---------");
		launcher.cleanScenario();
		launcher.addScenarioResource("protocol_on_board_computer_get_distante_to_empty", "unb.tg.osgi.g2");
		launcher.addScenarioResource("storage", "unb.tg.osgi.g3");
		launcher.addScenarioResource("synthesized_voice", "unb.tg.osgi.g5.impl.p15");
		
		launcher.executeGoal("unb.tg.osgi.g0");
		
		pressAnyKeyToContinue();
		
		// S7
		System.out.println("--------- Starting test case S7 ---------");
		launcher.cleanScenario();
		launcher.addScenarioResource("gps_capability", "unb.tg.osgi.g1");
		launcher.addScenarioResource("protocol_on_board_computer_get_distante_to_empty", "unb.tg.osgi.g2");
		launcher.addScenarioResource("interface_navigation_system", "unb.tg.osgi.g5");
		
		launcher.executeGoal("unb.tg.osgi.g0");
		
		pressAnyKeyToContinue();

		
		launcher.cleanScenario();
		launcher.shutdown();
		System.out.println("End of execution!");

	}

}
