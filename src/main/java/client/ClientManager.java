package client;

import claim.Claim;
import communication.Form;
import communication.FormType;

import java.util.ArrayList;

/**
 * Created by marc on 12/10/14.
 */
public class ClientManager {

	// Attributes

	private ArrayList<Client> clientList;
	private ArrayList<Form> formList;

	// Constructors

	public ClientManager() {
		this(new ArrayList<Client>(), new ArrayList<Form>());
	}

	public ClientManager(ArrayList<Client> clientList, ArrayList<Form> formList) {
		this.clientList = clientList;
		this.formList = formList;
	}

	// Methods

	public Client checkClient(String firstname, String name) {
		for (Client cl : clientList) {
			if (cl.match(firstname, name)) {
				return cl;
			}
		}
		return null;
	}

	public boolean sendForm(String firstname, String name, FormType formType) {
		Client cl = checkClient(firstname, name);

		if (cl != null) {
			formList.add(new Form(formType, cl));
			return true;
		}
		return false;
	}

	public boolean addClient(String firstname, String name, char insurance) {
		Insurance finalInsurance;
		switch (insurance) {
		case 'A':
			finalInsurance = Insurance.A;
			break;
		case 'B':
			finalInsurance = Insurance.B;
			break;
		case 'C':
			finalInsurance = Insurance.C;
			break;
		case 'D':
			finalInsurance = Insurance.D;
			break;
		default:
			return false;
		}
		clientList.add(new Client(firstname, name, finalInsurance,
				new ArrayList<Claim>()));
		return true;
	}

	// Accessors

	public ArrayList<Client> getClientList() {
		return clientList;
	}

	public ArrayList<Form> getFormList() {
		return formList;
	}
}
