package client;

/**
 * Created by root on 11/10/14.
 */
public class Claim {

    // Attributes

    private int carPrice, damageCost;
    // TODO data structure?
    private String carHistory;

    // Constructors

    public Claim() {
        this(0, 0, "previous history of accident");
    }

    public Claim(int carPrice, int damageCost, String carHistory) {
        this.carPrice = carPrice;
        this.damageCost = damageCost;
        this.carHistory = carHistory;
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

    // Override

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Claim)) return false;

        Claim claim = (Claim) o;

        if (carPrice != claim.carPrice) return false;
        if (damageCost != claim.damageCost) return false;
        if (!carHistory.equals(claim.carHistory)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = carPrice;
        result = 31 * result + damageCost;
        result = 31 * result + carHistory.hashCode();
        return result;
    }

}
