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
	claim3.setStatus(ClaimStatus.CLASSIFIED);
	claim4 = new Claim();
	claim4.setStatus(ClaimStatus.OK);
	claimList1 = new ArrayList<Claim>();
	claimList1.add(claim1);
	claimList1.add(claim2);
	claimList1.add(claim3);
	claimList1.add(claim4);
    }

    @Test
    public void testClaimManager() {
	assertEquals(new ArrayList<Claim>(), cm.getList());
	assertEquals(claimList, cm1.getList());
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
	assertEquals(0, cm.getList().size());
	cm.addClaim(claimAdd);
	assertEquals(1, cm.getList().size());
	assertTrue(cm.getList().contains(claimAdd));
    }

    @Test
    public void testSetClaimCategory() {
	cm.setClaimCategory(claim1, Category.complex);
	cm.setClaimCategory(claim2, Category.simple);
	assertEquals(Category.complex, claim1.getCategory());
	assertEquals(ClaimStatus.CLASSIFIED, claim1.getStatus());
	assertEquals(Category.simple, claim2.getCategory());
	assertEquals(ClaimStatus.CLASSIFIED, claim2.getStatus());
	;
	assertEquals(Category.undefined, cm1.getList().get(0)
		.getCategory());
    }

    @Test
    public void testSetClaimStatus() {
	cm.setClaimStatus(claim1, ClaimStatus.NOK);
	cm.setClaimStatus(claim2, ClaimStatus.PAYED);
	assertEquals(ClaimStatus.NOK, claim1.getStatus());
	assertEquals(ClaimStatus.PAYED, claim2.getStatus());
	assertEquals(ClaimStatus.REGISTERED, cm1.getList().get(0)
		.getStatus());
    }

    @Test
    public void lookForSpecificClaimsTest() {
	ArrayList<Claim> classClaims = cm.lookForSpecificClaims(claimList1,
		ClaimStatus.REGISTERED);
	assertTrue(classClaims.size() == 2);
	assertTrue(!classClaims.contains(claim3));
	assertTrue(!classClaims.contains(claim4));
	assertTrue(classClaims.contains(claim1));
	assertTrue(classClaims.contains(claim2));

	classClaims = cm.lookForSpecificClaims(claimList1,
		ClaimStatus.CLASSIFIED);
	assertTrue(classClaims.size() == 1);
	assertTrue(classClaims.contains(claim3));
	assertTrue(!classClaims.contains(claim4));
	assertTrue(!classClaims.contains(claim1));
	assertTrue(!classClaims.contains(claim2));

	classClaims = cm.lookForSpecificClaims(claimList1, ClaimStatus.OK);
	assertTrue(classClaims.size() == 1);
	assertTrue(!classClaims.contains(claim3));
	assertTrue(classClaims.contains(claim4));
	assertTrue(!classClaims.contains(claim1));
	assertTrue(!classClaims.contains(claim2));
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
