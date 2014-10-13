package view;

import java.util.ArrayList;

import claim.Category;
import user.UserAccount;
import claim.Claim;
import claim.ClaimManager;
import user.UserRank;

public class SearchClaimView extends View {

	// Attributes
	private UserAccount ua;
	private ClaimManager cm;
	private ArrayList<Claim> searchList;

	// Constructors
	public SearchClaimView(UserAccount um, ClaimManager cm) {
		super();
		this.ua = um;
		this.cm = cm;
		display();
	}

	public SearchClaimView() {
		this(new UserAccount(), new ClaimManager());
	}

	protected void display() {
        ArrayList<Claim> result= null;

		clear();
		System.out
				.println("1. Search claim by claimant\n2. Search claim by date\n3. Search claim by claimant AND date");
		System.out.print("\nPlease choose an option:");
		String option = sc.nextLine();

		while (!inputCheck(option, 3)) {
			System.out.print("\nPlease choose a VALID option: ");
			option = sc.nextLine();
		}

		switch (Integer.parseInt(option)) {
            case 1:
                result = claimantSearch();
                break;
            case 2:
                result = dateSearch();
                break;

            case 3:
                result = claimantAndDateSearch();
                break;
		}

        if(result != null && (ua.getRank() == UserRank.ACD || ua.getRank() == UserRank.BCD)) {
            classifyClaim(result);
        }
	}


    //Allows a user to classify a claim.
    private void classifyClaim(ArrayList<Claim> result) {
        String category;
        int n;

        System.out.print("Would you like to classify a claim? (Y/N) ");
        if(getYesNo()) {
            System.out.print("Enter the number of the claim you want to classify (0 abort): ");
            n = Integer.parseInt(sc.nextLine());
            while(0 > n || n > result.size()) {
                System.out.println("Enter a valid number!");
                n = Integer.parseInt(sc.nextLine());
            }
            if(n != 0) {
                System.out.println("How do you want to classify the claim (simple/complex, 0 abort)");
                category = sc.nextLine();
                while(!category.equals("simple") && !category.equals("complex") && !category.equals("0")) {
                    System.out.println("Enter a valid category! (simple/complex, 0 abort)");
                    category = sc.nextLine();
                }
                if(!category.equals("0")) {
                    if(category.equals("simple")) {
                        cm.setClaimCategory(result.get(n - 1), Category.simple);
                    }
                    else {
                        cm.setClaimCategory(result.get(n - 1), Category.complex);
                    }
                }
            }
        }
    }

	private void printList() {
		int index = 1;
		for (Claim cm : searchList) {
			System.out.println(index + ". " + cm.getDate() + " "
					+ cm.getClaimant());
		}
	}

	private ArrayList<Claim> claimantSearch() {
        String[] names;

        clear();
        names = askClientNames();
		searchList = cm.checkClaimByClient(names[0], names[1]);
		printList();
		printDetails();
        return searchList;
	}

	private ArrayList<Claim> dateSearch() {
		clear();
		searchList = cm.checkClaimByDate(askDate());
		printList();
		printDetails();
        return searchList;
	}

	private ArrayList<Claim> claimantAndDateSearch() {
        String[] names;

        clear();
        names = askClientNames();
		searchList = cm.checkClaimByClientAndDate(names[0], names[1], askDate());
		printList();
		printDetails();
        return searchList;
	}

	private void printDetails() {
		System.out
				.print("\nEnter the index of the claim you want to see detailed: ");
		String choice = sc.nextLine();

		while (!inputCheck(choice, searchList.size())) {
			System.out.print("\nPlease choose a VALID index: ");
			choice = sc.nextLine();
		}
		int choiceInt;
		if (Integer.parseInt(choice) <= 0) {
			choiceInt = 1;
		} else {
			choiceInt = Integer.parseInt(choice);
		}

		System.out.println(searchList.get(choiceInt - 1));
	}

    // asks the user to enter a date
    private String askDate() {
        System.out.print("Enter a date dd/mm/yyyy format: ");
        return sc.nextLine();
    }
}
