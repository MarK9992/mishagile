package payment;

import java.util.ArrayList;

public class PaymentManager {

	// Attributes

	private ArrayList<Payment> paymentsList;

	// Constructors

	public PaymentManager() {
		paymentsList = new ArrayList<Payment>();
	}
	
	// Methods
	
	public void addPayment(Payment thePayment){
		paymentsList.add(thePayment);
	}
	
}
