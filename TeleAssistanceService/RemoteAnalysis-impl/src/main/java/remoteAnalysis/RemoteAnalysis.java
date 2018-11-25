package remoteAnalysis;

import java.util.HashMap;

import analyzeData.AnalyzeData;

public class RemoteAnalysis implements AnalyzeData {
	
	int CHANGEDRUG = 0;
	int CHANGEDOSE = 1;
	int SENDALARM = 2;
	int PATIENTOK = 3;
	
	protected void activate() {
		System.out.println("RemoteAnalysis Module Activated");
	}
	
	protected void deactivate() {
		System.out.println("RemoteAnalysis Module Deactivated");
	}

	@Override
	public HashMap<String, HashMap<String, Integer>> makeAnalysisNoAdapt() {
		
		HashMap<String, HashMap<String, Integer>> op = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		
		int failure =  checkSuccess(0.18);
		
		
		res.put("Failures", failure);
		res.put("Cost", 2);
		res.put("Invocations", 1);
		res.put("SystemFailures", failure);

		if (failure != 1) {
			int analysisResult = getPatientStatus();
			res.put("analysisResult", analysisResult);
		}
		
		op.put("MedicalService", res);
		
		
		return op;
	}
	@Override
	public HashMap<String, HashMap<String, Integer>> makeAnalysisRetry() {
		HashMap<String, HashMap<String, Integer>> op = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		int failure = checkSuccess(0.18);  //Alarm Service 2: Most reliable!
		res.put("Cost", 2);
		res.put("Invocations", 1);
		res.put("SystemFailures", 0);
		res.put("Failures", failure);
		
		for (int i = 0; i < 2; i++) {
			if (failure == 0) break;
			res.put("Cost", res.get("Cost") + 2);
			res.put("Invocations", res.get("Invocations") + 1);
			res.put("Failures", res.get("Failures") + 1);
			failure = checkSuccess(0.18);
		}
		System.out.println("Medical Service Failure: " + failure);
		if (failure != 1) {
			int analysisResult = getPatientStatus();
			System.out.println("Putting analysisREsult");
			res.put("analysisResult", analysisResult);
		}
		else {
			res.put("SystemFailures", 1);
		}
		
		op.put("MedicalService", res);
		
		return op;
	}
	@Override
	public HashMap<String, HashMap<String, Integer>> makeAnalysisReliable() {
		HashMap<String, HashMap<String, Integer>> op = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		
		int failure = checkSuccess(0.07);  //Medical Service 2: Most reliable!
		res.put("Cost", 14);
		res.put("Invocations", 1);
		res.put("SystemFailures", 0);
		res.put("Failures", 0);
		
		if (failure == 1) {
			res.put("Cost", res.get("Cost") + 4);
			res.put("Invocations", res.get("Invocations") + 1);
			res.put("Failures", 1);
			failure = checkSuccess(0.12);  // Medical Service 1
			if (failure == 1) {
				res.put("Cost", res.get("Cost") + 2);
				res.put("Invocations", res.get("Invocations") + 1);
				res.put("Failures", res.get("Failures") + 1);
				failure = checkSuccess(0.18);  // Medical Service 1
				if (failure == 1) {
					res.put("Failures", res.get("Failures") + 1);
					res.put("SystemFailures", 1);
				}
			}
		}
		if (failure != 1) {
			int analysisResult = getPatientStatus();
			res.put("analysisResult", analysisResult);
		}
		op.put("MedicalService", res);
		
		return op;
	}

	public String makeAnalysis() {
		System.out.println("Making Analysis Remotelly");
		String[] services = {
				"MedicalService1", 
				"MedicalService2", 
				"MedicalService3" };
		for(String serviceName: services) {
			for(int i=0; i<=2; i++) {
				//if(checkSuccess(0.11)) return getPatientStatus();
			}
		}
		return "Remote Analysis Failed...";
	}
	public int checkSuccess(double d) {
		Double randomNumber = Math.random();
		System.out.println(randomNumber);
		if(d > randomNumber) {
			return 1;  //falhou!
		}
		return 0;
	}
	public Integer getPatientStatus() {
		Double rand = Math.random();
		if (rand < 0.25) return CHANGEDRUG;
		else if (rand < 0.5) return CHANGEDOSE;
		else if (rand < 0.75) return SENDALARM;
		return PATIENTOK;
	}
}
