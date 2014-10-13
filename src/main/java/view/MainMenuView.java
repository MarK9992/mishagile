package view;

import claim.ClaimManager;
import client.ClientManager;
import communication.Form;
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
		while (!inputCheck(option, 6)) {
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
        case 6:
            checkForm();
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
		while (!inputCheck(option, 6)) {
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
        case 6:
            checkForm();
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
        FormType type = askFormType();

        if (cm.sendForm(names[0], names[1], type)) {
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

	private void addClient() {
		String[] names = askClientNames();

		if (cm.checkClient(names[0], names[1]) == null) {
			do {
				System.out.print("\nInsurance (A, B, C or D): ");
			} while (!cm.addClient(names[0], names[1], sc.nextLine().charAt(0)));
		}

		else {
			System.out.println("Client already exists!");
		}
	}

	private void addClaim() {

	}

    private void checkForm() {
        String[] names = askClientNames();
        Form form = cm.checkForm(names[0], names[1], askFormType());

        System.out.println(form);
    }

    private FormType askFormType() {
        System.out.println("What kind of form ? (A, B, or C)");
        do {
            switch (sc.nextLine().charAt(0)) {
                case 'A':
                    return FormType.A;
                case 'a':
                    return FormType.A;
                case 'B':
                    return FormType.B;
                case 'b':
                    return FormType.B;
                case 'C':
                    return FormType.C;
                case 'c':
                    return FormType.C;
                default:
                    System.out
                            .println("Please enter a correct input. (A, B or C)");
            }
        } while (true);
    }
}