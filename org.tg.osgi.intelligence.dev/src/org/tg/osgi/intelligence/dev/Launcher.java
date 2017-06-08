package org.tg.osgi.intelligence.dev;

import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

public class Launcher {
	
	public static final String PLUGINDIR = "C:/Users/jpaul/Downloads/Programs/Eclipse/Equinox/plugins";
	public static final String USERDIR = PLUGINDIR + "/user";
	private static String[] jars = null;
	private static String[] libs = null;
	

	private BundleContext context;

	Launcher() {

		FrameworkFactory frameworkFactory = ServiceLoader.load(FrameworkFactory.class).iterator().next();

		Map<String, String> config = new HashMap<String, String>();
		config.put("osgi.console", "");
		config.put("osgi.clean", "true");
		config.put("osgi.noShutdown", "true");
		config.put("eclipse.ignoreApp", "true");
		config.put("osgi.bundles.defaultStartLevel", "4");
		config.put("osgi.configuration.area", "./configuration");

		// automated bundles deployment
		config.put("felix.fileinstall.dir", "./dropins");
		config.put("felix.fileinstall.noInitialDelay", "true");
		config.put("felix.fileinstall.start.level", "4");

		Framework framework = frameworkFactory.newFramework(config);

		try {
			framework.start();
		} catch (BundleException e) {
			e.printStackTrace();
		}
		
		System.out.println("OSGi iniciado!");

		context = framework.getBundleContext();
		
		// framework bundles
		//start("org.eclipse.osgi.services_3.5.100.v20160504-1419.jar");
		//start("org.eclipse.osgi.util_3.3.100.v20150423-1351.jar");
		start("org.eclipse.equinox.common_3.8.0.v20160509-1230.jar");
		//start("org.eclipse.equinox.registry_3.6.100.v20160223-2218.jar");
		//start("org.eclipse.equinox.preferences_3.6.1.v20160815-1406.jar");
		//start("org.eclipse.equinox.app_1.3.400.v20150715-1528.jar");
		start("org.eclipse.core.jobs_3.8.0.v20160509-0411.jar");
		//start("org.eclipse.core.contenttype_3.5.100.v20160418-1621.jar");
		//start("org.eclipse.core.runtime_3.12.0.v20160606-1342.jar");
		//start("org.eclipse.equinox.security_1.2.200.v20150715-1528.jar");
		//start("org.eclipse.equinox.event_1.3.200.v20160324-1850.jar");*/
		//start("org.eclipse.core.runtime_3.12.0.v20160606-1342.jar");
		
		// default shell
		start("org.apache.felix.gogo.runtime_0.10.0.v201209301036.jar");
		start("org.apache.felix.gogo.command_0.10.0.v201209301215.jar");
		start("org.apache.felix.gogo.shell_0.10.0.v201212101605.jar");
		start("org.eclipse.equinox.console_1.1.200.v20150929-1405.jar");

		System.out.println("Fim do construtor!");
	}

	private String[] getJARs() {		//Considero que ja' possuo todos os pacotes que necessito prontos para instalacao
		if (jars == null) {
			List<String> jarsList = new ArrayList<String>();
			File pluginsDir = new File(PLUGINDIR);
			for (String jar : pluginsDir.list()) {
				jarsList.add(jar);
			}
			jars = jarsList.toArray(new String[jarsList.size()]);
		}
		return jars;
	}
	
	protected String search4Bundle(String name) {
		String found = null;
		if (name.endsWith(".jar")) {
			return String.format("file:" + PLUGINDIR + "/%s", name);
		}
		for (String jar : getJARs()) {
			if (jar.startsWith(name + "_") || jar.startsWith(name + "-")) {
				found = String.format("file:" + PLUGINDIR + "/%s", jar);
				break;
			}
		}
		if (found == null) {
			throw new RuntimeException(String.format("JAR for %s not found", name));
		}
		return found;
	}
	
	protected String pathToFile(String name) {
		return String.format("file:" + PLUGINDIR + "/%s", name);
	}

	protected Bundle install(String name) {
		Bundle newBundle = context.getBundle(pathToFile(name));
		//String jarPath = search4Bundle(name); 
		if ((newBundle != null) && 
				((newBundle.getState() & 0x00000026) != 0x0)) {				//Verifica se esta ativo ou installed ou resolved
			System.out.println("Bundle " + name +" already installed!");
			return newBundle;
		}
		try {
			newBundle = context.installBundle(pathToFile(name));
			System.out.println("Bundle " + name +" installed!");
			return newBundle;
		} catch (BundleException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected Bundle start(String name) {
		//String jarPath = search4Bundle(name);
		Bundle newBundle = context.getBundle(pathToFile(name));
		
		//verifica se ja esta ativo
		if ((newBundle != null) && 
				((newBundle.getState() == Bundle.ACTIVE))) {
			System.out.println("Bundle " + name +" already ACTIVE!");
			return newBundle;
		}
		
		
		newBundle = install(name);
		if (newBundle != null) {
			try {
				newBundle.start();
			} catch (BundleException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Bundle " + name + " started!");
		return newBundle;
	}
	
	protected void stop(String name) {
		Bundle newBundle = context.getBundle(pathToFile(name));
		
		//verifica se esta ativo
		if ((newBundle == null) || 
				((newBundle.getState() != Bundle.ACTIVE))) {
			System.out.println("Bundle " + name +" not ACTIVE!");
			return;
		}
		try {
			newBundle.stop();
			System.out.println("Bundle " + name + " stopped!");
		} catch (BundleException e) {
			e.printStackTrace();
		}
	}
	
	protected void uninstall(String name) {
		Bundle newBundle = context.getBundle(pathToFile(name));
		
		//verifica se esta ativo
		if ((newBundle == null) || 
				((newBundle.getState() == Bundle.UNINSTALLED))) {
			System.out.println("Bundle " + name +" not Installed!");
			return;
		}
		try {
			newBundle.uninstall();
			System.out.println("Bundle " + name + " uninstalled!");
		} catch (BundleException e) {
			e.printStackTrace();
		}
	}
}
