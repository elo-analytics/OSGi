package enactTreatmentImpl;

import java.util.Arrays;
import java.util.HashMap;

import administerMedicine.AdministerMedicine;
import enactTreatment.EnactTreatment;
import notifyEmergencyMedicalServices.NotifyEmergencyMedicalServices;


public class EnactTreatmentImpl implements EnactTreatment {
	
	int CHANGEDRUG = 0;
	int CHANGEDOSE = 1;
	int SENDALARM = 2;
	int PATIENTOK = 3;
	private AdministerMedicine adminMed;
	private NotifyEmergencyMedicalServices notification;
	
	void setAdministerMedicine (AdministerMedicine adminMed) {
		this.adminMed = adminMed;
	}
	
	void setNotifyEmergencyMedicalServices (NotifyEmergencyMedicalServices notification) {
		this.notification = notification;
	}
	protected void activate() {
		System.out.println("Enact Treatment Module Activated");
		/*
		HashMap<String, HashMap<String, Integer>> results = new HashMap<String, HashMap<String, Integer>>();
		int analysisResult = SENDALARM;
		
		if (analysisResult == CHANGEDRUG) results = adminMed.admMedicineNoAdapt(analysisResult);
		else if (analysisResult == CHANGEDOSE) results = adminMed.admMedicineNoAdapt(analysisResult);
		else if (analysisResult == SENDALARM) results = notification.notifyEmergencyNoAdapt();
		
		System.out.println(Arrays.asList(results));
		*/
	}
	
	protected void deactivate() {
		System.out.println("Enact Treatment Module Deactivated");
	}

	@Override
	public HashMap<String, HashMap<String, Integer>> enactTreatment(int analysisResult, String strategy) {
		
		HashMap<String, HashMap<String, Integer>> results = new HashMap<String, HashMap<String, Integer>>();
		
		if (strategy.equals("noAdapt")){
			System.out.println("Enacting noAdapt...");
			if (analysisResult == CHANGEDRUG) results = adminMed.admMedicine(analysisResult, strategy);
			else if (analysisResult == CHANGEDOSE) results = adminMed.admMedicine(analysisResult, strategy);
			else if (analysisResult == SENDALARM) results = notification.notifyEmergencyNoAdapt();
		}
		if (strategy.equals("reliable")){
			System.out.println("Enacting reliable...");
			if (analysisResult == CHANGEDRUG) results = adminMed.admMedicine(analysisResult, strategy);
			else if (analysisResult == CHANGEDOSE) results = adminMed.admMedicine(analysisResult, strategy);
			else if (analysisResult == SENDALARM) results = notification.notifyEmergencyReliable();
		}
		if (strategy.equals("retry")){
			System.out.println("Enacting retry...");
			if (analysisResult == CHANGEDRUG) results = adminMed.admMedicine(analysisResult, strategy);
			else if (analysisResult == CHANGEDOSE) results = adminMed.admMedicine(analysisResult, strategy);
			else if (analysisResult == SENDALARM) results = notification.notifyEmergencyRetry();
		}

		return results;
	}

}
