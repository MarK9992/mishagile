package claim;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import claim.Claim;
import client.Client;
import client.Insurance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by root on 11/10/14.
 */
public class ClaimTest {

	private Claim c, c1;

	@Before
	public void setUp() {
		c = new Claim();
		c1 = new Claim(
				10,
				5,
				"09/09/1992 accident",
				ClaimStatus.CHECKED,
				new Client("Bob", "Agile", Insurance.D, new ArrayList<Claim>()),
				"09/09/1992", Category.complex, Decision.NOK);
	}

	@Test
	public void testClaim() {
		assertEquals(0, c.getCarPrice());
		assertEquals(0, c.getDamageCost());
		assertEquals("previous history of accident", c.getCarHistory());
        assertEquals(Category.undefined, c.getCategory());
        assertEquals(Decision.undefined, c.getDecision());
		assertEquals(10, c1.getCarPrice());
		assertEquals(5, c1.getDamageCost());
		assertEquals("09/09/1992 accident", c1.getCarHistory());
		assertEquals(ClaimStatus.CHECKED, c1.getStatus());
        assertEquals(Category.complex, c1.getCategory());
        assertEquals(Decision.NOK, c1.getDecision());
	}

	@Test
	public void testMatchClaimant() {
		assertTrue(c.matchClaimant(new Client()));
		assertTrue(!c.matchClaimant(new Client("Bob", "Agile", Insurance.D,
				new ArrayList<Claim>())));
		assertTrue(c1.matchClaimant(new Client("Bob", "Agile", Insurance.D,
				new ArrayList<Claim>())));
		assertTrue(!c1.matchClaimant(new Client()));
	}

	@Test
	public void testMatchDate() {
		assertTrue(c.matchDate("01/01/2014"));
		assertTrue(!c.matchDate("01/02/2014"));
		assertTrue(c1.matchDate("09/09/1992"));
		assertTrue(!c1.matchDate("01/01/2014"));
	}

	@After
	public void tearDown() {
		c = null;
		c1 = null;
	}
}
