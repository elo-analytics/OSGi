package org.tg.osgi.intelligence.dev;

import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;
import org.osgi.framework.wiring.BundleRequirement;
import org.osgi.framework.wiring.BundleRevision;
import org.tg.osgi.intelligence.obr.LocalRepoAdm;
import org.tg.osgi.intelligence.obr.RepositoryAdm;
//import org.apache.felix.bundlerepository.*;
import org.osgi.service.resolver.Resolver;


public class Launcher {
	
	public static final int START = 0x00010;
	
	
	//private static String[] libs = null;
	private Framework framework = null;
	private RepositoryAdm repoAdmin = null;
	private LocalRepoAdm localRepo = new LocalRepoAdm();
	private BundleContext context;
	//private Planejador 

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

		//context = framework.getBundleContext();
		setContext();
		
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
		//repoAdmin.addRepository("http://felix.apache.org/obr/releases.xml");		
		repoAdmin.addRepository("file:/C:/Users/jpaul/Documents/Workspaces/localrepo/repository.xml");
		//repoAdmin.addRepository("http://sling.apache.org/obr/sling.xml");
		repoAdmin.printListResources(null);
		

		//System.out.println("Fim do construtor!");
	}
	
	RepositoryAdm getRepoAdm(){
		return this.repoAdmin;
	}
	
	public void setContext() {
		context = framework.getBundleContext();
		
		context.addBundleListener(new BundleListener(){
			@Override
			public void bundleChanged(BundleEvent event) {
				switch (event.getType()) {
				case BundleEvent.RESOLVED:
	                break;
	            case BundleEvent.INSTALLED:
	                break;
				case BundleEvent.STARTED:
	                break;
	            case BundleEvent.UNINSTALLED:
	            	System.out.println("LISTENER: Bundle " + event.getBundle().getSymbolicName() + " Uninstalled!");
	            	Bundle b = start(event.getBundle().getSymbolicName());
	            	/*
	            	if (b ==null)
	            			String = planejador.replaneja(contexto);
	            			deployResources(String)
	            			*/
	                break;
				case BundleEvent.UNRESOLVED:
					System.out.println("LISTENER: Bundle " + event.getBundle().getSymbolicName() + " Unresolved!");
					start(event.getBundle().getSymbolicName());
	                break;
				case BundleEvent.STOPPED:
					System.out.println("LISTENER: Bundle " + event.getBundle().getSymbolicName() + " Stopped!");
					start(event.getBundle().getSymbolicName());
	                break; 
				}
			}
		});
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

	protected Bundle install(String name, int shouldStart) {
		Bundle newBundle = null;
		String jarPath = localRepo.search4BundleLocally(name);
		
		if (jarPath != null) {
			newBundle = localRepo.installLocalBundle(jarPath, context);
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
	
	protected BundleRequirement[] getRequirements(Bundle bundle){
		BundleRequirement[] req = null;
		
		return req;
	}
	
	protected BundleRequirement[] getInnactiveRequirements(BundleRequirement[] allRequirements) {
		BundleRequirement[] req = null;
		
		return req;
	}
	
	protected Boolean resolveRequirements(Bundle bundle) {
		Dictionary dict = bundle.getHeaders();
		//System.out.println(dict.keys().toString());
		BundleRequirement[] allRequirements = getRequirements(bundle);
		BundleRequirement[] innactiveReqs = getInnactiveRequirements(allRequirements);
		//System.out.println(dict.get(Bundle.Import-Package));
		for (Enumeration<Object> e = dict.keys(); e.hasMoreElements();)
		       System.out.println(e.nextElement());		/*
		for (BundleRequirement req : innactiveReqs) {
			if (start(req.toString()) != null) 
				return true;
		}
		        */
		return false;
	}
	
	protected Boolean startBundle(Bundle newBundle) {
		if (newBundle != null) {				//Bundle no framework
			if (localRepo.checkBundleActive(newBundle))	//Bundle ja esta ativo
				return true;
											//bundle instalado, mas nao ativo
			try {
				newBundle.start();
				System.out.println("Bundle " + newBundle.getSymbolicName() + " started successfully!");
				return true;
			} catch (BundleException e) {
				if (e.getType() == BundleException.RESOLVE_ERROR) {
					//e.printStackTrace();	
					if (!resolveRequirements(newBundle)) {
						System.out.println("ERROR: Bundle " + newBundle.getSymbolicName() + " couldn't be resolved!");
						return false;
					}
				}
			}
		
		}
		return false;
	}
	
	protected Bundle start(String name) {
		BundleListener listener;
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
		if (!localRepo.checkBundleActive(newBundle)) {
			if(startBundle(newBundle))
				return newBundle;
		}
		
		return newBundle;
	}
	
	protected void stop(String name) {
		Bundle newBundle = getBundleBySymbolicName(name);
		
		//verifica se esta ativo
		if (!localRepo.checkBundleActive(newBundle)) {
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
		if (!localRepo.checkBundleInstalled(newBundle)) {
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
