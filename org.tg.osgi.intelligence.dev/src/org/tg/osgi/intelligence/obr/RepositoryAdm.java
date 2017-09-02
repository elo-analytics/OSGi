package org.tg.osgi.intelligence.obr;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.felix.bundlerepository.Repository;
import org.apache.felix.bundlerepository.RepositoryAdmin;
import org.apache.felix.bundlerepository.impl.RepositoryAdminImpl;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

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
			System.out.println("ERRO: Não foi possivel adicionar repositorio.");
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
}
