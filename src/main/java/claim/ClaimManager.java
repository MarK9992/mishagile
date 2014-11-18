package claim;

import model.Manager;

import java.util.ArrayList;

public class ClaimManager extends Manager<Claim> {

    // Constructors

    public ClaimManager() {
        this(new ArrayList<Claim>());
    }

    public ClaimManager(ArrayList<Claim> list) {
        super();
        this.list = list;
    }

    // Accessors

    public ArrayList<Claim> getList() {
        return list;
    }

    // Methods

    public ArrayList<Claim> checkClaimByClient(String firstName, String name) {
        ArrayList<Claim> matches = new ArrayList<Claim>();
        for (Claim cl : list) {
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
        for (Claim cl : list) {
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
        for (Claim cl : list) {
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
        list.add(claim);
    }

    /**
     * Sets the category of a given claim.
     *
     * @param claim    the claim to set its category
     * @param category the category to apply to the claim
     */
    public void setClaimCategory(Claim claim, Category category) {
        claim.setCategory(category);
        if (category != Category.undefined) {
            claim.setStatus(ClaimStatus.CLASSIFIED);
        }
    }

    /**
     * Sets the status of a claim.
     *
     * @param claim  the claim to set its decision
     * @param status the status to apply to the claim
     */
    public void setClaimStatus(Claim claim, ClaimStatus status) {
        claim.setStatus(status);
    }

    /**
     * Given a list of claims, filters the claims according to the parameter.
     *
     * @param claims the claim list
     * @param status the claim status
     * @return the classified claims, null if none
     */
    public ArrayList<Claim> lookForSpecificClaims(ArrayList<Claim> claims,
                                                  ClaimStatus status) {
        ArrayList<Claim> classified = new ArrayList<Claim>();

        for (Claim claim : claims) {
            if (claim.getStatus() == status) {
                classified.add(claim);
            }
        }
        if (classified.size() == 0) {
            return null;
        }
        return classified;
    }
}
