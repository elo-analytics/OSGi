package pushButtonImp;

import pushButton.PushButton;

public class PushButtonImpl implements PushButton {
	
	protected void activate() {
		System.out.println("PushButton Module Activated");
	}
	
	protected void deactivate() {
		System.out.println("PushButton Module Deactivated");
	}

	@Override
	public String push() {
		return "Notify Emergency by Pushing Button";
	}
}
