package view;

import java.util.ArrayList;

import payment.PaymentManager;
import user.UserAccount;
import claim.Claim;
import claim.ClaimManager;
import claim.ClaimStatus;

public class ProceedPaymentView extends View {

	// Attributes

	private UserAccount ua;
	private PaymentManager pm;
	private ClaimManager cm;
	private ArrayList<Claim> claimsToBePayedList;

	// Constructors

	public ProceedPaymentView(UserAccount ua, PaymentManager pm, ClaimManager cm) {
		this.ua = ua;
		this.pm = pm;
		this.cm = cm;
		claimsToBePayedList = new ArrayList<Claim>();
		display();
	}

	protected void display() {
		clear();
		addToList();
		printClaimList(this.claimsToBePayedList);
		System.out
				.print("\nEnter the index of the claim you want proceed payment: ");
	}

	private void addToList() {
		this.claimsToBePayedList = cm.lookForSpecificClaims(cm.getClaimList(),
				ClaimStatus.OK);
	}
}
