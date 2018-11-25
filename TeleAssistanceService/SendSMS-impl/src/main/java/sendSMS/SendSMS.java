package sendSMS;

import notifyEmergencyMedicalServices.NotifyEmergencyMedicalServices;

public class SendSMS implements NotifyEmergencyMedicalServices {
	
	protected void activate() {
		System.out.println("sendSMS Module Activated");
	}
	
	protected void deactivate() {
		System.out.println("sendSMS Module Deactivated");
	}

	@Override
	public String notifyEmergency() {
		return "Notify Emergency by Sending SMS";
	}
}
