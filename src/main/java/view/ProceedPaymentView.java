package view;

import java.util.ArrayList;

import payment.PaymentManager;
import user.UserAccount;
import claim.Claim;

public class ProceedPaymentView extends View {

	// Attributes

	private UserAccount ua;
	private PaymentManager pm;
	private ArrayList<Claim> claimsToBePayedList;

	// Constructors

	public ProceedPaymentView(UserAccount ua, PaymentManager pm) {
		this.ua = ua;
		this.pm = pm;
		claimsToBePayedList = new ArrayList<Claim>();
		display();
	}

	protected void display() {
		clear();
		
	}
}
