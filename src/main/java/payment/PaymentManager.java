package payment;

import java.util.ArrayList;

import claim.Claim;
import claim.ClaimManager;
import claim.ClaimStatus;
import model.Manager;

public class PaymentManager extends Manager<Payment> {

    // Attributes

    private ClaimManager cm;

    // Constructors

    public PaymentManager(ClaimManager cm) {
        list = new ArrayList<Payment>();
        this.cm = cm;
    }

    // Methods

    public void addPayment(Claim cl, String date, PaymentMode mode) {
        list.add(new Payment(cl, date, mode));
    }

    public void setClaimStatusPayed(Claim cl) {
        cm.setClaimStatus(cl, ClaimStatus.PAYED);
    }

    public ArrayList<Payment> checkPaymentByClient(String firstName, String name) {
        ArrayList<Payment> matches = new ArrayList<Payment>();
        for (Payment cl : list) {
            if (cl.getClaimHandled().getClaimant().match(firstName, name)) {
                matches.add(cl);
            }
        }
        if (matches.size() == 0) {
            matches = null;
        }
        return matches;
    }

    public ArrayList<Payment> checkPaymentByDate(String date) {
        ArrayList<Payment> matches = new ArrayList<Payment>();
        for (Payment cl : list) {
            if (cl.matchDate(date)) {
                matches.add(cl);
            }
        }
        if (matches.size() == 0) {
            matches = null;
        }
        return matches;
    }

    public ArrayList<Payment> checkPaymentByMode(PaymentMode mode) {
        ArrayList<Payment> matches = new ArrayList<Payment>();
        for (Payment cl : list) {
            if (cl.getPayment().equals(mode)) {
                matches.add(cl);
            }
        }
        if (matches.size() == 0) {
            matches = null;
        }
        return matches;
    }

    // Accessors

    public ArrayList<Payment> getList() {
        return list;
    }

    public ClaimManager getCm() {
        return cm;
    }

}
