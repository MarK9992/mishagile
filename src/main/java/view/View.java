package view;

import claim.Claim;
import client.Insurance;
import communication.FormType;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by root on 12/10/14.
 */
public abstract class View {

    // Attributes

    protected Scanner sc;

    // Constructors

    /**
     * Default constructor.
     */
    public View() {
	sc = new Scanner(System.in);
    }

    // Methods

    /**
     * Displays the view.
     */
    protected abstract void display();

    /**
     * Clears console.
     */
    protected void clear() {
	for (int i = 0; i < 4; i++) {
	    System.out.println();
	}
    }

    /**
     * Asks user to enter a name and a firstname and returns the inputs.
     * 
     * @return a String[2] containing the firstname at index 0 and the name at
     *         index 1
     */
    protected String[] askClientNames() {
	String[] names = new String[2];

	System.out.print("\nEnter client's name and firstname.\n\nName: ");
	names[1] = sc.nextLine();
	System.out.print("Firstname: ");
	names[0] = sc.nextLine();
	return names;
    }

    protected boolean inputCheck(String input, int max) {
	int option;

	try {
	    option = Integer.parseInt(input);
	} catch (NumberFormatException e) {
	    return false;
	}
	return option <= max;
    }

    /**
     * Catches a yes or no input from the user.
     * 
     * @return true if yes, false otherwise
     */
    protected boolean getYesNo() {
	do {
	    switch (sc.nextLine().charAt(0)) {
	    case 'Y':
		return true;
	    case 'y':
		return true;
	    case 'N':
		return false;
	    case 'n':
		return false;
	    default:
		System.out.print("Please answer correctly! (Y/N) ");
	    }
	} while (true);
    }

    /**
     * Asks the user to enter an insurance
     * 
     * @return the insurance
     */
    protected Insurance askInsurance() {
	System.out.print("What kind of insurance? (A, B, C, or D) ");

	do {
	    switch (sc.nextLine().charAt(0)) {
	    case 'A':
		return Insurance.A;
	    case 'a':
		return Insurance.A;
	    case 'B':
		return Insurance.B;
	    case 'b':
		return Insurance.B;
	    case 'C':
		return Insurance.C;
	    case 'c':
		return Insurance.C;
	    case 'D':
		return Insurance.D;
	    case 'd':
		return Insurance.D;
	    default:
		System.out.print("Please answer correctly! (A, B, C, or D) ");
	    }
	} while (true);
    }

    /**
     * Asks the user to enter a date.
     * 
     * @return the string date
     */
    protected String askDate() {
	System.out.print("Enter a date (dd/mm/yyyy format): ");
	return sc.nextLine();
    }

    protected void printClaimList(ArrayList<Claim> claims) {
	if (claims != null) {
	    int index = 1;
	    for (Claim cm : claims) {
		System.out.println("\n" + index + ". " + cm.getDate() + " "
			+ cm.getClaimant().namesToString() + " "
			+ cm.getStatus().toString());
	    }
	} else {
	    System.out.println("\nNo claim was found...");
	}
    }

    /**
     * Asks the user to enter an option between 0 and maxoption.
     * 
     * @param maxoption
     *            the maximum input
     * @return the input
     */
    protected int askOption(int maxoption) {
	System.out.print("\nPlease choose an option: ");
	String option = sc.nextLine();

	while (!inputCheck(option, maxoption)) {
	    System.out.print("\nPlease choose a VALID option: ");
	    option = sc.nextLine();
	}

	return Integer.parseInt(option);
    }

    /**
     * Asks the user to enter a form type.
     * 
     * @return the formtype
     */
    protected FormType askFormType() {
	System.out.println("What kind of form? (A, B, or C) ");
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
			.println("Please enter a correct input (A, B or C): ");
	    }
	} while (true);
    }

    protected boolean isInt(String input) {
	try {
	    Integer.parseInt(input);
	} catch (NumberFormatException e) {
	    return false;
	}
	return true;
    }
}
