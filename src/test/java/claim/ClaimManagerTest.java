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
	private ArrayList<Claim> claimList, claimList1;
	private Claim claim1, claim2, claim3, claim4;

	@Before
	public void setUp() {
		cm = new ClaimManager();
		claimList = new ArrayList<Claim>();
		claimList.add(new Claim());
		cm1 = new ClaimManager(claimList);
		claim1 = new Claim();
		claim2 = new Claim();
		claim3 = new Claim();
		claim3.setDecision(Decision.OK);
		claim4 = new Claim();
		claim4.setDecision(Decision.NOK);
		claimList1 = new ArrayList<Claim>();
		claimList1.add(claim1);
		claimList1.add(claim2);
		claimList1.add(claim3);
		claimList1.add(claim4);
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

	@Test
	public void setClaimCategoryTest() {
		cm.setClaimCategory(claim1, Category.complex);
		cm.setClaimCategory(claim2, Category.simple);
		assertEquals(Category.complex, claim1.getCategory());
		assertEquals(Category.simple, claim2.getCategory());
		assertEquals(Category.undefined, cm1.getClaimList().get(0)
				.getCategory());
	}

	@Test
	public void setClaimDecisionTest() {
		cm.setClaimDecision(claim1, Decision.NOK);
		cm.setClaimDecision(claim2, Decision.OK);
		assertEquals(Decision.NOK, claim1.getDecision());
		assertEquals(Decision.OK, claim2.getDecision());
		assertEquals(Decision.undefined, cm1.getClaimList().get(0)
				.getDecision());
	}

	@Test
	public void lookForClassifiedClaimsTest() {
		ArrayList<Claim> classClaims = cm.lookForClassifiedClaims(claimList1);

		assertTrue(classClaims.size() == 2);
		assertTrue(classClaims.contains(claim3));
		assertTrue(classClaims.contains(claim4));
		assertTrue(!classClaims.contains(claim1));
		assertTrue(!classClaims.contains(claim2));
		assertNull(cm.lookForClassifiedClaims(claimList));
	}

	@After
	public void tearDown() {
		cm = null;
		cm1 = null;
		claimList = null;
		claim1 = null;
		claim2 = null;
		claim3 = null;
		claim4 = null;
		claimList1 = null;
	}
}
