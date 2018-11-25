package nonInvasive;

import java.util.HashMap;

public interface NonInvasive {
	public HashMap<String, HashMap<String, Integer>> admNonInvasiveDrugNoAdapt();
	public HashMap<String, HashMap<String, Integer>> admNonInvasiveDrugRetry();
	public HashMap<String, HashMap<String, Integer>> admNonInvasiveDrugReliable();
}