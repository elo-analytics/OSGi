package provideAutomatedLifeSupportImpl;

import java.util.Arrays;
import java.util.HashMap;

import enactTreatment.EnactTreatment;
import monitorPatient.MonitorPatient;
import provideAutomatedLifeSupport.ProvideAutomatedLifeSupport;


public class ProvideAutomatedLifeSupportImpl implements ProvideAutomatedLifeSupport {
	
	int CHANGEDRUG = 0;
	int CHANGEDOSE = 1;
	int SENDALARM = 2;
	int PATIENTOK = 3;
	private MonitorPatient monitor;
	private EnactTreatment enactor;
	
	void setMonitorPatient (MonitorPatient monitor) {
		this.monitor = monitor;
	}
	
	void setEnactTreatment (EnactTreatment enactor) {
		this.enactor = enactor;
	}
	protected void activate() {
		System.out.println("ProvideAutomatedLifeSupport Module Activated");
		/*
		HashMap<String, HashMap<String, Integer>> results = new HashMap<String, HashMap<String, Integer>>();
		int analysisResult = monitor.monitor("noAdapt").get("RemoteAnalysis").get("analysisResult");
		
		if (analysisResult == PATIENTOK){
			System.out.println("Patient OK. Nothing to do...");
			return;
		}
		results = enactor.enactTreatment(analysisResult, "noAdapt");
		System.out.println(Arrays.asList(results));
		*/
	}
	
	protected void deactivate() {
		System.out.println("ProvideAutomatedLifeSupport Module Deactivated");
	}

	@Override
	public HashMap<String, HashMap<String, Integer>> ProvideAutomatedLifeSupport(String strategy) {
		System.out.println("Providing Automated Life Support...");
		
		HashMap<String, HashMap<String, Integer>> results = new HashMap<String, HashMap<String, Integer>>();
		
		if (strategy.equals("noAdapt")){
			results = monitor.monitor("noAdapt");
			System.out.println("Got Monitor result!");
			
			if (results.get("MedicalService").get("SystemFailures") == 1) { // System Failure... Do not continue!
				System.out.println("Got MedicalService failure!");
				return results;
			}
			
			int analysisResult = results.get("MedicalService").get("analysisResult");
			System.out.println("Analysis result " + analysisResult);
			if (analysisResult == PATIENTOK){
				System.out.println("Patient OK. Nothing to do...");
				return results;
			}
			results = enactor.enactTreatment(analysisResult, "noAdapt");
		}
		else if (strategy.equals("retry")){
			results = monitor.monitor("retry");
			System.out.println("Got Monitor result!");
			System.out.println(Arrays.asList(results));
			if (results.get("MedicalService").get("SystemFailures") == 1) { // System Failure... Do not continue!
				System.out.println("Got MedicalService failure!");
				return results;
			}
			
			int analysisResult = results.get("MedicalService").get("analysisResult");
			System.out.println("Analysis result " + analysisResult);
			if (analysisResult == PATIENTOK){
				System.out.println("Patient OK. Nothing to do...");
				return results;
			}
			results = enactor.enactTreatment(analysisResult, "retry");
		}
		else if (strategy.equals("reliable")){
			results = monitor.monitor("reliable");
			System.out.println("Got Monitor result!");

			if (results.get("MedicalService").get("SystemFailures") == 1) { // System Failure... Do not continue!
				System.out.println("Got MedicalService failure!");
				return results;
			}
			
			int analysisResult = results.get("MedicalService").get("analysisResult");
			System.out.println("Analysis result " + analysisResult);
			if (analysisResult == PATIENTOK){
				System.out.println("Patient OK. Nothing to do...");
				return results;
			}
			results = enactor.enactTreatment(analysisResult, "reliable");
		}
		return results;
	}


}
