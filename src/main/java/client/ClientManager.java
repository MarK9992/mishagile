package client;

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
        for(Client cl: clientList) {
            if(cl.match(firstname, name)) {
                return cl;
            }
        }
        return null;
    }

    /**
     * Send a form to a client.
     * @param firstname the firstname of the client
     * @param name the surname of the client
     * @param formType the form type to send
     * @return true if succesfull, false otherwise
     */
    public boolean sendForm(String firstname, String name, FormType formType) {
        Client cl = checkClient(firstname, name);

        if(cl != null) {
            formList.add(new Form(formType, cl));
            return true;
        }
        return false;
    }

    /**
     * Retrieves a form send to the client.
     * @param firstname the firstname of the client
     * @param name the surname of the client
     * @param formType the form type to send
     * @return the form if exists, null otherwise
     */
    public Form checkForm(String firstname, String name, FormType formType) {
        for(Form f: formList) {
            if(f.getType() == formType && f.getClient().match(firstname, name)) {
                return f;
            }
        }
        return null;
    }

    // Accessors

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    public ArrayList<Form> getFormList() {
        return formList;
    }
}
