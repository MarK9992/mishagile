package view;

import java.util.ArrayList;

import payment.Payment;
import payment.PaymentManager;
import payment.PaymentMode;
import user.UserAccount;
import claim.Claim;
import claim.ClaimManager;

public class SearchPaymentView extends SearchView<Payment> {

    // Attributes

    private PaymentManager pm;

    // Constructors

    public SearchPaymentView(PaymentManager pm) {
	super();
	this.pm = pm;
	display();
    }

    public SearchPaymentView() {
	this(new PaymentManager(new ClaimManager()));
    }

    // Methods

    @Override
    protected void display() {
	clear();
	System.out
		.println("1. Search payment by date\n2. Search payment by claimant\n3. Search payment by payment mode");
	System.out.print("\nPlease choose an option:");
	String option = sc.nextLine();

	while (!inputCheck(option, 3)) {
	    System.out.print("\nPlease choose a VALID option: ");
	    option = sc.nextLine();
	}

	switch (Integer.parseInt(option)) {
	case 1:
	    dateSearch();
	    break;
	case 2:
	    claimantSearch();
	    break;

	case 3:
	    paymentModeSearch();
	    break;
	}
	printPaymentList(results);
	printDetails("\nEnter the index of the payment you want to see detailed: ");
    }

    private void paymentModeSearch() {
	String option = askMode();
	while (!inputCheck(option, 2)) {
	    System.out.print("\nPlease choose a VALID option: ");
	    option = sc.nextLine();
	}
	switch (Integer.parseInt(option)) {
	case 1:
	    finalPaymentModeSearch(PaymentMode.BANKTRANSFER);
	    break;
	case 2:
	    finalPaymentModeSearch(PaymentMode.CHECK);
	    break;
	}
    }

    private void finalPaymentModeSearch(PaymentMode mode) {
	clear();
	results = pm.checkPaymentByMode(mode);
    }

    private String askMode() {
	System.out.print("1. Bank transfer\n2. Check");
	return sc.nextLine();
    }

    private void claimantSearch() {
	String[] names;

	clear();
	names = askClientNames();
	results = pm.checkPaymentByClient(names[0], names[1]);
    }

    private void dateSearch() {
	clear();
	results = pm.checkPaymentByDate(askDate());
    }

    protected void printPaymentList(ArrayList<Payment> payment) {
	if (payment != null) {
	    int index = 1;
	    for (Payment p : payment) {
		System.out.println("\n" + index + ". " + p.getPaymentDate()
			+ " "
			+ p.getClaimHandled().getClaimant().namesToString()
			+ " " + p.getPayment());
	    }
	} else {
	    System.out.println("No payment was found...");
	}
    }
}
