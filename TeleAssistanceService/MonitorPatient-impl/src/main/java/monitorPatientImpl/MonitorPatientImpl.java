package monitorPatientImpl;

import java.util.Arrays;
import java.util.HashMap;

import analyzeData.AnalyzeData;
import getVitalParams.GetVitalParams;
import monitorPatient.MonitorPatient;


public class MonitorPatientImpl implements MonitorPatient {
	
	private GetVitalParams params;
	private AnalyzeData data;
	
	void setGetVitalParams (GetVitalParams params) {
		this.params = params;
	}
	
	void setAnalyzeData (AnalyzeData data) {
		this.data = data;
	}
	protected void activate() {
		System.out.println("MonitorPatient Module Activated");
		/*
		HashMap<String, HashMap<String, Integer>> results = new HashMap<String, HashMap<String, Integer>>();
		results.putAll(params.getVitalParams());
		results.putAll(data.makeAnalysisNoAdapt());
		
		System.out.println(Arrays.asList(results));
		*/
	}
	
	protected void deactivate() {
		System.out.println("MonitorPatient Module Deactivated");
	}

	@Override
	public HashMap<String, HashMap<String, Integer>> monitor(String strategy) {
		System.out.println("Monitoring Patient...");

		HashMap<String, HashMap<String, Integer>> results = new HashMap<String, HashMap<String, Integer>>();
		results.putAll(params.getVitalParams());
		
		if (strategy.equals("noAdapt")) {
			System.out.println("Making analysis NoAdapt...");
			results.putAll(data.makeAnalysisNoAdapt());
		}
		else if (strategy.equals("retry")) {
			System.out.println("Making analysis retry...");
			results.putAll(data.makeAnalysisRetry());
		}
		else if (strategy.equals("reliable")) {
			System.out.println("Making analysis reliable...");
			results.putAll(data.makeAnalysisReliable());
		}
		
		//results.putAll(data.makeAnalysisNoAdapt());
		System.out.println("Analysis done!");
		
		return results;
	}

}
