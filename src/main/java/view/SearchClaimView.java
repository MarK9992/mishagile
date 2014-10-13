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
		int option = sc.nextInt();
		sc.nextLine();

		while (!MainMenuView.inputCheck(option, 3)) {
			System.out.print("\nPlease choose a VALID option: ");
			option = sc.nextInt();
			sc.nextLine();
		}

		switch (option) {
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
		clear();
		System.out.print("Firstname: ");
		String firstname = sc.nextLine();
		System.out.print("\nLastname: ");
		String lastname = sc.nextLine();
		searchList = cm.checkClaimByClient(firstname, lastname);
		printList();
		printDetails();
	}

	private void DateSearch() {
		clear();
		System.out.print("Date: ");
		String date = sc.nextLine();
		searchList = cm.checkClaimByDate(date);
		printList();
		printDetails();
	}

	private void ClaimantAndDateSearch() {
		clear();
		System.out.print("Firstname: ");
		String firstname = sc.nextLine();
		System.out.print("\nLastname: ");
		String lastname = sc.nextLine();
		System.out.print("\nDate: ");
		String date = sc.nextLine();
		searchList = cm.checkClaimByClientAndDate(firstname, lastname, date);
		printList();
		printDetails();
	}

	private void printDetails() {
		System.out
				.print("\nEnter the index of the claim you want to see detailed: ");
		int choice = sc.nextInt();
		sc.nextLine();

		if (choice <= 0) {
			choice = 1;
		}

		while (!MainMenuView.inputCheck(choice, searchList.size())) {
			System.out.print("\nPlease choose a VALID index: ");
			choice = sc.nextInt();
			sc.nextLine();
		}

		System.out.println(searchList.get(choice - 1));
	}
}
