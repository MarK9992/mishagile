package claim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by root on 11/10/14.
 */
public class ClaimManagerTest {

	private ClaimManager cm, cm1;
	private ArrayList<Claim> claimList;

	@Before
	public void setUp() {
		cm = new ClaimManager();
		claimList = new ArrayList<Claim>();
		claimList.add(new Claim());
		cm1 = new ClaimManager(claimList);
	}

	@Test
	public void testClaimManager() {
		assertEquals(new ArrayList<Claim>(), cm.getClaimList());
		assertEquals(claimList, cm1.getClaimList());
	}

	@Test
	public void testCheckClaimByClient() {
		assertNull(cm.checkClaimByClient("firstname", "name"));
		assertEquals(claimList, cm1.checkClaimByClient("firstname", "name"));
	}

	@Test
	public void testCheckClaimByDate() {
		assertNull(cm.checkClaimByDate("01/01/2014"));
		assertEquals(claimList, cm1.checkClaimByDate("01/01/2014"));
	}

	@Test
	public void testCheckClaimByClientAndDate() {
		assertNull(cm.checkClaimByClientAndDate("firstname", "name",
				"01/01/2014"));
		assertEquals(claimList, cm1.checkClaimByClientAndDate("firstname",
				"name", "01/01/2014"));
	}

	@Test
	public void testAddClaim() {
		Claim claimAdd = new Claim();
		assertEquals(0, cm.getClaimList().size());
		cm.addClaim(claimAdd);
		assertEquals(1, cm.getClaimList().size());
		assertTrue(cm.getClaimList().contains(claimAdd));
	}

	@After
	public void tearDown() {
		cm = null;
		cm1 = null;
		claimList = null;
	}
}
