package claim;

import client.Client;

/**
 * Created by root on 11/10/14.
 */
public class Claim {

	// Attributes

	private int carPrice, damageCost;
	// TODO data structure?
	private String carHistory;
	private ClaimStatus status;
	private Client claimant;
	private String date;
    private Category category;
    private Decision decision;

	// Constructors

	public Claim() {
		this(0, 0, "previous history of accident", ClaimStatus.REGISTERED,
				new Client(), "01/01/2014", Category.undefined, Decision.undefined);
	}

    public Claim(int carPrice, int damageCost, String carHistory,
			ClaimStatus status, Client claimant, String date, Category category, Decision decision) {
		this.carPrice = carPrice;
		this.damageCost = damageCost;
		this.carHistory = carHistory;
		this.status = status;
		this.claimant = claimant;
		this.date = date;
        this.category = category;
        this.decision = decision;
	}

	// Accessors

	public int getCarPrice() {
		return carPrice;
	}

	public int getDamageCost() {
		return damageCost;
	}

	public String getCarHistory() {
		return carHistory;
	}

	public ClaimStatus getStatus() {
		return status;
	}

	public Client getClaimant() {
		return claimant;
	}

    /**
     * Returns the category field.
     *
     * @return the category field
     */
    public Category getCategory() { return category; }

    /**
     * Returns the decision field.
     *
     * @return the decision field
     */
    public Decision getDecision() {
        return decision;
    }

    // Mutators

    /**
     * Sets the category.
     *
     * @param category the category to apply
     */
    void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Sets the decision.
     *
     * @param decision the decision to apply
     */
    void setDecision(Decision decision) {
        this.decision = decision;
    }

	// Methods

	public String getDate() {
		return date;
	}

	public boolean matchClaimant(Client claimant) {
		if (this.claimant.match(claimant.getFirstName(), claimant.getName())) {
			return true;
		}
		return false;
	}

	public boolean matchDate(String date) {
		if (this.date.equals(date)) {
			return true;
		}
		return false;
	}

	// Override

    @Override
	public String toString() {
		return "Car price: " + carPrice + "\nDamage cost: " + damageCost
				+ "\nCar history: " + carHistory + "\nClaimant: " + claimant.namesToString()
				+ "\nDate: " + date;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Claim)) return false;

        Claim claim = (Claim) o;

        if (carPrice != claim.carPrice) return false;
        if (damageCost != claim.damageCost) return false;
        if (!carHistory.equals(claim.carHistory)) return false;
        if (category != claim.category) return false;
        if (!claimant.equals(claim.claimant)) return false;
        if (!date.equals(claim.date)) return false;
        if (decision != claim.decision) return false;
        if (status != claim.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = carPrice;
        result = 31 * result + damageCost;
        result = 31 * result + carHistory.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + claimant.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + decision.hashCode();
        return result;
    }
}
