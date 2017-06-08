package org.tg.osgi.intelligence.dev;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		Launcher launcher = new Launcher();
		
		launcher.start("com.javaworld.sample.HelloService_1.0.0.201706041720.jar");
		launcher.start("com.javaworld.sample.HelloWorld1_1.0.0.201706041720.jar");
		launcher.install("com.javaworld.sample.HelloWorld1_1.0.0.201706041720.jar");
		launcher.stop("com.javaworld.sample.HelloWorld1_1.0.0.201706041720.jar");
		launcher.uninstall("com.javaworld.sample.HelloService_1.0.0.201706041720.jar");
		
		launcher.start("org.equinoxosgi.toast.dev.gps_1.0.0.201706072222.jar");
	    launcher.start("org.equinoxosgi.toast.dev.airbag_1.0.0.201706072222.jar");
		launcher.start("org.equinoxosgi.toast.client.emergency_1.0.0.201706072222.jar");
		Thread.sleep(20000);
		launcher.uninstall("org.equinoxosgi.toast.client.emergency_1.0.0.201706072222.jar");
		launcher.stop("org.equinoxosgi.toast.dev.airbag_1.0.0.201706072222.jar");
		launcher.stop("org.equinoxosgi.toast.dev.gps_1.0.0.201706072222.jar");
		
		System.out.println("End of execution!");

	}

}
