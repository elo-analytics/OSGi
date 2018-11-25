package provideHealthSupportImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import provideAutomatedLifeSupport.ProvideAutomatedLifeSupport;
import provideHealthSupport.ProvideHealthSupport;
import provideSelfDiagnosedEmergenciesSupport.ProvideSelfDiagnosedEmergenciesSupport;

public class ProvideHealthSupportImpl implements ProvideHealthSupport {
	
	private ProvideAutomatedLifeSupport automated;
	private ProvideSelfDiagnosedEmergenciesSupport selfdiagnosed;
	private String pick;
	HashMap<String, HashMap<String, Integer>> results;
	private int systemFailures;
	
	void setProvideAutomatedLifeSupport (ProvideAutomatedLifeSupport automated) {
		this.automated = automated;
	}
	
	void setProvideSelfDiagnosedEmergenciesSupport (ProvideSelfDiagnosedEmergenciesSupport selfdiagnosed) {
		this.selfdiagnosed = selfdiagnosed;
	}
	
	protected HashMap<String, Integer> fillInitialParams(){
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		systemFailures = 0;
		res.put("Invocations", 0);
		res.put("Failures", 0);
		res.put("Cost", 0);
		res.put("SystemFailures", 0);
		
		return res;
	}

	protected void setInitialParams(){
		results = new HashMap<String, HashMap<String, Integer>>();
		
		results.put("AlarmService", fillInitialParams());
		results.put("MedicalService", fillInitialParams());
		results.put("DrugService", fillInitialParams());
		
		
	}
	
	protected void activate() {
		List<Integer> systemFailuresNoAdaptList = new ArrayList<Integer>();
		List<Integer> systemFailuresReliableList = new ArrayList<Integer>();
		List<Integer> systemFailuresRetryList = new ArrayList<Integer>();
		
		for (int i = 0; i <= 10; i++) {
			HashMap<String, HashMap<String, Integer>> finalResultsNoAdapt = null;
			int systemFailuresNoAdapt = 0;
			
			HashMap<String, HashMap<String, Integer>> finalResultsReliable = null;
			int systemFailuresReliable = 0;
			
			HashMap<String, HashMap<String, Integer>> finalResultsRetry = null;
			int systemFailuresRetry = 0;
			
			
			System.out.println("\n\n-------------------------------------------\n"
					+ 	"   ProvideHealthSupport Module Activated\n"
					+ 	"-------------------------------------------\n\n");
	
			System.out.println("\n\n-------------------------------------------\n"
					+ 	"   No Adaptation Strategy Experiment\n"
					+ 	"-------------------------------------------\n\n");
			runExperiment("noAdapt");
			finalResultsNoAdapt = results;
			systemFailuresNoAdapt = systemFailures;
			systemFailuresNoAdaptList.add(systemFailuresNoAdapt);
			
			System.out.println("\n\n-------------------------------------------\n"
					+ 	"   Reliable Strategy Experiment\n"
					+ 	"-------------------------------------------\n\n");
			runExperiment("reliable");
			finalResultsReliable = results;
			systemFailuresReliable = systemFailures;
			systemFailuresReliableList.add(systemFailuresReliable);
			
			System.out.println("\n\n-------------------------------------------\n"
					+ 	"   Retry Strategy Experiment\n"
					+ 	"-------------------------------------------\n\n");
			runExperiment("retry");
			finalResultsRetry = results;
			systemFailuresRetry = systemFailures;
			systemFailuresRetryList.add(systemFailuresRetry);
			
			System.out.println("\n\n-------------------------------------------\n"
					+ 	"   Results\n"
					+ 	"-------------------------------------------\n\n");
			System.out.println("\nNO ADAPT\n");
			System.out.println("System Failures: " + systemFailuresNoAdapt);
			System.out.println(Arrays.asList(finalResultsNoAdapt));
			System.out.println("\nRETRY\n");
			System.out.println("System Failures: " + systemFailuresRetry);
			System.out.println(Arrays.asList(finalResultsRetry));
			System.out.println("\nRELIABLE\n");
			System.out.println("System Failures: " + systemFailuresReliable);
			System.out.println(Arrays.asList(finalResultsReliable));
			
			printFinalResuls(finalResultsNoAdapt,finalResultsRetry,finalResultsReliable, 
					systemFailuresNoAdapt, systemFailuresRetry, systemFailuresReliable);
		}
		System.out.println("No Adapt" + Arrays.asList(systemFailuresNoAdaptList));
		System.out.println("Retry" + Arrays.asList(systemFailuresRetryList));
		System.out.println("Reliable" + Arrays.asList(systemFailuresReliableList));
	}
	
