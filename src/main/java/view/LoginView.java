package view;

import payment.PaymentManager;
import user.UserAccount;
import user.UserManager;
import claim.ClaimManager;
import client.ClientManager;

/**
 * Created by root on 11/10/14.
 */
public class LoginView extends View {

	private UserManager um;
	private ClientManager cm;
	private ClaimManager claimManager;
	private PaymentManager paymentManager;

	// Constructors

	public LoginView(UserManager um, ClientManager cm,
			ClaimManager claimManager, PaymentManager pm) {
		super();
		this.um = um;
		this.cm = cm;
		this.claimManager = claimManager;
		this.paymentManager = pm;
		display();
	}

	public LoginView(UserManager um, ClientManager cm, ClaimManager claimManager) {
		super();
		this.um = um;
		this.cm = cm;
		this.claimManager = claimManager;
		this.paymentManager = new PaymentManager(this.claimManager);
		display();
	}

	public LoginView() {
		this(new UserManager(), new ClientManager(), new ClaimManager());
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
		new MainMenuView(ua, cm, claimManager, paymentManager);
	}
}
