package enactTreatment;

import java.util.HashMap;

public interface EnactTreatment {
	public HashMap<String, HashMap<String, Integer>> enactTreatment(int analysisResult, String strategy);
}