	private void printFinalResuls(HashMap<String, HashMap<String, Integer>> finalResultsNoAdapt,
			HashMap<String, HashMap<String, Integer>> finalResultsRetry,
			HashMap<String, HashMap<String, Integer>> finalResultsReliable, int systemFailuresNoAdapt, 
			int systemFailuresRetry, int systemFailuresReliable) {
		
		HashMap<String, Integer> modNoAdapt = null;
		HashMap<String, Integer> modRetry = null;
		HashMap<String, Integer> modReliable = null;
		
		//--------- Alarm Service ----------- //
		modNoAdapt = finalResultsNoAdapt.get("AlarmService");
		modRetry = finalResultsRetry.get("AlarmService");
		modReliable = finalResultsReliable.get("AlarmService");
		printModuleResult(modNoAdapt, modRetry, modReliable, "AlarmService",
				systemFailuresNoAdapt, systemFailuresRetry, systemFailuresReliable);
		
		//--------- Drug Service ----------- //
		modNoAdapt = finalResultsNoAdapt.get("DrugService");
		modRetry = finalResultsRetry.get("DrugService");
		modReliable = finalResultsReliable.get("DrugService");
		printModuleResult(modNoAdapt, modRetry, modReliable, "DrugService",
				systemFailuresNoAdapt, systemFailuresRetry, systemFailuresReliable);
		
		//--------- Medical Service ----------- //
		modNoAdapt = finalResultsNoAdapt.get("MedicalService");
		modRetry = finalResultsRetry.get("MedicalService");
		modReliable = finalResultsReliable.get("MedicalService");
		printModuleResult(modNoAdapt, modRetry, modReliable, "MedicalService",
				systemFailuresNoAdapt, systemFailuresRetry, systemFailuresReliable);
		
	}

	private void printModuleResult(HashMap<String, Integer> modNoAdapt, HashMap<String, Integer> modRetry,
			HashMap<String, Integer> modReliable, String moduleName, int systemFailuresNoAdapt, 
			int systemFailuresRetry, int systemFailuresReliable) {
		System.out.println("\n\n--- " + moduleName);
		
		System.out.println("\nAdaptation Strategy\tRates TAS Failures\tRates "+moduleName+"\tCost (# Invocations)");
		System.out.println("No Adaptation\t\t\t"+
				((float)systemFailuresNoAdapt / 1000.0) +
				"\t\t\t" +((float)modNoAdapt.get("SystemFailures") / (float)modNoAdapt.get("Invocations")) +
				"\t\t" + modNoAdapt.get("Cost") + "(" + modNoAdapt.get("Invocations") + ")" );
		System.out.println("Retry\t\t\t\t"+
				((float)systemFailuresRetry / 1000.0) +
				"\t\t\t" +((float)modRetry.get("SystemFailures") / (float)modRetry.get("Invocations")) +
				"\t\t" + modRetry.get("Cost") + "(" + modRetry.get("Invocations") + ")" );
		System.out.println("Reliable\t\t\t"+
				((float)systemFailuresReliable / 1000.0) +
				"\t\t\t" +((float)modReliable.get("SystemFailures") / (float)modReliable.get("Invocations")) +
				"\t\t" + modReliable.get("Cost") + "(" + modReliable.get("Invocations") + ")" );
	}

	private void runExperiment(String strategy) {
		HashMap<String, HashMap<String, Integer>> finalResults = null;
		setInitialParams();
		//System.out.println(Arrays.asList(results));
		HashMap<String, HashMap<String, Integer>> partResults = null;
		for (int i=1;i<=1000;i++) {
			System.out.println("\nExecution " + i);
			partResults = new HashMap<String, HashMap<String, Integer>>();
			this.pick = getPick();
			if(this.pick.equals("vitalParamsMsg")) {
				System.out.println("Getpick got vitalParamsMsg!!");
				partResults = automated.ProvideAutomatedLifeSupport(strategy);
			}
			else if(this.pick.equals("buttonMsg")) {
				System.out.println("Getpick got buttonMsg!!");
				partResults = selfdiagnosed.provide(strategy);
			}
	
			System.out.println(Arrays.asList(partResults));
			updateResults(partResults);
		}
		//finalResults = results;
	}

	private void updateResults(HashMap<String, HashMap<String, Integer>> partResults) {
		if (partResults.containsKey("AlarmService")) {
			HashMap<String, Integer> alarm = partResults.get("AlarmService");
			updateModules(alarm, "AlarmService");
		}
		if (partResults.containsKey("DrugService")) {
			HashMap<String, Integer> alarm = partResults.get("DrugService");
			updateModules(alarm, "DrugService");
		}
		if (partResults.containsKey("MedicalService")) {
			HashMap<String, Integer> alarm = partResults.get("MedicalService");
			updateModules(alarm, "MedicalService");
		}

		System.out.println(Arrays.asList(results));
	}

	private void updateModules(HashMap<String, Integer> part, String string) {
		System.out.println("Updating " + string);
		HashMap<String, Integer> res = results.get(string);
		res.put("Invocations", part.get("Invocations") + res.get("Invocations"));
		res.put("Failures", part.get("Failures") + res.get("Failures"));
		res.put("Cost", part.get("Cost") + res.get("Cost"));
		res.put("SystemFailures", part.get("SystemFailures") + res.get("SystemFailures"));
		results.put(string, res);
		
		systemFailures += part.get("SystemFailures");
		System.out.println("System Failures: " + systemFailures);
		
	}

	protected void deactivate() {
		System.out.println("ProvideHealthSupport Module Deactivated");
	}

	@Override
	public String ProvideHealthSupport() {
		return "Providing Health Support...\n";
	}

	public String getPick() {
		Double rand = Math.random();
		if (rand > 0.1) return "vitalParamsMsg";
		return "buttonMsg";
	}

}
