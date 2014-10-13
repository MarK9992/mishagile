package claim;

import java.util.ArrayList;

import client.Client;

public class ClaimManager {

	// Attributes

	private ArrayList<Claim> claimList;

	// Constructors

	public ClaimManager() {
		this(new ArrayList<Claim>());
	}

	public ClaimManager(ArrayList<Claim> claimList) {
		this.claimList = claimList;
	}

	// Accessors

	public ArrayList<Claim> getClaimList() {
		return claimList;
	}

	// Methods

	public ArrayList<Claim> checkClaimByClient(String firstName, String name) {
		ArrayList<Claim> matches = new ArrayList<Claim>();
		for (Claim cl : claimList) {
			if (cl.getClaimant().match(firstName, name)) {
				matches.add(cl);
			}
		}
		if (matches.size() == 0) {
			matches = null;
		}
		return matches;
	}

	public ArrayList<Claim> checkClaimByDate(String date) {
		ArrayList<Claim> matches = new ArrayList<Claim>();
		for (Claim cl : claimList) {
			if (cl.matchDate(date)) {
				matches.add(cl);
			}
		}
		if (matches.size() == 0) {
			matches = null;
		}
		return matches;
	}

	public ArrayList<Claim> checkClaimByClientAndDate(String firstName,
			String name, String date) {
		ArrayList<Claim> matches = new ArrayList<Claim>();
		for (Claim cl : claimList) {
			if (cl.getClaimant().match(firstName, name) && cl.matchDate(date)) {
				matches.add(cl);
			}
		}
		if (matches.size() == 0) {
			matches = null;
		}
		return matches;
	}

	public void addClaim(Claim claim) {
		claimList.add(claim);
	}

    /**
     * Sets the category of a given claim.
     *
     * @param claim the claim to set its category
     * @param category the category to apply to the claim
     */
    public void setClaimCategory(Claim claim, Category category) {
           claim.setCategory(category);
    }

    /**
     * Sets the decision about a claim.
     *
     * @param claim the claim to set its decision
     * @param decision the decision to apply to the claim
     */
    public void setClaimDecision(Claim claim, Decision decision) {
        claim.setDecision(decision);
    }
}
