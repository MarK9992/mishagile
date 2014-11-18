package client;

import communication.ClientSimulator;
import claim.Claim;
import communication.Form;
import communication.FormType;
import model.Manager;

import java.util.ArrayList;

/**
 * Created by marc on 12/10/14.
 */
public class ClientManager extends Manager<Client> {

    // Attributes

    private ArrayList<Form> formList;

    // Constructors

    public ClientManager() {
        this(new ArrayList<Client>(), new ArrayList<Form>());
    }

    public ClientManager(ArrayList<Client> list, ArrayList<Form> formList) {
        this.list = list;
        this.formList = formList;
    }

    // Methods

    public void addClaimToClient(Client client, Claim newClaim) {
        int index = indexClient(client.getFirstName(), client.getName());
        if (index >= 0) {
            list.set(index, client.addNewClaim(newClaim));
        }
    }

    public int indexClient(String firstname, String name) {
        for (Client cl : list) {
            if (cl.match(firstname, name)) {
                return list.indexOf(cl);
            }
        }
        return -1;
    }

    public Client checkClient(String firstname, String name) {
        for (Client cl : list) {
            if (cl.match(firstname, name)) {
                return cl;
            }
        }
        return null;
    }

    /**
     * Send a form to a client.
     *
     * @param firstname the firstname of the client
     * @param name      the surname of the client
     * @param formType  the form type to send
     * @return true if succesfull, false otherwise
     */
    public boolean sendForm(String firstname, String name, FormType formType) {
        Client cl = checkClient(firstname, name);

        if (cl != null) {
            formList.add(new Form(formType, cl));
            return true;
        }
        return false;
    }

    /**
     * Retrieves a form send to the client.
     *
     * @param firstname the firstname of the client
     * @param name      the surname of the client
     * @param formType  the form type to send
     * @return the form if exists, null otherwise
     */
    public Form checkForm(String firstname, String name, FormType formType) {
        for (Form f : formList) {
            if (f.getType() == formType && f.getClient().match(firstname, name)) {
                ClientSimulator.getInstance().fillForm(f,
                        Math.random() * ClientSimulator.RANGE);
                formList.remove(f);
                return f;
            }
        }
        return null;
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
        list.add(new Client(firstname, name, finalInsurance,
                new ArrayList<Claim>()));
        return true;
    }

    /**
     * Search clients by their names.
     *
     * @param firstname client's firstname
     * @param name      client's name
     * @return a list of matching clients
     */
    public ArrayList<Client> searchClient(String firstname, String name) {
        ArrayList<Client> results = new ArrayList<Client>();

        for (Client client : list) {
            if (client.match(firstname, name)) {
                results.add(client);
            }
        }
        return results;
    }

    /**
     * Search clients by their insurance.
     *
     * @param insurance client's insurance
     * @return a list of matching clients
     */
    public ArrayList<Client> searchClient(Insurance insurance) {
        ArrayList<Client> results = new ArrayList<Client>();

        for (Client client : list) {
            if (client.getInsurance() == insurance) {
                results.add(client);
            }
        }
        return results;
    }

    /**
     * Search a client by a claim.
     *
     * @param claim the client's claim
     * @return the matching client
     */
    public Client searchClient(Claim claim) {
        for (Client client : list) {
            for (Claim currentClaim : client.getClaimList()) {
                if (currentClaim.equals(claim)) {
                    return client;
                }
            }
        }
        return null;
    }

    // Accessors

    public ArrayList<Client> getList() {
        return list;
    }

    public ArrayList<Form> getFormList() {
        return formList;
    }
}
