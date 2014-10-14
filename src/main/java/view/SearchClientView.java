package view;

import client.Client;
import client.ClientManager;

import java.util.ArrayList;

/**
 * View for client search.
 * 
 * Created by marc on 14/10/14.
 */
public class SearchClientView extends View {

	// Attributes

	private ClientManager clientManager;
	private ArrayList<Client> results;

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
	 * @param clientManager
	 *            the manager to construct the view with
	 */
	public SearchClientView(ClientManager clientManager) {
		super();
		this.clientManager = clientManager;
	}

	// Methods

	/**
	 * Displays the view.
	 */
	protected void display() {
		clear();
		System.out.println("1. Search by names\n2. Search by insurance.");
		System.out.print("\nPlease choose an option:");
		String option = sc.nextLine();

		while (!inputCheck(option, 2)) {
			System.out.print("\nPlease choose a VALID option: ");
			option = sc.nextLine();
		}
		switch (Integer.parseInt(option)) {
		case 1:
			namesSearch();
			break;
		case 2:
			insuranceSearch();
			break;
		default:
		}
		printList(results);
		printDetails();
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
		int index = 1;

		for (Client client : clients) {
			System.out.println(index + ". " + client.namesToString() + " "
					+ client.getInsurance());
		}
	}

	// Prints the details of an entry of a resulting list
	private void printDetails() {
		int choiceInt;

		System.out
				.print("\nEnter the index of the client you want to see detailed (0 exit): ");
		String choice = sc.nextLine();

		while (!inputCheck(choice, results.size())) {
			System.out.print("\nPlease choose a VALID index: ");
			choice = sc.nextLine();
		}
		choiceInt = Integer.parseInt(choice);
		if (choiceInt > 0) {
			System.out.println(results.get(choiceInt - 1));
		}
	}
}
