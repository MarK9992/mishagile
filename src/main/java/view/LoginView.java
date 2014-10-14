package view;

import user.UserAccount;
import user.UserManager;
import claim.ClaimManager;
import client.ClientManager;

/**
 * Created by root on 11/10/14.
 */
public class LoginView extends View {

	private UserManager um;

	// Constructor
	public LoginView(UserManager um) {
		super();
		this.um = um;
		display();
	}

	public LoginView() {
		this(new UserManager());
	}

	// Methods
	public String getLogin() {
		System.out.print("LOGIN: ");
		return sc.nextLine();
	}

	public String getPassword() {
		System.out.print("PASSWORD: ");
		return sc.nextLine();
	}

    @Override
	public void display() {
		System.out
				.println("Welcome to INSERT METAPHOR\n\nPlease enter your login and password.\n");

		UserAccount ua;

		while ((ua = um.login(getLogin(), getPassword())) == null) {
			System.out
					.println("LOGIN and/or PASSWORD incorrect. Please try again.\n");
		}

		new MainMenuView(ua, new ClientManager(), new ClaimManager());
	}
}
