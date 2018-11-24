package changeDrug;

import java.util.HashMap;

public interface ChangeDrug {
	public HashMap<String, HashMap<String, Integer>> changeDrugNoAdapt();
	public HashMap<String, HashMap<String, Integer>> changeDrugRetry();
	public HashMap<String, HashMap<String, Integer>> changeDrugReliable();
}
