package payment;

import claim.Claim;

public class Payment {

	// Attributes
	
	private Claim claimHandled;
	private String paymentDate;
	private PaymentMode payment;
	
	// Constructors
	
	public Payment(Claim claim, String date, PaymentMode payment){
		this.claimHandled = claim;
		this.paymentDate = date;
		this.payment = payment;
	}
}
