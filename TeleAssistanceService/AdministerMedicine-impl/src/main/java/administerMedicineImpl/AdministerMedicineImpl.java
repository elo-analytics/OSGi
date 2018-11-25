package administerMedicineImpl;

import java.util.Arrays;
import java.util.HashMap;

import administerMedicine.AdministerMedicine;
import changeDose.ChangeDose;
import changeDrug.ChangeDrug;


public class AdministerMedicineImpl implements AdministerMedicine {
	
	int CHANGEDRUG = 0;
	int CHANGEDOSE = 1;
	int SENDALARM = 2;
	int PATIENTOK = 3;
	private ChangeDose dose;
	private ChangeDrug drug;
	
	void setChangeDrug (ChangeDrug drug) {
		this.drug = drug;
	}
	
	void setChangeDose (ChangeDose dose) {
		this.dose = dose;
	}
	protected void activate() {
		System.out.println("Administer Medicine Module Activated");
		/*
		HashMap<String, HashMap<String, Integer>> results;
		
		
		String analysisResult = "changeDrug";

		if (analysisResult == "changeDrug") results = drug.changeDrugNoAdapt();
		else results = dose.changeDoseNoAdapt();
		System.out.println(Arrays.asList(results));
		*/
	}
	
	protected void deactivate() {
		System.out.println("Administer Medicine Module Deactivated");
	}

	@Override
	public HashMap<String, HashMap<String, Integer>> admMedicine(int analysisResult, String strategy) {
		HashMap<String, HashMap<String, Integer>> results = null;

		if (strategy.equals("noAdapt")) {
			System.out.println("Administering medicine noAdapt...");
			if (analysisResult == CHANGEDRUG) results = drug.changeDrugNoAdapt();
			else results = dose.changeDoseNoAdapt();
		}
		else if (strategy.equals("retry")) {
			System.out.println("Administering medicine retry...");
			if (analysisResult == CHANGEDRUG) results = drug.changeDrugRetry();
			else results = dose.changeDoseRetry();
		}
		else if (strategy.equals("reliable")) {
			System.out.println("Administering medicine reliable...");
			if (analysisResult == CHANGEDRUG) results = drug.changeDrugReliable();
			else results = dose.changeDoseReliable();
		}
		return results;
	}

}
