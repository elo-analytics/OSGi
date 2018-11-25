package monitorPatient;

import java.util.HashMap;

public interface MonitorPatient {
	public HashMap<String, HashMap<String, Integer>> monitor(String strategy);
}
