package alarmService;

import java.util.HashMap;

import notifyEmergencyMedicalServices.NotifyEmergencyMedicalServices;

public class AlarmService implements NotifyEmergencyMedicalServices {
	
	protected void activate() {
		System.out.println("Alarm Service Module Activated");
	}
	
	protected void deactivate() {
		System.out.println("Alarm Service Module Deactivated");
	}

	@Override
	public HashMap<String, HashMap<String, Integer>> notifyEmergencyNoAdapt() {
		System.out.println("Sounding the alarm!");
		HashMap<String, HashMap<String, Integer>> op = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		int failure = checkFailed(0.18);
		
		res.put("Failures", failure);
		res.put("Cost", 2);
		res.put("Invocations", 1);
		res.put("SystemFailures", failure);
		
		op.put("AlarmService", res);
		return op;
	}
	
	@Override
	public HashMap<String, HashMap<String, Integer>> notifyEmergencyReliable() {
		System.out.println("Sounding the alarm REliably!");
		HashMap<String, HashMap<String, Integer>> op = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		
		int failure = checkFailed(0.04);  //Alarm Service 2: Most reliable!
		res.put("Cost", 12);
		res.put("Invocations", 1);
		res.put("SystemFailures", 0);
		res.put("Failures", 0);
		
		if (failure == 1) {
			res.put("Cost", res.get("Cost") + 4);
			res.put("Invocations", res.get("Invocations") + 1);
			res.put("Failures", 1);
			failure = checkFailed(0.11);  // ALarm Service 1
			if (failure == 1) {
				res.put("Cost", res.get("Cost") + 2);
				res.put("Invocations", res.get("Invocations") + 1);
				res.put("Failures", res.get("Failures") + 1);
				failure = checkFailed(0.18);  // ALarm Service 1
				if (failure == 1) {
					res.put("Failures", res.get("Failures") + 1);
					res.put("SystemFailures", 1);
				}
			}
		}
		op.put("AlarmService", res);
		return op;
	}
	
	@Override
	public HashMap<String, HashMap<String, Integer>> notifyEmergencyRetry() {
		System.out.println("Sounding the alarm Retrying!");
		HashMap<String, HashMap<String, Integer>> op = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		int failure = checkFailed(0.18);  
		res.put("Cost", 2);
		res.put("Invocations", 1);
		res.put("SystemFailures", 0);
		res.put("Failures", failure);
		
		for (int i = 0; i < 2; i++) {
			if (failure == 0) break;
			res.put("Cost", res.get("Cost") + 2);
			res.put("Invocations", res.get("Invocations") + 1);
			res.put("Failures", res.get("Failures") + 1);
			failure = checkFailed(0.18);
		}
		if (failure == 1) {
			res.put("SystemFailures", 1);
		}
		op.put("AlarmService", res);
		return op;
	}
	
	
	public Integer checkFailed(double d) {
		Double randomNumber = Math.random();
		if(d < randomNumber) {
			return 0;
		}
		return 1;
	}
}