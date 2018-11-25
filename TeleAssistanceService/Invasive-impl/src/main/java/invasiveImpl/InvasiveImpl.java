package invasiveImpl;

import java.util.HashMap;

import invasive.Invasive;

public class InvasiveImpl implements Invasive {
	
	protected void activate() {
		System.out.println("Invasive Drug Administration Module Activated");
	}
	
	protected void deactivate() {
		System.out.println("Invasive Drug Administration Module Deactivated");
	}


	public String admInvasiveDrug() {
		System.out.println("Administering medicine by administrating invasive drug");
		String[] services = {"DrugService"};
		for(String serviceName: services) {
			for(int i=0; i<=2; i++) {
				//if(checkSuccess(0.01)) return serviceName;
			}
		}
		return "Drug Service Failed...";
	}
	public Integer checkFailed(double d) {
		Double randomNumber = Math.random();
		if(d < randomNumber) {
			return 0;
		}
		return 1;
	}
	@Override
	public HashMap<String, HashMap<String, Integer>> admInvasiveDrugNoAdapt() {
		System.out.println("Changing drug Invasive");
		HashMap<String, HashMap<String, Integer>> op = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		int failure =  checkFailed(0.01);
		
		res.put("Failures", failure);
		res.put("Cost", 5);
		res.put("SystemFailures", failure);
		res.put("Invocations", 1);
		op.put("DrugService", res);
		
		return op;
	}

	@Override
	public HashMap<String, HashMap<String, Integer>> admInvasiveDrugRetry() {
		HashMap<String, HashMap<String, Integer>> op = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		int failure = checkFailed(0.01);  //Alarm Service 2: Most reliable!
		res.put("Cost", 5);
		res.put("Invocations", 1);
		res.put("SystemFailures", 0);
		res.put("Failures", failure);
		
		for (int i = 0; i < 2; i++) {
			if (failure == 0) break;
			res.put("Cost", res.get("Cost") + 5);
			res.put("Invocations", res.get("Invocations") + 1);
			res.put("Failures", res.get("Failures") + 1);
			failure = checkFailed(0.01);
		}
		if (failure == 1) res.put("SystemFailures", 1);
		op.put("DrugService", res);
		
		return op;
	}

	@Override
	public HashMap<String, HashMap<String, Integer>> admInvasiveDrugReliable() {
		HashMap<String, HashMap<String, Integer>> op = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		int failure =  checkFailed(0.01);
		
		res.put("Failures", failure);
		res.put("Cost", 5);
		res.put("SystemFailures", failure);
		res.put("Invocations", 1);
		op.put("DrugService", res);
		
		return op;
	}
}
