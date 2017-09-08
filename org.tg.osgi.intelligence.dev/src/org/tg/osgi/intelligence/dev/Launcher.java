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
	public static final int START = 0x00010;
	public static final String USERDIR = PLUGINDIR + "/user";
	private static String[] jars = null;
	//private static String[] libs = null;
	private Framework framework = null;
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

		framework = frameworkFactory.newFramework(config);

		try {
			framework.start();
		} catch (BundleException e) {
			e.printStackTrace();
		}
		
		System.out.println("OSGi Framework successfully started!");

		context = framework.getBundleContext();
		
		// framework bundles
		start("org.eclipse.osgi.services");
		start("org.eclipse.osgi.util");
		start("org.eclipse.equinox.common");
		start("org.eclipse.equinox.registry");
		start("org.eclipse.equinox.preferences");
		start("org.eclipse.equinox.app");
		start("org.eclipse.core.jobs");
		start("org.eclipse.equinox.util");
		start("org.eclipse.equinox.ds");
		start("org.eclipse.core.contenttype");
		start("org.eclipse.core.runtime");
		start("org.eclipse.equinox.security");
		start("org.eclipse.equinox.event");
		start("org.apache.felix.bundlerepository");		//Adiciona o OBR
		//start("org.eclipse.core.runtime_3.12.0.v20160606-1342.jar");	//runtime do eclipse
		
		// default shell
		start("org.apache.felix.gogo.runtime");	//runtime do felix
		start("org.apache.felix.gogo.command");
		start("org.apache.felix.gogo.shell");
		start("org.eclipse.equinox.console");
		
		
		
		//Repository 
		repoAdmin = new RepositoryAdm(context);
		repoAdmin.addRepository("http://felix.apache.org/obr/releases.xml");
		//repoAdmin.addRepository("http://sling.apache.org/obr/sling.xml");
		

		//System.out.println("Fim do construtor!");
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
	
	protected String search4BundleLocally(String name) {
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
		}
		return found;
	}
	
	protected Bundle search4BundleRemotelly(String name, int shouldStart) {
		String filterExpr = null;
		Boolean deployed = false;
		
		System.out.println("Looking for bundle " + name + " remotelly...");
		
		if (name.endsWith(".jar")) {
			System.out.println("ERROR: Bad Bundle Symbolic name format!");
			return null;
		}
		
		filterExpr = String.format("(symbolicname=" + name + ")");
		deployed = repoAdmin.deployResource(filterExpr, shouldStart);
		if (deployed) {		//Achou o bundle e instalou com sucesso!
			return getBundleBySymbolicName(name);
		}
		

		
		return null;
	}
	
	protected Bundle getBundleBySymbolicName(String name) {
		for (Bundle bundle : context.getBundles()) {
			if (bundle.getSymbolicName().equals(name)) {
				return bundle;
			}
		}
		return null;
	}
	
	protected Boolean checkBundleActive(Bundle bundle) {
		if ((bundle != null) && 
				((bundle.getState() == Bundle.ACTIVE))) {
			//System.out.println("Bundle " + bundle.getSymbolicName() +" already ACTIVE!");
			return true;
		}
		return false;
	}
	
	protected Boolean checkBundleInstalled(Bundle bundle) {
		if ((bundle != null) && 
				((bundle.getState() & (Bundle.ACTIVE | Bundle.INSTALLED | Bundle.RESOLVED)) != 0x0)) {				//Verifica se esta ativo ou installed ou resolved
			//System.out.println("Bundle " + bundle.getSymbolicName() +" already installed!");
			return true;
		}
		return false;
	}
	
	protected Bundle installLocalBundle(String name) {
		Bundle newBundle = context.getBundle(name);
		
		if (checkBundleInstalled(newBundle)) {				//Verifica se esta ativo ou installed ou resolved
			return newBundle;
		}
		try {
			newBundle = context.installBundle(name);
			return newBundle;
		} catch (BundleException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected Bundle install(String name, int shouldStart) {
		Bundle newBundle = null;
		String jarPath = search4BundleLocally(name);
		
		if (jarPath != null) {
			newBundle = installLocalBundle(jarPath);
			if (newBundle == null) {
				System.out.println("ERROR: Couldn't install local bundle " + name);
				return null;
			}
			System.out.println("Local Bundle " + name +" installed successfully!");
			return newBundle;
		}
		else {	//Bundle not found locally
			newBundle = search4BundleRemotelly(name, shouldStart);
			if (newBundle == null) return null;
			return newBundle;
		}
	}
	
	protected Boolean startBundle(Bundle newBundle) {
		if (newBundle != null) {				//Bundle no framework
			if (checkBundleActive(newBundle))	//Bundle ja esta ativo
				return true;
			else {								//bundle instalado, mas nao ativo
				try {
					newBundle.start();
					System.out.println("Bundle " + newBundle.getSymbolicName() + " started successfully!");
					return true;
				} catch (BundleException e) {
					//pegar dependencias caso nao consiga
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	protected Bundle start(String name) {
		Bundle newBundle = getBundleBySymbolicName(name);
		
		if (newBundle != null) {	//Bundle no framework
			if (startBundle(newBundle)){
				return newBundle;
			}
			System.out.println("ERROR: Couldn't start bundle " + name);
			return null;
		}
		
		//Bundle nao esta no framework
		newBundle = install(name, START);
		if (!checkBundleActive(newBundle)) {
			if(startBundle(newBundle))
				return newBundle;
		}
		
		return newBundle;
	}
	
	protected void stop(String name) {
		String jarPath = search4BundleLocally(name);
		Bundle newBundle = context.getBundle(jarPath);
		
		//verifica se esta ativo
		if (!checkBundleActive(newBundle)) {
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
		
		if(newBundle == null) {
			System.out.println("ERROR: Bundle " + name  + " not found. Couldn't unninstall!");
		}
		
		//verifica se esta ativo
		if (!checkBundleInstalled(newBundle)) {
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
	
	protected void shutdown() throws BundleException {
		try {
			framework.stop();
			System.out.println("OSGi Framework successfully stopped!");
		} catch (BundleException e) {
			e.printStackTrace();
		}
	}
}
