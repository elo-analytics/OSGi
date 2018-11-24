package analyzeData;

import java.util.HashMap;

public interface AnalyzeData {
	public HashMap<String, HashMap<String, Integer>> makeAnalysisNoAdapt();
	public HashMap<String, HashMap<String, Integer>> makeAnalysisRetry();
	public HashMap<String, HashMap<String, Integer>> makeAnalysisReliable();
}
