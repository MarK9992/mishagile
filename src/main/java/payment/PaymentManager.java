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

    public ArrayList<Payment> checkPaymentByClient(String firstName, String name) {
	ArrayList<Payment> matches = new ArrayList<Payment>();
	for (Payment cl : paymentsList) {
	    if (cl.getClaimHandled().getClaimant().match(firstName, name)) {
		matches.add(cl);
	    }
	}
	if (matches.size() == 0) {
	    matches = null;
	}
	return matches;
    }

    public ArrayList<Payment> checkPaymentByDate(String date) {
	ArrayList<Payment> matches = new ArrayList<Payment>();
	for (Payment cl : paymentsList) {
	    if (cl.matchDate(date)) {
		matches.add(cl);
	    }
	}
	if (matches.size() == 0) {
	    matches = null;
	}
	return matches;
    }

    public ArrayList<Payment> checkPaymentByMode(PaymentMode mode) {
	ArrayList<Payment> matches = new ArrayList<Payment>();
	for (Payment cl : paymentsList) {
	    if (cl.getPayment().equals(mode)) {
		matches.add(cl);
	    }
	}
	if (matches.size() == 0) {
	    matches = null;
	}
	return matches;
    }

    // Accessors

    public ArrayList<Payment> getPaymentsList() {
	return paymentsList;
    }

    public ClaimManager getCm() {
	return cm;
    }

}
