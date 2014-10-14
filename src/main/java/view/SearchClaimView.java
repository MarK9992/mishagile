package view;

import java.util.ArrayList;

import claim.Category;
import claim.ClaimStatus;
import user.UserAccount;
import claim.Claim;
import claim.ClaimManager;
import user.UserRank;

public class SearchClaimView extends View {

	// Attributes
	
	private UserAccount ua;
	private ClaimManager cm;
	private ArrayList<Claim> searchList;

	// Constructors
	
	public SearchClaimView(UserAccount um, ClaimManager cm) {
		super();
		this.ua = um;
		this.cm = cm;
		display();
	}

	public SearchClaimView() {
		this(new UserAccount(), new ClaimManager());
	}

	protected void display() {
		ArrayList<Claim> classifiedClaims;

		clear();
		System.out
				.println("1. Search claim by claimant\n2. Search claim by date\n3. Search claim by claimant AND date");
		System.out.print("\nPlease choose an option:");
		String option = sc.nextLine();

		while (!inputCheck(option, 3)) {
			System.out.print("\nPlease choose a VALID option: ");
			option = sc.nextLine();
		}

		switch (Integer.parseInt(option)) {
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

		if (searchList != null
				&& (ua.getRank() == UserRank.ACD || ua.getRank() == UserRank.BCD)) {
			classifyClaim(searchList);
			classifiedClaims = cm.lookForClassifiedClaims(searchList);
			if (classifiedClaims != null) {
				makeDecision(classifiedClaims);
			}
		}
	}

	// Allows a user to make a decision about a claim.
	private void makeDecision(ArrayList<Claim> claims) {
		int n;
		String decision;

		System.out.print("Would you like to classify a claim? (Y/N) ");
		if (getYesNo()) {
			System.out.println("Here are the classified claims:");
			printList(claims);
			n = askNumber(0, claims.size(), "make a decision about");
			if (n != 0) {
				System.out
						.println("What decision you want to make about the claim (OK/NOK, 0 abort)");
				decision = sc.nextLine();
				while (!decision.equals("OK") && !decision.equals("NOK")
						&& !decision.equals("0")) {
					System.out
							.println("Enter a valid decision! (OK/NOK, 0 abort)");
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
	}

	// TODO grosse duplication dégueulasse

	// Allows a user to classify a claim.
	private void classifyClaim(ArrayList<Claim> result) {
		String category;
		int n;

		System.out.print("Would you like to classify a claim? (Y/N) ");
		if (getYesNo()) {
			n = askNumber(0, result.size(), "classify");
			if (n != 0) {
				System.out
						.println("How do you want to classify the claim (simple/complex, 0 abort)");
				category = sc.nextLine();
				while (!category.equals("simple")
						&& !category.equals("complex") && !category.equals("0")) {
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

	private void printList(ArrayList<Claim> claims) {
		int index = 1;
		for (Claim cm : claims) {
			System.out.println(index + ". " + cm.getDate() + " "
					+ cm.getClaimant().namesToString() + " "
					+ cm.getStatus().toString());
		}
	}

	private void claimantSearch() {
		String[] names;

		clear();
		names = askClientNames();
		searchList = cm.checkClaimByClient(names[0], names[1]);
		printList(searchList);
		printDetails();
	}

	private void dateSearch() {
		clear();
		searchList = cm.checkClaimByDate(askDate());
		printList(searchList);
		printDetails();
	}

	private void claimantAndDateSearch() {
		String[] names;

		clear();
		names = askClientNames();
		searchList = cm
				.checkClaimByClientAndDate(names[0], names[1], askDate());
		printList(searchList);
		printDetails();
	}

	private void printDetails() {
		System.out
				.print("\nEnter the index of the claim you want to see detailed: ");
		String choice = sc.nextLine();

		while (!inputCheck(choice, searchList.size())) {
			System.out.print("\nPlease choose a VALID index: ");
			choice = sc.nextLine();
		}
		int choiceInt;
		if (Integer.parseInt(choice) <= 0) {
			choiceInt = 1;
		} else {
			choiceInt = Integer.parseInt(choice);
		}

		System.out.println(searchList.get(choiceInt - 1));
	}

	// asks the user to enter a date
	private String askDate() {
		System.out.print("Enter a date dd/mm/yyyy format: ");
		return sc.nextLine();
	}
}
