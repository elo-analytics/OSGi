package provideHealthSupportImpl;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;

import provideAutomatedLifeSupport.ProvideAutomatedLifeSupport;
import provideHealthSupport.ProvideHealthSupport;
import provideSelfDiagnosedEmergenciesSupport.ProvideSelfDiagnosedEmergenciesSupport;

public class ProvideHealthSupportImpl implements ProvideHealthSupport, BundleActivator {
	
	private ProvideAutomatedLifeSupport automated;
	private ProvideSelfDiagnosedEmergenciesSupport selfdiagnosed;
	private String pick;
	ServiceRegistration registration;
	
	void setProvideAutomatedLifeSupport (ProvideAutomatedLifeSupport automated) {
		this.automated = automated;
	}
	
	void setProvideSelfDiagnosedEmergenciesSupport (ProvideSelfDiagnosedEmergenciesSupport selfdiagnosed) {
		this.selfdiagnosed = selfdiagnosed;
	}

	protected void activate(ComponentContext context) {
		System.out.println("\n\n--------------------------------\n"
				+ 	"ProvideHealthSupport Module Activated\n"
				+ 	"--------------------------------\n\n");
		System.out.println(automated.ProvideAutomatedLifeSupport());
		System.out.println(selfdiagnosed.provide());
		
		this.pick = getPick();
		
		if(this.pick=="vitalParamsMsg") {
			automated.ProvideAutomatedLifeSupport();
		}
		else if(pick=="buttonMsg") {
			selfdiagnosed.provide();
		}

	    
	}
	
	protected void deactivate(ComponentContext context) {
		System.out.println("ProvideHealthSupport Module Deactivated");
	}

	@Override
	public String ProvideHealthSupport() {
		return "Providing Health Support...\n" +
				"		" + automated.ProvideAutomatedLifeSupport() +
				"\n		" + selfdiagnosed.provide();
	}

	public String getPick() {
		Double rand = Math.random();
		if (rand > 0.25) return "vitalParamsMsg";
		return "buttonMsg";
	}

	@Override
	public void start(BundleContext arg0) throws Exception {
		Hashtable<String,Object> props = new Hashtable<String, Object>();
	    registration = arg0.registerService(ProvideHealthSupport.class.getName(),
	          new ProvideHealthSupportImpl(),props);
	      
	      
		System.out.println("\n\n--------------------------------\n"
				+ 	"ProvideHealthSupport Module Activated\n"
				+ 	"--------------------------------\n\n");
		System.out.println(automated.ProvideAutomatedLifeSupport());
		System.out.println(selfdiagnosed.provide());
		
		this.pick = getPick();
		
		if(this.pick=="vitalParamsMsg") {
			automated.ProvideAutomatedLifeSupport();
		}
		else if(pick=="buttonMsg") {
			selfdiagnosed.provide();
		}
		
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
