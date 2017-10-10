package org.tg.osgi.intelligence.dev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.osgi.framework.BundleException;

public class Main {

	public static void main(String[] args) throws InterruptedException, BundleException {
		
		Launcher launcher = new Launcher();
		
		/*
		launcher.start("com.javaworld.sample.HelloService_1.0.0.201706041720.jar");
		launcher.start("com.javaworld.sample.HelloWorld1_1.0.0.201706041720.jar");
		//launcher.install("com.javaworld.sample.HelloWorld1_1.0.0.201706041720.jar");
		launcher.stop("com.javaworld.sample.HelloWorld1_1.0.0.201706041720.jar");
		launcher.stop("com.javaworld.sample.HelloService_1.0.0.201706041720.jar");
		Thread.sleep(20000);
		
		//Service Tracker
		launcher.start("org.equinoxosgi.toast.dev.gps_1.0.0.201706072222.jar");
		launcher.install("org.equinoxosgi.toast.dev.airbag_1.0.0.201706072222.jar");
		launcher.start("org.equinoxosgi.toast.client.emergency_1.0.0.201706072222.jar");
	    launcher.start("org.equinoxosgi.toast.dev.airbag_1.0.0.201706072222.jar");
		Thread.sleep(40000);
		launcher.uninstall("org.equinoxosgi.toast.client.emergency_1.0.0.201706072222.jar");
		launcher.stop("org.equinoxosgi.toast.dev.airbag_1.0.0.201706072222.jar");
		launcher.stop("org.equinoxosgi.toast.dev.gps_1.0.0.201706072222.jar");
		*/
		/*
		//Declarative Service
		launcher.start("org.equinoxosgi.toastDS.dev.gps");
		launcher.start("org.equinoxosgi.toastDS.dev.airbag");
		launcher.start("org.equinoxosgi.toastDS.client.emergency");
		Thread.sleep(20000);
		launcher.stop("org.equinoxosgi.toastDS.dev.gps");
		launcher.stop("org.equinoxosgi.toastDS.dev.airbag");
		launcher.stop("org.equinoxosgi.toastDS.client.emergency");
		*/
		//launcher.start("org.equinoxosgi.toastDS.client.emergency");
		
		//launcher.getRepoAdm().listRepositories();
		//System.out.println("---------------------------");
		//launcher.getRepoAdm().printListResources(null);
		//System.out.println("---------------------------");
		//launcher.start("org.apache.felix.webconsole");
		//launcher.start("com.packtpub.felix.bookshelf-inventory-api");
		//launcher.start("com.packtpub.felix.bookshelf-inventory-impl-mock");
		//launcher.getRepoAdm().deployResource("(symbolicname=org.apache.felix.webconsole)(version=1.5.0))", 0x00010);	
		
		//start bundle!
		
		System.out.println("-------------------------");
		launcher.start("unb.tg.osgi.g5.impl.p15.api");
		launcher.start("unb.tg.osgi.g5.impl.p15.impl.p17");
		launcher.start("unb.tg.osgi.g5.impl.p15.impl.p16");
		launcher.start("unb.tg.osgi.g5.api");
		launcher.start("unb.tg.osgi.g5.impl.p14");
		launcher.start("unb.tg.osgi.g5.impl.p18");
		launcher.start("unb.tg.osgi.g5.impl.p15");
		launcher.start("unb.tg.osgi.g4.impl.p13.api");
		launcher.start("unb.tg.osgi.g4.impl.p13.impl");
		launcher.start("unb.tg.osgi.g4.impl.p12.api");
		launcher.start("unb.tg.osgi.g4.impl.p12.impl");
		launcher.start("unb.tg.osgi.g4.api");
		launcher.start("unb.tg.osgi.g4.impl");
		launcher.start("unb.tg.osgi.g3.api");
		launcher.start("unb.tg.osgi.g3.impl.p11");
		launcher.start("unb.tg.osgi.g3.impl.p10");
		launcher.start("unb.tg.osgi.g2.impl.p4.p6.api");
		launcher.start("unb.tg.osgi.g2.impl.p4.p6.impl");
		launcher.start("unb.tg.osgi.g2.impl.p4.p5.api");
		launcher.start("unb.tg.osgi.g2.impl.p4.p5.impl");
		launcher.start("unb.tg.osgi.g2.impl.p7.p8.api");
		launcher.start("unb.tg.osgi.g2.impl.p7.p8.impl");
		launcher.start("unb.tg.osgi.g2.api");
		launcher.start("unb.tg.osgi.g2.impl.p3");
		launcher.start("unb.tg.osgi.g2.impl.p7");
		launcher.start("unb.tg.osgi.g2.impl.p4");
		launcher.start("unb.tg.osgi.g1.api");
		launcher.start("unb.tg.osgi.g1.impl.p2");
		launcher.start("unb.tg.osgi.g1.impl.p1");
		launcher.start("unb.tg.osgi.g0");
		System.out.println("-------------------------");
		
		//launcher.planejador("G0");
		launcher.executeGoal("Filling Station", Arrays.asList("antenna_triangulation", 
																"protocol_get_fuel_level_and_mileage",
																"storage",
																"sound"));
		
		//stop bundle
		//launcher.stop("unb.tg.osgi.fillingStation");
		//launcher.shutdown();
		System.out.println("End of execution!");

	}

}
