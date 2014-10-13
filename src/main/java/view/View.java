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
}
