package client;

import java.util.ArrayList;

/**
 * Created by marc on 12/10/14.
 */
public class ClientManager {

    // Attributes

    private ArrayList<Client> clientList;

    // Constructors

    public ClientManager() {
        this(new ArrayList<Client>());
    }

    public ClientManager(ArrayList<Client> clientList) {
        this.clientList = clientList;
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

    // Accessors

    public ArrayList<Client> getClientList() {
        return clientList;
    }
}
