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

	public ArrayList<Claim> checkClaimByClient(Client claimant) {
		ArrayList<Claim> matches = new ArrayList<Claim>();
		for (Claim cl : claimList) {
			if (cl.matchClaimant(claimant)) {
				matches.add(cl);
			}
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
		return matches;
	}

	public ArrayList<Claim> checkClaimByClientAndDate(Client claimant,
			String date) {
		ArrayList<Claim> matches = new ArrayList<Claim>();
		for (Claim cl : claimList) {
			if (cl.matchClaimant(claimant) && cl.matchDate(date)) {
				matches.add(cl);
			}
		}
		return matches;
	}
}
