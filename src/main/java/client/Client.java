package client;

import java.util.ArrayList;

/**
 * Created by root on 11/10/14.
 */
public class Client {

    // Atributes

    String firstName;
    String name;
    Insurance insurance;
    ArrayList<Claim> claimList;

    // Constructors

    public Client() {
        this("firstname", "name", Insurance.D, new ArrayList<Claim>());
    }

    public Client(String firstName, String name, Insurance insurance, ArrayList<Claim> claimList) {
        this.firstName = firstName;
        this.name = name;
        this.insurance = insurance;
        this.claimList = claimList;
    }

    // Accessors

    public String getFirstName() {
        return firstName;
    }

    public String getName() {
        return name;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public ArrayList<Claim> getClaimList() {
        return claimList;
    }

    // Override

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (!claimList.equals(client.claimList)) return false;
        if (!firstName.equals(client.firstName)) return false;
        if (insurance != client.insurance) return false;
        if (!name.equals(client.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (insurance != null ? insurance.hashCode() : 0);
        result = 31 * result + claimList.hashCode();
        return result;
    }
}
