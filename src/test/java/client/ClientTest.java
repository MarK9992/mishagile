package client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import claim.Claim;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by root on 11/10/14.
 */
public class ClientTest {

    private Client c, c1;
    private ArrayList<Claim> clist;

    @Before
    public void setUp() {
        c = new Client();
        clist = new ArrayList<Claim>();
        clist.add(new Claim());
        c1 = new Client("Bob", "Sylvestre", Insurance.B, clist);
    }

    @Test
    public void testClient() {
        assertEquals("firstname", c.getFirstName());
        assertEquals("name", c.getName());
        assertEquals(Insurance.D, c.getInsurance());
        assertEquals(new ArrayList<Claim>(), c.getClaimList());
        assertEquals("Bob", c1.getFirstName());
        assertEquals("Sylvestre", c1.getName());
        assertEquals(Insurance.B, c1.getInsurance());
        assertEquals(clist, c1.getClaimList());
    }

    @Test
    public void testMatch() {
        assertTrue(c.match("firstname", "name"));
        assertTrue(!c.match("false", "name"));
        assertTrue(c1.match("Bob", "Sylvestre"));
        assertTrue(!c1.match("Bob", "false"));
    }

    @After
    public void tearDown() {
        c = null;
        c1 = null;
        clist = null;
    }
}
