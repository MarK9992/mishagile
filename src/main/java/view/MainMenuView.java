package view;

import claim.Claim;
import claim.ClaimManager;
import claim.ClaimStatus;
import client.Client;
import client.ClientManager;
import communication.FormType;
import user.UserAccount;

import java.util.Scanner;

/**
 * Created by root on 11/10/14.
 */
public class MainMenuView extends View {

	// Attributes
	private UserAccount ua;
	private ClientManager cm;
	private ClaimManager claimManager;

	// Constructors
	public MainMenuView(UserAccount um, ClientManager cm,
			ClaimManager claimManager) {
		super();
		this.ua = um;
		this.cm = cm;
		this.claimManager = claimManager;
		display();
	}

	public MainMenuView() {
		this(new UserAccount(), new ClientManager(), new ClaimManager());
	}

	// Methods
	protected void display() {
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

	public static boolean inputCheck(String input, int max) {
		int option;
		try {
			option = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return false;
		}
		return option <= max;
	}

	private void printSharedMenu() {
		System.out
				.println("1. Check if client insured\n2. Search claim\n3. Send form to client\n4. Create client\n5. Register claim\n0. Logout");
	}

	// TODO refactor?
	private void menuAcd() {
		printSharedMenu();
		System.out.print("\nPlease choose an option:");
		String option = sc.nextLine();

		// TODO: Remplacer 4 par le nombre de choix
		while (!inputCheck(option, 5)) {
			System.out.print("\nPlease choose a VALID option: ");
			option = sc.nextLine();
		}

		switch (Integer.parseInt(option)) {
		case 1:
			checkClient();
			break;
		case 2:
			new SearchClaimView(ua, claimManager);
			break;
		case 3:
			sendForm();
			break;
		case 4:
			addClient();
			break;
		case 5:
			addClaim();
			break;
		case 0:
			new LoginView();
		}
	}

	private void menuBcd() {
		printSharedMenu();
		System.out.print("\nPlease choose an option:");
		String option = sc.nextLine();

		// TODO: Remplacer 4 par le nombre de choix
		while (!inputCheck(option, 5)) {
			System.out.print("\nPlease choose a VALID option: ");
			option = sc.nextLine();
		}

		switch (Integer.parseInt(option)) {
		case 1:
			checkClient();
			break;
		case 2:
			new SearchClaimView(ua, claimManager);
			break;
		case 3:
			sendForm();
			break;
		case 4:
			addClient();
			break;
		case 5:
			addClaim();
			break;

		case 0:
			new LoginView();
		}
	}

	private void menuCd() {
		printSharedMenu();
		System.out.print("\nPlease choose an option:");
		String option = sc.nextLine();

		// TODO: Remplacer 4 par le nombre de choix
		while (!inputCheck(option, 5)) {
			System.out.print("\nPlease choose a VALID option: ");
			option = sc.nextLine();
		}

		switch (Integer.parseInt(option)) {
		case 1:
			checkClient();
			break;
		case 2:
			new SearchClaimView(ua, claimManager);
			break;
		case 3:
			sendForm();
			break;
		case 4:
			addClient();
			break;
		case 5:
			addClaim();
			break;
		case 0:
			new LoginView();
		}
	}

	private void menuFin() {
		printSharedMenu();
		System.out.print("\nPlease choose an option:");
		String option = sc.nextLine();

		// TODO: Remplacer 4 par le nombre de choix
		while (!inputCheck(option, 5)) {
			System.out.print("\nPlease choose a VALID option: ");
			option = sc.nextLine();
		}

		switch (Integer.parseInt(option)) {
		case 1:
			checkClient();
			break;
		case 2:
			new SearchClaimView(ua, claimManager);
			break;
		case 3:
			sendForm();
			break;
		case 4:
			addClient();
			break;
		case 5:
			addClaim();
			break;
		case 0:
			new LoginView();
		}
	}

	private void mainMenuReturn() {
		System.out.println("\n\nPress enter to get back to the main menu... ");
		sc.nextLine();
		new MainMenuView(this.ua, this.cm, this.claimManager);
	}

	// asks user to enter a name and a firstname and checks if he's insured
	private void checkClient() {
		String[] names = askClientNames();

		if (cm.checkClient(names[0], names[1]) != null) {
			System.out.println("Client insured.");
		} else {
			System.out.println("Client not insured.");
		}
	}

	// sends a form to a given client
	private void sendForm() {
		String[] names = askClientNames();
		Boolean bool;

		System.out
				.println("What kind of form would you like to send ? (A, B or C, 0 to exit)");
		do {
			switch (sc.nextLine().charAt(0)) {
			case 'A':
				performFormSending(names, FormType.A);
				bool = true;
				break;
			case 'a':
				performFormSending(names, FormType.A);
				bool = true;
				break;
			case 'B':
				performFormSending(names, FormType.B);
				bool = true;
				break;
			case 'b':
				performFormSending(names, FormType.B);
				bool = true;
				break;
			case 'C':
				performFormSending(names, FormType.C);
				bool = true;
				break;
			case 'c':
				performFormSending(names, FormType.C);
				bool = true;
				break;
			case '0':
				bool = true;
				break;
			default:
				System.out
						.println("Please enter a correct input. (A, B or C, 0 to exit)");
				bool = false;
			}
		} while (!bool);
	}

	// performs the sending of a form
	private void performFormSending(String[] names, FormType formType) {
		if (cm.sendForm(names[0], names[1], formType)) {
			System.out.println("Sending successfull");
		} else {
			System.out.println("Error client does not exist.");
		}
	}

	// asks user to enter a name and a firstname and returns the inputs
	private String[] askClientNames() {
		String[] names = new String[2];

		System.out.println("Enter client's name and firstname.\nname: ");
		names[1] = sc.nextLine();
		System.out.println("firstname");
		names[0] = sc.nextLine();
		return names;
	}

	private Client addClient() {
		String firstname, name;
		System.out.print("Client's firstname: ");
		firstname = sc.nextLine();
		System.out.print("\nClient's name: ");
		name = sc.nextLine();

		if (cm.checkClient(firstname, name) == null) {
			do {
				System.out.print("\nInsurance (A, B, C or D): ");
			} while (!cm.addClient(firstname, name, sc.nextLine().charAt(0)));
		}

		else {
			System.out.println("Client already exists.");
		}

		return cm.checkClient(firstname, name);
	}

	private boolean isInt(String input) {
		try {
			int test = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private void addClaim() {
		Client client = addClient();
		String date, carHistory, carPrice, damageCost;
		System.out.print("Date: ");
		date = sc.nextLine();
		System.out.print("\nCar history: ");
		carHistory = sc.nextLine();
		System.out.print("\nCar price: ");
		carPrice = sc.nextLine();

		while (!isInt(carPrice)) {
			System.out.print("\nPlease enter a VALID price: ");
			carPrice = sc.nextLine();
		}

		System.out.print("\nDamage cost: ");
		damageCost = sc.nextLine();

		while (!isInt(damageCost)) {
			System.out.print("\nPlease enter a VALID cost: ");
			damageCost = sc.nextLine();
		}
		claimManager.addClaim(new Claim(Integer.parseInt(carPrice), Integer
				.parseInt(damageCost), carHistory, ClaimStatus.REGISTERED,
				client, date));
	}

}