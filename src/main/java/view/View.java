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
}
