package payment;

import claim.Claim;

public class Payment {

	// Attributes

	private Claim claimHandled;
	private String paymentDate;
	private PaymentMode payment;

	// Constructors

	public Payment(Claim claim, String date, PaymentMode payment) {
		this.claimHandled = claim;
		this.paymentDate = date;
		this.payment = payment;
	}

	// Methods

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((claimHandled == null) ? 0 : claimHandled.hashCode());
		result = prime * result + ((payment == null) ? 0 : payment.hashCode());
		result = prime * result
				+ ((paymentDate == null) ? 0 : paymentDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Payment)) {
			return false;
		}
		Payment other = (Payment) obj;
		if (claimHandled == null) {
			if (other.claimHandled != null) {
				return false;
			}
		} else if (!claimHandled.equals(other.claimHandled)) {
			return false;
		}
		if (payment != other.payment) {
			return false;
		}
		if (paymentDate == null) {
			if (other.paymentDate != null) {
				return false;
			}
		} else if (!paymentDate.equals(other.paymentDate)) {
			return false;
		}
		return true;
	}

	// Accessors

	public Claim getClaimHandled() {
		return claimHandled;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public PaymentMode getPayment() {
		return payment;
	}
}
