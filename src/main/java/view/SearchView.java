package view;

import java.util.ArrayList;

/**
 * Abstract class for search views.
 * <p/>
 * Created by marc on 14/10/14.
 */
public abstract class SearchView<E> extends View {

    // Attributes

    protected ArrayList<E> results;

    // Constructors

    /**
     * Default constructor.
     */
    public SearchView() {
        super();
        results = new ArrayList<E>();
    }

    // Methods

    /**
     * Asks the user if he would like to print the details of a result entry.
     */
    protected void printDetails(String question) {
        if (results != null) {
            System.out.print(question);
            String choice = sc.nextLine();

            while (!inputCheck(choice, results.size())) {
                System.out.print("\nPlease choose a VALID index: ");
                choice = sc.nextLine();
            }
            int choiceInt;
            if (Integer.parseInt(choice) <= 0) {
                choiceInt = 1;
            } else {
                choiceInt = Integer.parseInt(choice);
            }

            System.out.println("\n" + results.get(choiceInt - 1));
        }
    }
}
