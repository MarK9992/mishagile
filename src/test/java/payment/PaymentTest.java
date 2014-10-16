package payment;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import claim.Claim;

public class PaymentTest {

    Payment p1, p2;

    @Before
    public void setUp() throws Exception {
	p1 = new Payment(new Claim(), "12/12/12", PaymentMode.BANKTRANSFER);
	p2 = new Payment(new Claim(), "11/11/11", PaymentMode.CHECK);
    }

    @After
    public void tearDown() throws Exception {
	p1 = null;
	p2 = null;
    }

    @Test
    public void testPayment() {
	assertEquals(new Claim(), p1.getClaimHandled());
	assertEquals("12/12/12", p1.getPaymentDate());
	assertEquals(PaymentMode.BANKTRANSFER, p1.getPayment());
	assertFalse(p1.equals(p2));
    }

}
