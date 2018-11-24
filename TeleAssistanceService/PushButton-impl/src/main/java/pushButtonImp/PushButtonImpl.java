package pushButtonImp;

import java.util.HashMap;

import pushButton.PushButton;

public class PushButtonImpl implements PushButton {
	
	protected void activate() {
		System.out.println("PushButton Module Activated");
	}
	
	protected void deactivate() {
		System.out.println("PushButton Module Deactivated");
	}

	@Override
	public HashMap<String, HashMap<String, Integer>> push() {
		System.out.println("Pushing Button!");
		HashMap<String, HashMap<String, Integer>> op = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		
		res.put("Failure", 0);
		res.put("Cost", 0);
		op.put("PushButton", res);
		
		System.out.println("Notifying Emergency by Pushing Button!");
		return op;
	}
}
