package getVitalParamsImpl;

import java.util.HashMap;

import getVitalParams.GetVitalParams;

public class GetVitalParamsImpl implements GetVitalParams{
	
	protected void activate() {
		System.out.println("GetVitalParams Module Activated");
	}
	
	protected void deactivate() {
		System.out.println("GetVitalParams Module Deactivated");
	}

	@Override
	public HashMap<String, HashMap<String, Integer>> getVitalParams() {
		HashMap<String, HashMap<String, Integer>> op = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		
		res.put("Failure", 0);
		res.put("Cost", 0);
		op.put("GetVitalParams", res);
		
		System.out.println("Getting Vital Params...");
		return op;
	}
}
