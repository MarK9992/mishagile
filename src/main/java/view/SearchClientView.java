package view;

import client.Client;
import client.ClientManager;

import java.util.ArrayList;

/**
 * View for client search.
 * <p/>
 * Created by marc on 14/10/14.
 */
public class SearchClientView extends SearchView<Client> {

    // Attributes

    private ClientManager clientManager;

    // Constructors

    /**
     * Default constructor.
     */
    public SearchClientView() {
        this(new ClientManager());
    }

    /**
     * Constructs a new SearchClientView object with the given ClientManager.
     *
     * @param clientManager the manager to construct the view with
     */
    public SearchClientView(ClientManager clientManager) {
        super();
        this.clientManager = clientManager;
        display();
    }

    // Methods

    /**
     * Displays the view.
     */
    protected void display() {
        clear();
        System.out.println("1. Search by names\n2. Search by insurance.");
        switch (askOption(2)) {
            case 1:
                namesSearch();
                break;
            case 2:
                insuranceSearch();
                break;
            default:
        }
        printList(results);
        printDetails("\nEnter the index of the client you want to see detailed (0 exit): ");
    }

    // Searches clients by name
    private void namesSearch() {
        String[] names;

        names = askClientNames();
        results = clientManager.searchClient(names[0], names[1]);
    }

    // Searches clients by insurance
    private void insuranceSearch() {
        results = clientManager.searchClient(askInsurance());
    }

    // Prints a resulting list of clients
    private void printList(ArrayList<Client> clients) {
        if (clients.size() > 0) {
            int index = 1;

            for (Client client : clients) {
                System.out.println("\n" + index + ". " + client.namesToString()
                        + " " + client.getInsurance());
            }
        } else {
            System.out.println("No client was found...");
        }
    }
}
