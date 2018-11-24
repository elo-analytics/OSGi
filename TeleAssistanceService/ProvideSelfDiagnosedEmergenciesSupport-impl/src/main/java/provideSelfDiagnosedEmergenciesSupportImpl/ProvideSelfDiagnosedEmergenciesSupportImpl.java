package provideSelfDiagnosedEmergenciesSupportImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import notifyEmergencyMedicalServices.NotifyEmergencyMedicalServices;
import provideSelfDiagnosedEmergenciesSupport.ProvideSelfDiagnosedEmergenciesSupport;
import pushButton.PushButton;

public class ProvideSelfDiagnosedEmergenciesSupportImpl implements ProvideSelfDiagnosedEmergenciesSupport {
	
	private PushButton pButton;
	private NotifyEmergencyMedicalServices notification;
	
	void setPushButton (PushButton pButton) {
		System.out.println("Binding pushButton module");
		this.pButton = pButton;
		
	}
	
	void setNotifyEmergencyMedicalServices (NotifyEmergencyMedicalServices notification) {
		System.out.println("Binding setNotifyEmergency module");
		this.notification = notification;
	}
	
	protected void activate() {
		System.out.println("Provide Self Diagnosed Emergencies Support Module Activated");
		System.out.println(Arrays.asList(notification.notifyEmergencyRetry()));
		/*
		HashMap<String, HashMap<String, Integer>> results = new HashMap<String, HashMap<String, Integer>>();
				
		
		
		results.putAll(pButton.push());
		results.putAll(notification.notifyEmergencyNoAdapt());
		
		System.out.println(Arrays.asList(results));
		*/
	}
	
	protected void deactivate() {
		System.out.println("Provide Self Diagnosed Emergencies Support Module Deactivated");
	}

	public HashMap<String, HashMap<String, Integer>> provide(String strategy) {
		HashMap<String, HashMap<String, Integer>> results = new HashMap<String, HashMap<String, Integer>>();
		System.out.println("Providing self diagnosed support!");
		results.putAll(pButton.push());
		System.out.println("Button Pushed!");
		if (strategy.equals("noAdapt")){
			results.putAll(notification.notifyEmergencyNoAdapt());
		}
		if (strategy.equals("reliable")){
			System.out.println("Running reliable notifyEmergency module");
			results.putAll(notification.notifyEmergencyReliable());
		}
		if (strategy.equals("retry")){
			System.out.println("Running reliable notifyEmergency module");
			results.putAll(notification.notifyEmergencyRetry());
		}
		

		System.out.println("Self diagnosed support Provided!");
		return results;
	}
}
