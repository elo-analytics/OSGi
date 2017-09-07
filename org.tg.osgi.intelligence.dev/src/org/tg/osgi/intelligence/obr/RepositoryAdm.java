package org.tg.osgi.intelligence.obr;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.felix.bundlerepository.Repository;
import org.apache.felix.bundlerepository.RepositoryAdmin;
import org.apache.felix.bundlerepository.Resolver;
import org.apache.felix.bundlerepository.Resource;
import org.apache.felix.bundlerepository.Reason;
import org.apache.felix.bundlerepository.impl.RepositoryAdminImpl;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;

public class RepositoryAdm {
	
	protected RepositoryAdmin repoAdmin = null;
	
	public RepositoryAdm (BundleContext context) {
		//check if context exists?
		repoAdmin = new RepositoryAdminImpl(context, null);
        
		if (repoAdmin != null)
			System.out.println("Criado RepoAdmin!");
		else
			System.out.println("ERRO: Problemas na criacao do RepositoryAdmin");
        	
	}

	public Repository addRepository(String repositoryStr) {
		Repository repository = null;
		//URL repositoryURL = null;
		
		if (repoAdmin == null){
			System.out.println("ERROR: Repository Admin Unavailable!");
			return null;
		}
		
		
		/*
		try {
			repositoryURL = new URL(repositoryStr);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: Couldn't create URL based on repository given address!");
			e1.printStackTrace();
		}

		boolean duplicate = false;
        for (Repository repo : repoAdmin.listRepositories())
        {
            try {
				if (repositoryURL.toURI().toString().equalsIgnoreCase(repo.getURI())){
				    duplicate = true;
				    break;
				}
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    	
        }
        
        if (duplicate == true){
        	System.out.println("Repository already added!");
        	return null;
        }
        */
        
		try {
			repository = repoAdmin.addRepository(repositoryStr);
			System.out.println("Repository " + repositoryStr + " added!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERRO: N�o foi possivel adicionar repositorio.");
			e.printStackTrace();
		}
		
		return repository;
	}
	
	public Repository[] listRepositories(){
		Repository[] repos;
		
		repos = repoAdmin.listRepositories();
		
		if (repos == null){
			System.out.println("ERROR: It wasn't possible to retrieve list of repos!");
			return null;
		}
		
		System.out.println("Repositories:");
		for (Repository r: repos) {
			System.out.println(r.getURI());
		}
		
		return repos;
	}
	
	public boolean removeRepository(String toBeRemoved){
		boolean removed;
		removed = repoAdmin.removeRepository(toBeRemoved);
		if (removed) {
			System.out.println("Repository "+ toBeRemoved + " removed");
			return removed;
		}
		System.out.println("ERROR: Couldn't remove repository");
		return false;
	}
	
	public Resource[] getListResources(String filterExpr) {
		Resource[] resources = null;
		
		try {
			resources = repoAdmin.discoverResources(filterExpr);
		} catch (InvalidSyntaxException e) {
			System.out.println("ERROR: Cannot list resources.");
			//e.printStackTrace();
			return null;
		}
		
		return (resources.length == 0) ? null : resources;
	}
	
	public void printListResources(String filterExpr) {
		Resource[] resources = getListResources(filterExpr);
		
		if (resources == null) {
			System.out.println("Couldn't find any bundles matching: " + filterExpr);
			return;
		}
		
		System.out.println("Bundles found:");
		for (Resource res : resources) {
			System.out.println(res.getPresentationName() + " " + res.getVersion());
		}
	}
	
	public Reason[] deploy(Resource resource, int opt) {		//deve retornar um bundle!!	
		Resolver resolver = this.repoAdmin.resolver();
		resolver.add(resource);
		int resolveAttempt = 5; 
		
        while (resolveAttempt-- > 0) 
        {
        	try { 
        		if (resolver.resolve(opt)) 
        		{ 
        			resolver.deploy(opt);
        			System.out.println("Bundle " + resource.getSymbolicName() + "-"
        					+ resource.getVersion() + ".jar"
        					+ " downloaded from remote repository and sucessfully started!");
        			return null;
        		} else { 
        			Reason[] reqs = resolver.getUnsatisfiedRequirements(); 
        			return reqs;	//Resolve sem sucesso, mas completo.
        		} 
        	} catch (IllegalStateException e) { 
        		// If resolve unsuccessful, try again 
        		if (resolveAttempt == 0) 
        			e.printStackTrace(); 
        	}
        }
        return null;
	}
	
	
	/*
	 * Method that gets the list of requirements and tries do get them from the repository
	 * @param filterExpr 
	 * @param opt
	 * */
	public void deployResource(String filterExpr, int opt) {	//deve retornar o bundle!!!!
		Resource[] resource = null;
		Reason[] reasons = null;
		
		
		resource = getListResources(filterExpr);
		
		if (resource == null) {
			System.out.println("ERROR: Couldn't find bundle that matches " +
						         				filterExpr + " in any known repository!");
			return;
		}
			
		reasons = deploy(selectNewestVersion(resource), opt);
		if (reasons == null) return;	//deploy successfull
		
		//trying to deploy dependencies recursively... Check if needed!
		for (int i = 0; i < reasons.length; i++) { 		
            System.out.println("Trying to resolve bundle: " + reasons[i]); 
            deployResource(reasons[i].getRequirement().getFilter(), opt);
        }
	}
	
	private Resource selectNewestVersion(Resource[] resources) {
        int index = -1;
        Version version = null;
        for (int i = 0; (resources != null) && (i < resources.length); i++) {
            if (i == 0) {
                index = 0;
                version = resources[i].getVersion();
            } else {
                Version ver = resources[i].getVersion();
                if (ver.compareTo(version) > 0) {
                    index = i;
                    version = ver;
                }
            }
        }

        return (index < 0) ? null : resources[index];
    }
}
