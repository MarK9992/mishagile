package payment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import claim.Claim;
import claim.ClaimManager;
import claim.ClaimStatus;

public class PaymentManagerTest {

	private PaymentManager pm;
	private ClaimManager cm;

	@Before
	public void setUp() throws Exception {
		cm = new ClaimManager();
		pm = new PaymentManager(cm);
	}

	@After
	public void tearDown() throws Exception {
		pm = null;
		cm = null;
	}

	@Test
	public void testPaymentManager() {
		assertNotNull(pm);
		assertNotNull(pm.getPaymentsList());
		assertNotNull(pm.getCm());
	}

	@Test
	public void testAddPayment() {
		pm.addPayment(new Claim(), "12/12/12", PaymentMode.BANKTRANSFER);
		pm.addPayment(new Claim(), "11/11/11", PaymentMode.CHECK);
		assertEquals(2, pm.getPaymentsList().size());
		assertEquals(new Payment(new Claim(), "12/12/12",
				PaymentMode.BANKTRANSFER), pm.getPaymentsList().get(0));
		assertEquals(new Payment(new Claim(), "11/11/11", PaymentMode.CHECK),
				pm.getPaymentsList().get(1));
	}

	@Test
	public void testSetClaimStatusPayed() {
		pm.addPayment(new Claim(), "12/12/12", PaymentMode.BANKTRANSFER);
		pm.setClaimStatusPayed(pm.getPaymentsList().get(0).getClaimHandled());
		assertEquals(ClaimStatus.PAYED, pm.getPaymentsList().get(0)
				.getClaimHandled().getStatus());
	}

}
