package ui;

import java.util.ArrayList;
import java.util.Scanner;

import user.UserAccount;
import claim.Claim;
import claim.ClaimManager;

public class SearchClaimUi {

	// Attributes
	private Scanner sc;
	private UserAccount ua;
	private ClaimManager cm;
	private ArrayList<Claim> searchList;

	// Constructors
	public SearchClaimUi(UserAccount um) {
		sc = new Scanner(System.in);
		this.ua = um;
		cm = new ClaimManager();
		display();
	}

	public SearchClaimUi() {
		this(new UserAccount());
	}

	private void display() {
		MenuUi.clear();
		System.out
				.println("1. Search claim by claimant\n2. Search claim by date\n3. Search claim by claimant AND date");
		System.out.print("\nPlease choose an option:");
		int option = sc.nextInt();
		sc.nextLine();

		while (!MenuUi.inputCheck(option, 3)) {
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
		MenuUi.clear();
		System.out.print("Firstname: ");
		String firstname = sc.nextLine();
		System.out.print("\nLastname: ");
		String lastname = sc.nextLine();
		searchList = cm.checkClaimByClient(firstname, lastname);
		printList();
		printDetails();
	}

	private void DateSearch() {
		MenuUi.clear();
		System.out.print("Date: ");
		String date = sc.nextLine();
		searchList = cm.checkClaimByDate(date);
		printList();
		printDetails();
	}

	private void ClaimantAndDateSearch() {
		MenuUi.clear();
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

		while (!MenuUi.inputCheck(choice, searchList.size())) {
			System.out.print("\nPlease choose a VALID index: ");
			choice = sc.nextInt();
			sc.nextLine();
		}

		System.out.println(searchList.get(choice - 1));
	}
}