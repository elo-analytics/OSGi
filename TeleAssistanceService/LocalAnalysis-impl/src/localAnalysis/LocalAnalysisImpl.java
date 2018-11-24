package localAnalysis;

import analyzeData.AnalyzeData;

public class LocalAnalysisImpl implements AnalyzeData {
	
	protected void activate() {
		System.out.println("LocalAnalysis Module Activated");
	}
	
	protected void deactivate() {
		System.out.println("LocalAnalysis Module Deactivated");
	}

	@Override
	public String makeAnalysis() {
		System.out.println("Making Analysis locally");
		String[] services = {
				"MedicalService1", 
				"MedicalService2", 
				"MedicalService3" };
		for(String serviceName: services) {
			for(int i=0; i<=2; i++) {
				if(checkSuccess(serviceName)) return getPatientStatus();
			}
		}
		return "Local Analysis Failed...";
	}
	public boolean checkSuccess(String serviceName) {
		Double randomNumber = Math.random();
		if(0.25 > randomNumber) {
			return false;
		}
		return true;
	}
	
	public String getPatientStatus() {
		Double rand = Math.random();
		if (rand < 0.25) return "changeDrug";
		else if (rand < 0.5) return "changeDose";
		else if (rand < 0.75) return "sendAlarm";
		return "patientOK";
	}
}
