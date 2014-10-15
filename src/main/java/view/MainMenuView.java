package view;

import payment.PaymentManager;
import user.UserAccount;
import user.UserManager;
import claim.Category;
import claim.Claim;
import claim.ClaimManager;
import claim.ClaimStatus;
import client.Client;
import client.ClientManager;

import communication.Form;
import communication.FormType;

/**
 * Created by root on 11/10/14.
 */
public class MainMenuView extends View {

    // Attributes
    private UserAccount ua;
    private ClientManager cm;
    private ClaimManager claimManager;
    private PaymentManager paymentManager;

    private static int ABCDOPTIONSNUMBER = 10;
    private static int CDOPTIONSNUMBER = 6;
    private static int FINOPTIONSNUMBER = 6;

    // Constructors
    public MainMenuView(UserAccount um, ClientManager cm,
	    ClaimManager claimManager, PaymentManager pm) {
	super();
	this.ua = um;
	this.cm = cm;
	this.claimManager = claimManager;
	this.paymentManager = pm;
	display();
    }

    // Methods

    @Override
    protected void display() {
	clear();

	switch (ua.getRank()) {
	case ACD:
	    menuABcd();
	    break;
	case BCD:
	    menuABcd();
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

    private void printSharedMenu() {
	System.out
		.println("0. Logout\n1. Check if client insured\n2. Search claim\n3. Search client\n4. Create client");
    }

    // Prints the options available for a CD employee
    private void printCDSharedMenu() {
	System.out.println("5. Register claim\n6. Send letter");
    }

    // TODO refactor?
    private void menuABcd() {
	printSharedMenu();
	printCDSharedMenu();
	System.out
		.println("7. Send a form to a client\n8. Check a form\n9. Classify a claim\n10. Make a decision about a claim");

	switch (askOption(ABCDOPTIONSNUMBER)) {
	case 1:
	    checkClient();
	    break;
	case 2:
	    new SearchClaimView(ua, claimManager, ClaimAction.UNDEFINED);
	    break;
	case 3:
	    new SearchClientView(cm);
	    break;
	case 4:
	    addClient();
	    break;
	case 5:
	    addClaim();
	    break;
	case 6:
	    sendLetter();
	    break;
	case 7:
	    sendForm();
	    break;
	case 8:
	    checkForm();
	    break;
	case 9:
	    new SearchClaimView(ua, claimManager, ClaimAction.CLASSIFY);
	    break;
	case 10:
	    new SearchClaimView(ua, claimManager, ClaimAction.MAKEDECISION);
	    break;
	case 0:
	    new LoginView(new UserManager(), this.cm, this.claimManager,
		    this.paymentManager);
	}
    }

    private void menuCd() {
	printSharedMenu();
	printCDSharedMenu();

	switch (askOption(CDOPTIONSNUMBER)) {
	case 1:
	    checkClient();
	    break;
	case 2:
	    new SearchClaimView(ua, claimManager, ClaimAction.UNDEFINED);
	    break;
	case 3:
	    new SearchClientView();
	    break;
	case 4:
	    addClient();
	    break;
	case 5:
	    addClaim();
	    break;
	case 6:
	    sendLetter();
	    break;
	case 0:
	    new LoginView(new UserManager(), this.cm, this.claimManager,
		    this.paymentManager);
	}
    }

    private void menuFin() {
	printSharedMenu();
	System.out.println("5. Proceed payment");
	System.out.println("6. Search payment");

	switch (askOption(FINOPTIONSNUMBER)) {
	case 1:
	    checkClient();
	    break;
	case 2:
	    new SearchClaimView(ua, claimManager, ClaimAction.UNDEFINED);
	    break;
	case 3:
	    new SearchClientView();
	    break;
	case 4:
	    addClient();
	    break;
	case 5:
	    new ProceedPaymentView(ua, paymentManager, claimManager);
	    break;
	case 6:
	    new SearchPaymentView(paymentManager);
	    break;
	case 0:
	    new LoginView(new UserManager(), this.cm, this.claimManager,
		    this.paymentManager);

	}
    }

    // Allows the user to send a letter to a client about his claim's status
    private void sendLetter() {
	System.out
		.println("First find the claim you want to send a letter about.");
	new SearchClaimView(ua, claimManager, ClaimAction.SENDLETTER);
    }

    private void mainMenuReturn() {
	System.out.println("\n\nPress enter to get back to the main menu... ");
	sc.nextLine();
	new MainMenuView(this.ua, this.cm, this.claimManager,
		this.paymentManager);
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

    private Client addClient() {
	String[] names = askClientNames();

	if (cm.checkClient(names[0], names[1]) == null) {
	    do {
		System.out.print("\nInsurance (A, B, C or D): ");
	    } while (!cm.addClient(names[0], names[1], sc.nextLine().charAt(0)));
	}

	else {
	    System.out.println("Client already exists.");
	}

	return cm.checkClient(names[0], names[1]);
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
	Claim newClaim = new Claim(Integer.parseInt(carPrice),
		Integer.parseInt(damageCost), carHistory,
		ClaimStatus.REGISTERED, client, date, Category.undefined);
	claimManager.addClaim(newClaim);
	cm.addClaimToClient(client, newClaim);
    }

    private void checkForm() {
	String[] names = askClientNames();
	Form form = cm.checkForm(names[0], names[1], askFormType());

	System.out.println(form);
    }
}