package client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void equalsTest() {
        assertTrue(c1.equals(c1));
        assertTrue(!c1.equals(new Integer(1)));
        assertTrue(!c1.equals(new Client("false", "Sylvestre", Insurance.B, clist)));
        assertTrue(!c1.equals(new Client("Bob", "false", Insurance.B, clist)));
        assertTrue(!c1.equals(new Client("Bob", "Sylvestre", null, clist)));
        assertTrue(!c1.equals(new Client("Bob", "Sylvestre", Insurance.B, new ArrayList<Claim>())));
        assertTrue(c.equals(new Client()));
    }

    @Test
    public void hashCodeTest() {
        int result = c1.getFirstName().hashCode();
        result = 31 * result + c1.getName().hashCode();
        result = 31 * result + (c1.getInsurance() != null ? c1.getInsurance().hashCode() : 0);
        result = 31 * result + (c1.getClaimList() != null ? c1.getClaimList().hashCode() : 0);

        assertEquals(result, c1.hashCode());
    }

    @After
    public void tearDown() {
        c = null;
        c1 = null;
        clist = null;
    }
}
