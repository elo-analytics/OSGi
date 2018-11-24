package changeDrugImpl;

import java.util.Arrays;
import java.util.HashMap;

import changeDrug.ChangeDrug;
import invasive.Invasive;
import nonInvasive.NonInvasive;

public class ChangeDrugImpl implements ChangeDrug {
	
	private Invasive invasiveDrug;
	private NonInvasive nonInvasiveDrug;
	private String drugType;
	
	void setInvasive (Invasive invasiveDrug) {
		this.invasiveDrug = invasiveDrug;
	}
	
	void setNonInvasive (NonInvasive nonInvasiveDrug) {
		this.nonInvasiveDrug = nonInvasiveDrug;
	}
	protected void activate() {
		System.out.println("Change Drug Module Activated");
		/*
		HashMap<String, HashMap<String, Integer>> results = new HashMap<String, HashMap<String, Integer>>();
		
		System.out.println("Provide Self Diagnosed Emergencies Support Module Activated");
		results.putAll(invasiveDrug.admInvasiveDrugNoAdapt());
		results.putAll(nonInvasiveDrug.admNonInvasiveDrugNoAdapt());
		
		System.out.println(Arrays.asList(results));
		*/
	}
	
	protected void deactivate() {
		System.out.println("Change Drug Module Deactivated");
	}

	public String getDrugType() {
		Double rand = Math.random();
		if (rand > 0.25) return "invasiveDrug";
		return "nonInvasiveDrug";
	}

	@Override
	public HashMap<String, HashMap<String, Integer>> changeDrugNoAdapt() {		
		this.drugType = getDrugType();
		if(this.drugType.equals("invasiveDrug")) {
			return invasiveDrug.admInvasiveDrugNoAdapt();
		}
		else return nonInvasiveDrug.admNonInvasiveDrugNoAdapt();
	}

	@Override
	public HashMap<String, HashMap<String, Integer>> changeDrugRetry() {
		this.drugType = getDrugType();
		if(this.drugType.equals("invasiveDrug")) {
			return invasiveDrug.admInvasiveDrugRetry();
		}
		else return nonInvasiveDrug.admNonInvasiveDrugRetry();
	}

	@Override
	public HashMap<String, HashMap<String, Integer>> changeDrugReliable() {
		this.drugType = getDrugType();
		if(this.drugType.equals("invasiveDrug")) {
			return invasiveDrug.admInvasiveDrugReliable();
		}
		else return nonInvasiveDrug.admNonInvasiveDrugReliable();
	}

}
