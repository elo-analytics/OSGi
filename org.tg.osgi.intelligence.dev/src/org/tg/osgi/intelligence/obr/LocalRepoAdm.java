package org.tg.osgi.intelligence.obr;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

public class LocalRepoAdm {
	
	private static String[] jars = null;
	public static final String PLUGINDIR = "C:/Users/jpaul/Downloads/Programs/Eclipse/Equinox/plugins";
	public static final String USERDIR = PLUGINDIR + "/user";

	public String[] getJARs() {
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
	
	public String search4BundleLocally(String name) {
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
	
	public Boolean checkBundleActive(Bundle bundle) {
		if ((bundle != null) && 
				((bundle.getState() == Bundle.ACTIVE))) {
			//System.out.println("Bundle " + bundle.getSymbolicName() +" already ACTIVE!");
			return true;
		}
		return false;
	}
	
	public Boolean checkBundleResolved(Bundle bundle) {
		if ((bundle != null) && 
				(bundle.getState() == Bundle.RESOLVED)) {				//Verifica se esta resolved
			//System.out.println("Bundle " + bundle.getSymbolicName() +" already installed!");
			return true;
		}
		return false;
	}
	
	public Boolean checkBundleInstalled(Bundle bundle) {
		if ((bundle != null) && 
				((bundle.getState() & (Bundle.ACTIVE | Bundle.INSTALLED | Bundle.RESOLVED)) != 0x0)) {				//Verifica se esta ativo ou installed ou resolved
			//System.out.println("Bundle " + bundle.getSymbolicName() +" already installed!");
			return true;
		}
		return false;
	}
	
	public Bundle installLocalBundle(String name, BundleContext context) {
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
}
