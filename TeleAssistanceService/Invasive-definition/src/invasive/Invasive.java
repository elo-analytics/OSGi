package invasive;

import java.util.HashMap;

public interface Invasive {
	public HashMap<String, HashMap<String, Integer>> admInvasiveDrugNoAdapt();
	public HashMap<String, HashMap<String, Integer>> admInvasiveDrugRetry();
	public HashMap<String, HashMap<String, Integer>> admInvasiveDrugReliable();
}
