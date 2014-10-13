package view;

import client.ClientManager;
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

    // Constructors
    public MainMenuView(UserAccount um) {
        super();
        this.ua = um;
        cm = new ClientManager();
        display();
    }

    public MainMenuView() {
        this(new UserAccount());
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

    public static boolean inputCheck(int input, int max) {
        return input <= max;
    }

    // TODO refactor?
    private void menuAcd() {
        System.out
                .println("1. Check if client insured\n2. Search claim\n3. Send form to client\n0. Logout");
        System.out.print("\nPlease choose an option:");
        int option = sc.nextInt();
        sc.nextLine();

        // TODO: Remplacer 4 par le nombre de choix
        while (!inputCheck(option, 4)) {
            System.out.print("\nPlease choose a VALID option: ");
            option = sc.nextInt();
            sc.nextLine();
        }

        switch (option) {
            case 1:
                checkClient();
                break;
            case 2:
                new SearchClaimView();
                break;
            case 3:
                sendForm();
                break;
            case 4:
                // do something

            case 0:
                new LoginView();
        }
    }

    private void menuBcd() {
        System.out
                .println("1. Check if client insured\n2. Search claim\n3. Send form to client\n0. Logout");
        System.out.print("\nPlease choose an option:");
        int option = sc.nextInt();
        sc.nextLine();

        // TODO: Remplacer 4 par le nombre de choix
        while (!inputCheck(option, 4)) {
            System.out.print("\nPlease choose a VALID option: ");
            option = sc.nextInt();
            sc.nextLine();
        }

        switch (option) {
            case 1:
                checkClient();
                break;
            case 2:
                new SearchClaimView();
                break;
            case 3:
                sendForm();
                break;
            case 4:
                // do something

            case 0:
                new LoginView();
        }
    }

    private void menuCd() {
        System.out
                .println("1. Check if client insured\n2. Search claim\n3. Send form to client\n0. Logout");
        System.out.print("\nPlease choose an option: ");
        int option = sc.nextInt();
        sc.nextLine();

        // TODO: Remplacer 4 par le nombre de choix
        while (!inputCheck(option, 4)) {
            System.out.print("\nPlease choose a VALID option: ");
            option = sc.nextInt();
            sc.nextLine();
        }

        switch (option) {
            case 1:
                checkClient();
                break;
            case 2:
                new SearchClaimView();
                break;
            case 3:
                sendForm();
                break;
            case 4:
                // do something

            case 0:
                new LoginView();
        }
    }

    private void menuFin() {
        System.out
                .println("1. Check if client insured\n2. Search claim\n3. Send form to client\n0. Logout");
        System.out.print("\nPlease choose an option:");
        int option = sc.nextInt();
        sc.nextLine();

        // TODO: Remplacer 4 par le nombre de choix
        while (!inputCheck(option, 4)) {
            System.out.print("\nPlease choose a VALID option: ");
            option = sc.nextInt();
            sc.nextLine();
        }

        switch (option) {
            case 1:
                checkClient();
                break;
            case 2:
                new SearchClaimView();
                break;
            case 3:
                sendForm();
                break;
            case 4:
                // do something

            case 0:
                new LoginView();
        }
    }

    private void mainMenuReturn() {
        System.out.println("\n\nPress enter to get back to the main menu... ");
        sc.nextLine();
        new MainMenuView(this.ua);
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
        Boolean bool;

        System.out.println("What kind of form would you like to send ? (A, B or C, 0 to exit)");
        do {
            switch (sc.nextLine().charAt(0)) {
                case 'A':
                    performFormSending(names, FormType.A);
                    bool = true;
                    break;
                case 'a':
                    performFormSending(names, FormType.A);
                    bool = true;
                    break;
                case 'B':
                    performFormSending(names, FormType.B);
                    bool = true;
                    break;
                case 'b':
                    performFormSending(names, FormType.B);
                    bool = true;
                    break;
                case 'C':
                    performFormSending(names, FormType.C);
                    bool = true;
                    break;
                case 'c':
                    performFormSending(names, FormType.C);
                    bool = true;
                    break;
                case '0':
                    bool = true;
                    break;
                default:
                    System.out.println("Please enter a correct input. (A, B or C, 0 to exit)");
                    bool = false;
            }
        } while (!bool);
    }

    // performs the sending of a form
    private void performFormSending(String[] names, FormType formType) {
        if (cm.sendForm(names[0], names[1], formType)) {
            System.out.println("Sending successfull");
        } else {
            System.out.println("Error client does not exist.");
        }
    }

    // asks user to enter a name and a firstname and returns the inputs
    private String[] askClientNames() {
        String[] names = new String[2];

        System.out.println("Enter client's name and firstname.\nname: ");
        names[2] = sc.nextLine();
        System.out.println("firstname");
        names[1] = sc.nextLine();
        return names;
    }
}