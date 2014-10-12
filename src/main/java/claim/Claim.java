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

	// Constructors

	public Claim() {
		this(0, 0, "previous history of accident", ClaimStatus.UNCHECKED,
				new Client(), "01/01/2014");
	}

	public Claim(int carPrice, int damageCost, String carHistory,
			ClaimStatus status, Client claimant, String date) {
		this.carPrice = carPrice;
		this.damageCost = damageCost;
		this.carHistory = carHistory;
		this.status = status;
		this.claimant = claimant;
		this.date = date;
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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Claim)) {
			return false;
		}
		Claim other = (Claim) obj;
		if (carHistory == null) {
			if (other.carHistory != null) {
				return false;
			}
		} else if (!carHistory.equals(other.carHistory)) {
			return false;
		}
		if (carPrice != other.carPrice) {
			return false;
		}
		if (claimant == null) {
			if (other.claimant != null) {
				return false;
			}
		} else if (!claimant.equals(other.claimant)) {
			return false;
		}
		if (damageCost != other.damageCost) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (status != other.status) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((carHistory == null) ? 0 : carHistory.hashCode());
		result = prime * result + carPrice;
		result = prime * result
				+ ((claimant == null) ? 0 : claimant.hashCode());
		result = prime * result + damageCost;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Car price: " + carPrice + "\nDamage cost:" + damageCost
				+ "\nCar history: " + carHistory + "\nClaimant: " + claimant
				+ "\nDate: " + date;
	}
}
