package view;

import java.util.ArrayList;

/**
 * Abstract class for search views.
 *
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
    protected abstract void printDetails();
}
