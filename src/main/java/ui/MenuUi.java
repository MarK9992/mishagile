package ui;

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

	// Constructors
	public MenuUi(UserAccount um) {
		sc = new Scanner(System.in);
		this.ua = um;
		display();
	}

	public MenuUi() {
		this(new UserAccount());
	}

	// Methods
	public void display() {
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
	}

	private boolean inputCheck(int input, int max) {
		return input <= max;
	}

	// TODO refactor?
	private void menuAcd() {
		clear();
		System.out.println("MENU ACD...");
		System.out.print("\nPlease choose an option: ");
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
			// do something

		case 2:
			// do something

		case 3:
			// do something

		case 4:
			// do something

		case 0:
			new LoginUi();
		}
	}

	private void menuBcd() {
		clear();
		System.out.println("MENU BCD...");
		System.out.print("\nPlease choose an option: ");
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
			// do something

		case 2:
			// do something

		case 3:
			// do something

		case 4:
			// do something

		case 0:
			new LoginUi();
		}
	}

	private void menuCd() {
		clear();
		System.out.println("MENU CD...");
		System.out.print("\nPlease choose an option: ");
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
			// do something

		case 2:
			// do something

		case 3:
			// do something

		case 4:
			// do something

		case 0:
			new LoginUi();
		}
	}

	private void menuFin() {
		clear();
		System.out.println("MENU FIN...");
		System.out.print("\nPlease choose an option: ");
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
			// do something

		case 2:
			// do something

		case 3:
			// do something

		case 4:
			// do something

		case 0:
			new LoginUi();
		}
	}

	private void clear() {
		for (int i = 0; i < 20; i++) {
			System.out.println();
		}
	}
}