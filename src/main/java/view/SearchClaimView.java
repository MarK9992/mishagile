package view;

import java.util.ArrayList;

import claim.Category;
import claim.ClaimStatus;
import user.UserAccount;
import claim.Claim;
import claim.ClaimManager;
import user.UserRank;

public class SearchClaimView extends SearchView<Claim> {

    // Attributes

    private UserAccount ua;
    private ClaimManager cm;
    private ClaimAction action;

    // Constructors

    public SearchClaimView(UserAccount um, ClaimManager cm, ClaimAction action) {
	super();
	this.ua = um;
	this.cm = cm;
	this.action = action;
	display();
    }

    public SearchClaimView() {
	this(new UserAccount(), new ClaimManager(), ClaimAction.UNDEFINED);
    }

    @Override
    protected void display() {
	ArrayList<Claim> classifiedClaims, decidedClaims;

	clear();
	System.out
		.println("1. Search claim by claimant\n2. Search claim by date\n3. Search claim by claimant AND date");

	switch (askOption(3)) {
	case 1:
	    claimantSearch();
	    break;
	case 2:
	    dateSearch();
	    break;
	case 3:
	    claimantAndDateSearch();
	    break;
	}

	if (action != ClaimAction.MAKEDECISION) {
	    this.printClaimList(results);
	}
	if (action == ClaimAction.UNDEFINED) {
	    this.printDetails("\nEnter the index of the claim you want to see detailed: ");
	}

	if (results != null) {
	    if (ua.getRank() == UserRank.ACD || ua.getRank() == UserRank.BCD) {
		if (action == ClaimAction.CLASSIFY) {
		    classifyClaim(results);
		} else if (action == ClaimAction.MAKEDECISION) {
		    classifiedClaims = cm.lookForSpecificClaims(results,
			    ClaimStatus.CLASSIFIED);
		    if (classifiedClaims != null) {
			makeDecision(classifiedClaims);
		    } else {
			System.out.println("No classified claims were found.");
		    }
		}
		if (ua.getRank() == UserRank.CD
			&& action == ClaimAction.SENDLETTER) {
		    decidedClaims = cm.lookForSpecificClaims(results,
			    ClaimStatus.OK);
		    decidedClaims.addAll(cm.lookForSpecificClaims(results,
			    ClaimStatus.NOK));
		    if (decidedClaims != null) {
			sendLetter(decidedClaims);
		    } else {
			System.out
				.println("No claims which a decision was made about were found.");
		    }
		}
	    }
	}
    }

    // Allows the user to send a letter about a claim
    private void sendLetter(ArrayList<Claim> claims) {
	int n;
	String decision;

	System.out
		.println("Here are the claims that a decision was made about:");
	printClaimList(claims);
	n = askNumber(0, claims.size(), "send a letter for");
	if (n != 0) {
	    System.out.println("Letter send.");
	}
    }

    // Allows a user to make a decision about a claim.
    private void makeDecision(ArrayList<Claim> claims) {
	int n;
	String decision;

	System.out.println("Here are the classified claims:");
	printClaimList(claims);
	n = askNumber(0, claims.size(), "make a decision about");
	if (n != 0) {
	    System.out
		    .println("What decision you want to make about the claim (OK/NOK, 0 abort)");
	    decision = sc.nextLine();
	    while (!decision.equals("OK") && !decision.equals("NOK")
		    && !decision.equals("0")) {
		System.out.println("Enter a valid decision! (OK/NOK, 0 abort)");
		decision = sc.nextLine();
	    }
	    if (!decision.equals("0")) {
		if (decision.equals("OK")) {
		    cm.setClaimStatus(claims.get(n - 1), ClaimStatus.OK);
		} else {
		    cm.setClaimStatus(claims.get(n - 1), ClaimStatus.NOK);
		}
	    }
	}
    }

    // TODO grosse duplication dégueulasse

    // Allows a user to classify a claim.
    private void classifyClaim(ArrayList<Claim> result) {
	String category;
	int n;

	n = askNumber(0, result.size(), "classify");
	if (n != 0) {
	    System.out
		    .println("How do you want to classify the claim (simple/complex, 0 abort)");
	    category = sc.nextLine();
	    while (!category.equals("simple") && !category.equals("complex")
		    && !category.equals("0")) {
		System.out
			.println("Enter a valid category! (simple/complex, 0 abort)");
		category = sc.nextLine();
	    }
	    if (!category.equals("0")) {
		if (category.equals("simple")) {
		    cm.setClaimCategory(result.get(n - 1), Category.simple);
		} else {
		    cm.setClaimCategory(result.get(n - 1), Category.complex);
		}
	    }
	}
    }

    // Asks a user to give a number between the parameters for the specified
    // action
    private int askNumber(int a, int b, String action) {
	int n;

	System.out.print("Enter the number of the claim you want to " + action
		+ " (0 abort): ");
	n = Integer.parseInt(sc.nextLine());
	while (a > n || n > b) {
	    System.out.println("Enter a valid number!");
	    n = Integer.parseInt(sc.nextLine());
	}
	return n;
    }

    private void claimantSearch() {
	String[] names;

	clear();
	names = askClientNames();
	results = cm.checkClaimByClient(names[0], names[1]);
    }

    private void dateSearch() {
	clear();
	results = cm.checkClaimByDate(askDate());
    }

    private void claimantAndDateSearch() {
	String[] names;

	clear();
	names = askClientNames();
	results = cm.checkClaimByClientAndDate(names[0], names[1], askDate());
    }
}
