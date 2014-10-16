package communication;

import claim.Claim;
import client.Client;
import client.Insurance;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by root on 12/10/14.
 */
public class FormTest {

    private Form f, f1, f2;
    private Client c1, c2;

    @Before
    public void setUp() {
	f = new Form();
	c1 = new Client("Bob", "Sinclar", Insurance.C, new ArrayList<Claim>());
	c2 = new Client("Joey", "Starr", Insurance.A, new ArrayList<Claim>());
	f1 = new Form(FormType.B, c1);
	f2 = new Form(FormType.C, c2);
    }

    @After
    public void tearDown() {
	f = null;
	f1 = null;
	f2 = null;
	c1 = null;
	c2 = null;
    }

    @Test
    public void testForm() {
	String[] fk = { "field1" }, f1k = { "field2", "field1" }, f2k = {
		"field3", "field2", "field1" };

	assertEquals(FormType.A, f.getType());
	assertEquals(fk, f.keySet().toArray());
	assertNotEquals(f1k, f.keySet().toArray());
	assertNull(f.get("field1"));
	assertEquals(new Client(), f.getClient());

	assertEquals(FormType.B, f1.getType());
	assertEquals(f1k, f1.keySet().toArray());
	assertNotEquals(fk, f.keySet().toArray());
	assertNull(f1.get("field1"));
	assertNull(f1.get("field2"));
	assertEquals(c1, f1.getClient());

	assertEquals(FormType.C, f2.getType());
	assertEquals(f2k, f2.keySet().toArray());
	assertNotEquals(fk, f.keySet().toArray());
	assertNull(f2.get("field1"));
	assertNull(f2.get("field2"));
	assertNull(f2.get("field3"));
	assertEquals(c2, f2.getClient());
    }
}
