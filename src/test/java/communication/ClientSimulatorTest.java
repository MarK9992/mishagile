package communication;

import claim.Claim;
import client.Client;
import client.Insurance;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by root on 13/10/14.
 */
public class ClientSimulatorTest {

    private ClientSimulator clientSim;
    private Form f1, f2, f3, f4;
    private Client c1;

    @Before
    public void setUp() {
	clientSim = ClientSimulator.getInstance();
	c1 = new Client("Bob", "Sylvestre", Insurance.A, new ArrayList<Claim>());
	f1 = new Form(FormType.B, c1);
	f2 = new Form(FormType.B, c1);
	f2.put("field1", "goodanswer");
	f2.put("field2", "goodanswer");
	f3 = new Form(FormType.B, c1);
	f3.put("field1", "falseanswer");
	f3.put("field2", "falseanswer");
	f4 = new Form(FormType.B, c1);
    }

    @Test
    public void testFillGoodForm() {
	assertEquals(f2, clientSim.fillForm(f1, 0));
    }

    @Test
    public void testFillFalseForm() {
	assertEquals(f3, clientSim.fillForm(f1, 1));
    }

    @Test
    public void testFillNotForm() {
	assertEquals(f4, clientSim.fillForm(f1, 2));
    }

    @After
    public void tearDown() {
	clientSim = null;
	f1 = null;
	f2 = null;
	f3 = null;
	c1 = null;
	f4 = f1;
    }
}
