package notifyEmergencyMedicalServices;

import java.util.HashMap;

public interface NotifyEmergencyMedicalServices {
	public HashMap<String, HashMap<String, Integer>> notifyEmergencyNoAdapt();
	public HashMap<String, HashMap<String, Integer>> notifyEmergencyRetry();
	public HashMap<String, HashMap<String, Integer>> notifyEmergencyReliable();
}
