package view;

import java.util.ArrayList;

import payment.PaymentManager;
import payment.PaymentMode;
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

	// Methods

	protected void display() {
		clear();
		addToList();
		printClaimList(this.claimsToBePayedList);

		int choice = claimSelection();
		PaymentMode mode = paymentModeSelection();
		pm.setClaimStatusPayed(claimsToBePayedList.get(choice - 1));

		recordPayment(claimsToBePayedList.get(choice - 1), mode);
	}

	private void recordPayment(Claim cl, PaymentMode mode) {
		System.out.print("Please enter the date of payment: ");
		pm.addPayment(cl, sc.nextLine(), mode);
	}

	private PaymentMode paymentModeSelection() {
		System.out.println("1. Bank transfer\n2.Check");
		System.out.print("\nHow dou you want to proceed payment? ");
		String choice = sc.nextLine();

		while (!inputCheck(choice, 2)) {
			System.out.print("\nPlease choose a VALID index: ");
			choice = sc.nextLine();
		}
		if (Integer.parseInt(choice) == 1) {
			return PaymentMode.BANKTRANSFER;
		} else {
			return PaymentMode.CHECK;
		}
	}

	private int claimSelection() {
		System.out
				.print("\nEnter the index of the claim you want proceed payment: ");
		String choice = sc.nextLine();

		while (!inputCheck(choice, claimsToBePayedList.size())) {
			System.out.print("\nPlease choose a VALID index: ");
			choice = sc.nextLine();
		}
		if (Integer.parseInt(choice) <= 0) {
			return 1;
		} else {
			return Integer.parseInt(choice);
		}
	}

	private void addToList() {
		this.claimsToBePayedList = cm.lookForSpecificClaims(cm.getClaimList(),
				ClaimStatus.OK);
	}
}
