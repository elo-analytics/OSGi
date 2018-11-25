package org.goald.osgi.intelligence.dev;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.launch.Framework;

import goald.model.Agent;
import goald.model.CtxEvaluator;
import goald.model.Goal;
import goald.model.util.AgentBuilder;
import goald.model.util.CtxEvaluatorBuilder;
/*
import goald.FeelingStationAdvisorRepoMock;
import goald.model.CtxEvaluator;
import goald.model.Dame;
import goald.model.Goal;
import goald.model.util.AgentBuilder;
import goald.model.util.CtxEvaluatorBuilder;
import goald.model.util.RepoQueryBuilder;
import goald.planning.DamUpdater;
*/
//import org.osgi.framework.BundleException;
import goald.model.util.RepoQueryBuilder;
import notifyEmergencyMedicalServices.NotifyEmergencyMedicalServices;
import provideHealthSupport.ProvideHealthSupport;



public class Main {
	

	private static ServiceReference<?> reference;

	private static void pressAnyKeyToContinue() {
		System.out.println("Press any key to continue execution...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException { //, BundleException
		
		Launcher launcher = new Launcher();
		BundleContext context;
		BundleContext context1;
		Framework framework;

		CtxEvaluator ctx = CtxEvaluatorBuilder.create()
				.with("gps_capability")
				.with("antenna_capability")
				.with("internet-connection")
				.with("drug-being-administered")
				.build();
	
		Agent agent = AgentBuilder.create()
			.withQualityWeight("precision", 3)
			.withQualityWeight("responseTime", 1)
			.withContext(ctx)
			.build();
		
		List<Goal> query = RepoQueryBuilder.create()
			.queryFor("ProvideHealthSupport")
			.build();
		
		
		/*
		 * ProvideAutomatedLifeSupport
		 * EnactTreatment
		 * ProvideSelfDiagnosedEmergenciesSupport
		 * ProvideHealthSupport
		 */
		
		
		
		context = launcher.executeGoal(agent, ctx, query);
		
		//context1 = FrameworkUtil.getBundle(NotifyEmergencyMedicalServices.class).getBundleContext();
		/*
		Bundle bundle = context.getBundle("notifyEmergencyMedicalServices.NotifyEmergencyMedicalServices.class");
		try {
			bundle.start();
		} catch (BundleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*
		reference = context1.getServiceReference(NotifyEmergencyMedicalServices.class.getName());
		Object server = (Object) context1.getService(reference);
		Method method = null;
		try {
			method = server.getClass().getMethod("notifyEmergency");
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			method.invoke(server);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		context1 = context.getBundle(NotifyEmergencyMedicalServices.class.getName()).getBundleContext();
		//framework.getBundleContext();
		ServiceReference<?> ref = context1.getServiceReference(NotifyEmergencyMedicalServices.class.getName());
		NotifyEmergencyMedicalServices support =  (NotifyEmergencyMedicalServices) context.getService(ref);
		//NotifyEmergencyMedicalServices support =  (NotifyEmergencyMedicalServices) obj;
		support.notifyEmergency();
*/
		// S1
		/*
		System.out.println("--------- Starting test case S1 ---------");
		
		launcher.addScenarioResource("antenna_triangulation");
		launcher.addScenarioResource("protocol_get_fuel_level_and_mileage");
		launcher.addScenarioResource("storage");
		launcher.addScenarioResource("sound");
		*/
		/*
		launcher.executeGoal("unb.tg.osgi.g0");
		//launcher.getTimer().split("Study case 1");
		
		pressAnyKeyToContinue();
		
		launcher.addScenarioResource("gps_capability");
		launcher.removeScenarioResource("antenna_triangulation");
		*/
		pressAnyKeyToContinue();
		
                /*		
		// S2
		System.out.println("--------- Starting test case S2 ---------");
		launcher.cleanScenario();
		launcher.addScenarioResource("gps_capability");
		launcher.addScenarioResource("protocol_on_board_computer_get_distante_to_empty");
		launcher.addScenarioResource("internet_connection");
		launcher.addScenarioResource("synthesized_voice");
		
		launcher.setGoal("unb.tg.osgi.g0");
		launcher.getTimer().split("Study case 2");
		
		pressAnyKeyToContinue();
		
		// S3
		System.out.println("--------- Starting test case S3 ---------");
		launcher.cleanScenario();
		launcher.addScenarioResource("gps_capability");
		launcher.addScenarioResource("internet_connection");
		launcher.addScenarioResource("synthesized_voice");
		
		launcher.setGoal("unb.tg.osgi.g0");
		launcher.getTimer().split("Study case 3");
			
		pressAnyKeyToContinue();

		// S4
		System.out.println("--------- Starting test case S4 ---------");
		launcher.cleanScenario();
		launcher.addScenarioResource("gps_capability");
		launcher.addScenarioResource("protocol_on_board_computer_get_distante_to_empty");
		launcher.addScenarioResource("storage");
		launcher.addScenarioResource("visible_graphical_interface");
		
		launcher.setGoal("unb.tg.osgi.g0");
		launcher.getTimer().split("Study case 4");
		
		pressAnyKeyToContinue();
		
		// S5
		System.out.println("--------- Starting test case S5 ---------");
		launcher.cleanScenario();
		launcher.addScenarioResource("gps_capability");
		launcher.addScenarioResource("protocol_on_board_computer_get_distante_to_empty");
		launcher.addScenarioResource("internet_connection");
		launcher.addScenarioResource("interface_navigation_system");
		
		launcher.setGoal("unb.tg.osgi.g0");
		launcher.getTimer().split("Study case 5");
		
		pressAnyKeyToContinue();
		
		// S6
		System.out.println("--------- Starting test case S6 ---------");
		launcher.cleanScenario();
		launcher.addScenarioResource("protocol_on_board_computer_get_distante_to_empty");
		launcher.addScenarioResource("storage");
		launcher.addScenarioResource("synthesized_voice");
		
		launcher.setGoal("unb.tg.osgi.g0");
		launcher.getTimer().split("Study case 6");
		
		pressAnyKeyToContinue();
		
		// S7
		System.out.println("--------- Starting test case S7 ---------");
		launcher.cleanScenario();
		launcher.addScenarioResource("gps_capability");
		launcher.addScenarioResource("protocol_on_board_computer_get_distante_to_empty");
		launcher.addScenarioResource("interface_navigation_system");
		
		launcher.setGoal("unb.tg.osgi.g0");
		launcher.getTimer().split("Study case 7");
		
		pressAnyKeyToContinue();

		
		launcher.cleanScenario();
		launcher.shutdown();
		launcher.getTimer().split("Shutdown");
		System.out.println("End of execution!");
		launcher.getTimer().finish();
		
		launcher.printTimerResults();
		
*/
	}

}
