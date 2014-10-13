package claim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.Client;
import client.Insurance;

/**
 * Created by root on 11/10/14.
 */
public class ClaimManagerTest {

	private ClaimManager cm, cm1;
	private ArrayList<Claim> claimList;
    private Claim claim1, claim2;

	@Before
	public void setUp() {
		cm = new ClaimManager();
		claimList = new ArrayList<Claim>();
		claimList.add(new Claim());
		cm1 = new ClaimManager(claimList);
        claim1 = new Claim();
        claim2 = new Claim();
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
    public void setClaimCategoryTest() {
        cm.setClaimCategory(claim1, Category.complex);
        cm.setClaimCategory(claim2, Category.simple);
        assertEquals(Category.complex, claim1.getCategory());
        assertEquals(Category.simple, claim2.getCategory());
        assertEquals(Category.undefined, cm1.getClaimList().get(0).getCategory());
    }

    @Test
    public void setClaimDecisionTest() {
        cm.setClaimDecision(claim1, Decision.NOK);
        cm.setClaimDecision(claim2, Decision.OK);
        assertEquals(Decision.NOK, claim1.getDecision());
        assertEquals(Decision.OK, claim2.getDecision());
        assertEquals(Decision.undefined, cm1.getClaimList().get(0).getDecision());
    }

	@After
	public void tearDown() {
		cm = null;
		cm1 = null;
		claimList = null;
        claim1 = null;
        claim2 = null;
	}
}
