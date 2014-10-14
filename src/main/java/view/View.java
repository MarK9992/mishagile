package view;

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
		for (int i = 0; i < 20; i++) {
			System.out.println();
		}
	}

    /**
     *  Asks user to enter a name and a firstname and returns the inputs.
     *
      * @return a String[2] containing the firstname at index 0 and the name at index 1
     */
    protected String[] askClientNames() {
        String[] names = new String[2];

        System.out.print("Enter client's name and firstname.\nname: ");
        names[1] = sc.nextLine();
        System.out.print("firstname");
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
                    System.out.print("Please answer correctly! (Y/N)");
            }
        } while(true);
    }
}
