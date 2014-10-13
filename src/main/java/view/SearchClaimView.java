package view;

import java.util.ArrayList;

import user.UserAccount;
import claim.Claim;
import claim.ClaimManager;

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
			ClaimantSearch();
			break;
		case 2:
			DateSearch();
			break;

		case 3:
			ClaimantAndDateSearch();
			break;
		}
	}

	private void printList() {
		int index = 1;
		for (Claim cm : searchList) {
			System.out.println(index + ". " + cm.getDate() + " "
					+ cm.getClaimant());
		}
	}

	private void ClaimantSearch() {
        String[] names;

        clear();
        names = askClientNames();
		searchList = cm.checkClaimByClient(names[0], names[1]);
		printList();
		printDetails();
	}

	private void DateSearch() {
		clear();
		searchList = cm.checkClaimByDate(askDate());
		printList();
		printDetails();
	}

    // asks the user to enter a date
    private String askDate() {
        System.out.print("Enter a date dd/mm/yyyy format: ");
        return sc.nextLine();
    }

	private void ClaimantAndDateSearch() {
        String[] names;

        clear();
        names = askClientNames();
		searchList = cm.checkClaimByClientAndDate(names[0], names[1], askDate());
		printList();
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
}
