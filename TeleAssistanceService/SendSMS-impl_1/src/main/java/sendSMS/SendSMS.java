package sendSMS;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import notifyEmergencyMedicalServices.NotifyEmergencyMedicalServices;

@Component
public class SendSMS implements NotifyEmergencyMedicalServices {
	
	@Activate
	protected void activate(ComponentContext context) {
		System.out.println("sendSMS Module Activated");
	}
	@Deactivate
	protected void deactivate(ComponentContext context) {
		System.out.println("sendSMS Module Deactivated");
	}

	@Override
	public String notifyEmergency() {
		return "Notify Emergency by Sending SMS";
	}
}
