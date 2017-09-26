package org.tg.osgi.intelligence.dev;

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
		//Declarative Service
		launcher.start("org.equinoxosgi.toastDS.dev.gps");
		launcher.start("org.equinoxosgi.toastDS.dev.airbag");
		launcher.start("org.equinoxosgi.toastDS.client.emergency");
		Thread.sleep(20000);
		launcher.stop("org.equinoxosgi.toastDS.dev.gps");
		launcher.stop("org.equinoxosgi.toastDS.dev.airbag");
		launcher.stop("org.equinoxosgi.toastDS.client.emergency");
		
		//launcher.start("org.equinoxosgi.toastDS.client.emergency");
		
		//launcher.getRepoAdm().listRepositories();
		//System.out.println("---------------------------");
		//launcher.getRepoAdm().printListResources(null);
		//System.out.println("---------------------------");
		//launcher.start("org.apache.felix.webconsole");
		//launcher.start("com.packtpub.felix.bookshelf-inventory-api");
		//launcher.start("com.packtpub.felix.bookshelf-inventory-impl-mock");
		//launcher.getRepoAdm().deployResource("(symbolicname=org.apache.felix.webconsole)(version=1.5.0))", 0x00010);		//start bundle!
		
		//launcher.shutdown();
		System.out.println("End of execution!");

	}

}
