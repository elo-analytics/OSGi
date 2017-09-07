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
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;
import org.tg.osgi.intelligence.obr.RepositoryAdm;
import org.apache.felix.bundlerepository.*;


public class Launcher {
	
	public static final String PLUGINDIR = "C:/Users/jpaul/Downloads/Programs/Eclipse/Equinox/plugins";
	public static final String USERDIR = PLUGINDIR + "/user";
	private static String[] jars = null;
	private static String[] libs = null;
	private Framework framework;
	private RepositoryAdm repoAdmin = null;
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
		
		//Criar o RepositoryAdm sem problemas
		config.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA, "org.apache.felix.bundlerepository; version=2.0.10");

		Framework framework = frameworkFactory.newFramework(config);

		try {
			framework.start();
		} catch (BundleException e) {
			e.printStackTrace();
		}
		
		System.out.println("OSGi iniciado!");

		context = framework.getBundleContext();
		
		// framework bundles
		start("org.eclipse.osgi.services"); //_3.5.100.v20160504-1419.jar");
		start("org.eclipse.osgi.util");	//_3.3.100.v20150423-1351.jar");
		start("org.eclipse.equinox.common");	//_3.8.0.v20160509-1230.jar");
		start("org.eclipse.equinox.registry");	//_3.6.100.v20160223-2218.jar");
		start("org.eclipse.equinox.preferences");	//_3.6.1.v20160815-1406.jar");
		start("org.eclipse.equinox.app");	//_1.3.400.v20150715-1528.jar");
		start("org.eclipse.core.jobs");	//_3.8.0.v20160509-0411.jar");
		start("org.eclipse.equinox.util");	//_1.0.500.v20130404-1337.jar");
		start("org.eclipse.equinox.ds");	//_1.4.400.v20160226-2036.jar");
		start("org.eclipse.core.contenttype");	//_3.5.100.v20160418-1621.jar");
		start("org.eclipse.core.runtime");	//_3.12.0.v20160606-1342.jar");
		start("org.eclipse.equinox.security");	//_1.2.200.v20150715-1528.jar");
		start("org.eclipse.equinox.event");	//_1.3.200.v20160324-1850.jar");
		start("org.apache.felix.bundlerepository");	//-2.0.10.jar");			//Adiciona o OBR
		//start("org.eclipse.core.runtime_3.12.0.v20160606-1342.jar");
		
		// default shell
		start("org.apache.felix.gogo.runtime");	//_0.10.0.v201209301036.jar");
		start("org.apache.felix.gogo.command");	//_0.10.0.v201209301215.jar");
		start("org.apache.felix.gogo.shell");	//_0.10.0.v201212101605.jar");
		start("org.eclipse.equinox.console");	//_1.1.200.v20150929-1405.jar");
		
		
		
		//Repository 
		repoAdmin = new RepositoryAdm(context);
		repoAdmin.addRepository("http://felix.apache.org/obr/releases.xml");
		//repoAdmin.addRepository("http://sling.apache.org/obr/sling.xml");
		

		System.out.println("Fim do construtor!");
	}
	
	RepositoryAdm getRepoAdm(){
		return this.repoAdmin;
	}

	private String[] getJARs() {
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
		if (name.endsWith(".jar")) {		//Veio o nome completo do bundle, so' falta formatar
			return String.format("file:" + PLUGINDIR + "/%s", name);
		}
		for (String jar : getJARs()) {			//ideal seria buscar a versao mais recente (apesar de menos performatico)
			if (jar.startsWith(name + "_") || jar.startsWith(name + "-")) {
				found = String.format("file:" + PLUGINDIR + "/%s", jar);
				break;
			}
		}
		if (found == null) {
			System.out.println("JAR for " + name + " not found in local repository");
			//throw new RuntimeException(String.format("JAR for %s not found in local repository", name));
		}
		return found;
	}
	
	protected Bundle installLocalBundle(String name) {
		Bundle newBundle = context.getBundle(name);		//pathToFile(name));		//atualmente nao estou pesquisando o bundle
		if ((newBundle != null) && 
				((newBundle.getState() & (Bundle.ACTIVE | Bundle.INSTALLED | Bundle.RESOLVED)) != 0x0)) {				//Verifica se esta ativo ou installed ou resolved
			System.out.println("Bundle " + name +" already installed!");
			return newBundle;
		}
		try {
			newBundle = context.installBundle(name);
			//System.out.println("Bundle " + name +" installed!");
			return newBundle;
		} catch (BundleException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected Bundle install(String name) {
		Bundle newBundle = null;
		String jarPath = search4Bundle(name);
		
		if (jarPath != null) {
			newBundle = installLocalBundle(jarPath);
			System.out.println("Bundle " + name +" installed!");
			return newBundle;
		}
		else {	//Bundle not found locally
			//repoAdmin
		}
		
		return null;
	}
	
	protected Boolean checkBundleActive(Bundle bundle) {
		if ((bundle != null) && 
				((bundle.getState() == Bundle.ACTIVE))) {
			System.out.println("Bundle " + bundle.getSymbolicName() +" already ACTIVE!");
			return true;
		}
		return false;
	}
	
	protected Bundle start(String name) {
		String jarPath = search4Bundle(name);
		Bundle newBundle = context.getBundle(jarPath);		//pathToFile(name));		//a funcao pathtofile esta quebrada
																	//vai vir agora so o symbolic name
		if(checkBundleActive(newBundle))
			return newBundle;

		newBundle = installLocalBundle(jarPath);
		if (newBundle != null) {
			System.out.println("Bundle " + name +" installed!");
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
		String jarPath = search4Bundle(name);
		Bundle newBundle = context.getBundle(jarPath);
		
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
		Bundle newBundle = getBundleBySymbolicName(name);
		
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
	
	protected Bundle getBundleBySymbolicName(String name) {
		for (Bundle bundle : context.getBundles()) {
			if (bundle.getSymbolicName().equals(name)) {
				return bundle;
			}
		}
		return null;
	}
	
	protected void shutdown() throws BundleException {
		try {
			framework.stop();
		} catch (BundleException e) {
			e.printStackTrace();
		}
	}
}
