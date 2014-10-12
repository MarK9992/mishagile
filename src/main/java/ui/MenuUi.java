package ui;

import client.ClientManager;
import user.UserAccount;
import user.UserManager;

import java.util.Scanner;

import static user.UserRank.*;

/**
 * Created by root on 11/10/14.
 */
public class MenuUi {

	// Attributes
	private Scanner sc;
	private UserAccount ua;
	private ClientManager cm;

	// Constructors
	public MenuUi(UserAccount um) {
		sc = new Scanner(System.in);
		this.ua = um;
		cm = new ClientManager();
		display();
	}

	public MenuUi() {
		this(new UserAccount());
	}

	// Methods
	private void display() {
		clear();

		switch (ua.getRank()) {

		case ACD:
			menuAcd();
			break;

		case BCD:
			menuBcd();
			break;

		case CD:
			menuCd();
			break;

		case FIN:
			menuFin();
			break;

		}

		mainMenuReturn();
	}

	public static boolean inputCheck(int input, int max) {
		return input <= max;
	}

	// TODO refactor?
	private void menuAcd() {
		System.out
				.println("1. Check if client insured\n2. Search claim\n0. Logout");
		System.out.print("\nPlease choose an option:");
		int option = sc.nextInt();
		sc.nextLine();

		// TODO: Remplacer 4 par le nombre de choix
		while (!inputCheck(option, 4)) {
			System.out.print("\nPlease choose a VALID option: ");
			option = sc.nextInt();
			sc.nextLine();
		}

		switch (option) {
		case 1:
			checkClient();
			break;
		case 2:
			new SearchClaimUi();
			break;

		case 3:
			// do something

		case 4:
			// do something

		case 0:
			new LoginUi();
		}
	}

	private void menuBcd() {
		System.out
				.println("1. Check if client insured\n2. Search claim\n0. Logout");
		System.out.print("\nPlease choose an option:");
		int option = sc.nextInt();
		sc.nextLine();

		// TODO: Remplacer 4 par le nombre de choix
		while (!inputCheck(option, 4)) {
			System.out.print("\nPlease choose a VALID option: ");
			option = sc.nextInt();
			sc.nextLine();
		}

		switch (option) {
		case 1:
			checkClient();
			break;
		case 2:
			new SearchClaimUi();
			break;

		case 3:
			// do something

		case 4:
			// do something

		case 0:
			new LoginUi();
		}
	}

	private void menuCd() {
		System.out
				.println("1. Check if client insured\n2. Search claim\n0. Logout");
		System.out.print("\nPlease choose an option:");
		int option = sc.nextInt();
		sc.nextLine();

		// TODO: Remplacer 4 par le nombre de choix
		while (!inputCheck(option, 4)) {
			System.out.print("\nPlease choose a VALID option: ");
			option = sc.nextInt();
			sc.nextLine();
		}

		switch (option) {
		case 1:
			checkClient();
			break;
		case 2:
			new SearchClaimUi();
			break;

		case 3:
			// do something

		case 4:
			// do something

		case 0:
			new LoginUi();
		}
	}

	private void menuFin() {
		System.out
				.println("1. Check if client insured\n2. Search claim\n0. Logout");
		System.out.print("\nPlease choose an option:");
		int option = sc.nextInt();
		sc.nextLine();

		// TODO: Remplacer 4 par le nombre de choix
		while (!inputCheck(option, 4)) {
			System.out.print("\nPlease choose a VALID option: ");
			option = sc.nextInt();
			sc.nextLine();
		}

		switch (option) {
		case 1:
			checkClient();
			break;
		case 2:
			new SearchClaimUi();
			break;

		case 3:
			// do something

		case 4:
			// do something

		case 0:
			new LoginUi();
		}
	}

	public static void clear() {
		for (int i = 0; i < 20; i++) {
			System.out.println();
		}
	}

	private void mainMenuReturn() {
		System.out.println("\n\nPress enter to get back to the main menu... ");
		sc.nextLine();
		new MenuUi(this.ua);
	}

	private void checkClient() {
		String name, firstname;

		System.out.println("Enter client's name and firstname.\nname: ");
		name = sc.nextLine();
		System.out.println("firstname");
		firstname = sc.nextLine();
		if (cm.checkClient(firstname, name) != null) {
			System.out.println("Client insured.");
		} else {
			System.out.println("Client not insured.");
		}
	}
}