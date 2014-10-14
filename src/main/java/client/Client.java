package client;

import java.util.ArrayList;

import claim.Claim;

/**
 * Created by root on 11/10/14.
 */
public class Client {

	// Attributes

	String firstName;
	String name;
	Insurance insurance;
	ArrayList<Claim> claimList;

	// Constructors

	public Client() {
		this("firstname", "name", Insurance.D, new ArrayList<Claim>());
	}

	public Client(String firstName, String name, Insurance insurance,
			ArrayList<Claim> claimList) {
		this.firstName = firstName;
		this.name = name;
		this.insurance = insurance;
		this.claimList = claimList;
	}

	// Methods

	public boolean match(String firstName, String name) {
		if (this.firstName.equals(firstName) && this.name.equals(name)) {
			return true;
		}
		return false;
	}

	public Client addNewClaim(Claim newClaim) {
		this.claimList.add(newClaim);
		return this;
	}

    /**
     * Returns a string representation of the client's names.
     *
     * @return the names in a string
     */
    public String NamesToString() {
        return firstName + " " + name;
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
		if (this == o)
			return true;
		if (!(o instanceof Client))
			return false;

		Client client = (Client) o;

		if (!claimList.equals(client.claimList))
			return false;
		if (!firstName.equals(client.firstName))
			return false;
		if (insurance != client.insurance)
			return false;
		if (!name.equals(client.name))
			return false;

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

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", name='" + name + '\'' +
                ", insurance=" + insurance +
                ", claimList=" + claimList +
                '}';
    }
}
