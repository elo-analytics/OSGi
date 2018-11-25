package changeDose;

import java.util.HashMap;

public interface ChangeDose {
	public HashMap<String, HashMap<String, Integer>> changeDoseNoAdapt();
	public HashMap<String, HashMap<String, Integer>> changeDoseRetry();
	public HashMap<String, HashMap<String, Integer>> changeDoseReliable();
}
