package payment;

import java.util.ArrayList;

import claim.Claim;
import claim.ClaimManager;
import claim.ClaimStatus;

public class PaymentManager {

	// Attributes

	private ArrayList<Payment> paymentsList;
	private ClaimManager cm;

	// Constructors

	public PaymentManager(ClaimManager cm) {
		paymentsList = new ArrayList<Payment>();
		this.cm = cm;
	}

	// Methods

	public void addPayment(Claim cl, String date, PaymentMode mode) {
		paymentsList.add(new Payment(cl, date, mode));
	}

	public void setClaimStatusPayed(Claim cl) {
		cm.setClaimStatus(cl, ClaimStatus.PAYED);
	}

	// Accessors

	public ArrayList<Payment> getPaymentsList() {
		return paymentsList;
	}

	public ClaimManager getCm() {
		return cm;
	}

}
