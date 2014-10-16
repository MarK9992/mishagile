package client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import claim.Claim;
import communication.Form;
import communication.FormType;

/**
 * Created by root on 11/10/14.
 */
public class ClientManagerTest {

    private ClientManager cm, cm1, cm2;
    private ArrayList<Client> clientList, list;
    private ArrayList<Form> formList;
    private Client cl, cl1, cl2;
    private Form form;
    private Claim claim;

    @Before
    public void setUp() {
	cm = new ClientManager();
	cl = new Client();
	clientList = new ArrayList<Client>();
	clientList.add(cl);
	formList = new ArrayList<Form>();
	// formList.add(new Form());
	cm1 = new ClientManager(clientList, formList);
	form = new Form();
	cl1 = new Client("Bob", "Sinclar", Insurance.D, new ArrayList<Claim>());
	cl2 = new Client("Pierre", "Menes", Insurance.A, new ArrayList<Claim>());
	claim = new Claim();
	cl2.getClaimList().add(claim);
	list = new ArrayList<Client>();
	list.add(cl1);
	list.add(cl2);
	list.add(cl);
	cm2 = new ClientManager(list, new ArrayList<Form>());
    }

    @Test
    public void testSearchClientByName() {
	ArrayList<Client> results = cm2.searchClient("Bob", "Sinclar");

	assertEquals(1, results.size());
	assertTrue(results.contains(cl1));
    }

    @Test
    public void testAddClaimToClient() {
	assertEquals(0, cm2.getList().get(0).getClaimList().size());
	cm2.addClaimToClient(cm2.getList().get(0), new Claim());
	assertEquals(1, cm2.getList().get(0).getClaimList().size());
	assertEquals(new Claim(), cm2.getList().get(0).getClaimList()
		.get(0));
    }

    @Test
    public void testIndexClient() {
	assertEquals(1, cm2.indexClient("Pierre", "Menes"));
    }

    @Test
    public void testAddClient() {
	assertEquals(0, cm.getList().size());
	cm.addClient("Yassine", "Tijani", 'A');
	assertEquals(1, cm.getList().size());
	assertEquals(new Client("Yassine", "Tijani", Insurance.A,
		new ArrayList<Claim>()), cm.getList().get(0));
    }

    @Test
    public void testSearchClientByInsurance() {
	ArrayList<Client> results = cm2.searchClient(Insurance.D);

	assertEquals(2, results.size());
	assertTrue(results.contains(cl));
	assertTrue(results.contains(cl1));
    }

    @Test
    public void testSearchClientByClaim() {
	assertEquals(cl2, cm2.searchClient(claim));
	assertNull(cm.searchClient(claim));
    }

    @Test
    public void testClientManager() {
	assertEquals(new ArrayList<Client>(), cm.getList());
	assertEquals(clientList, cm1.getList());
	assertEquals(formList, cm1.getFormList());
    }

    @Test
    public void testCheckClient() {
	assertNull(cm.checkClient("Antoine", "DeCaunes"));
	assertEquals(new Client(), cm1.checkClient("firstname", "name"));
    }

    @Test
    public void sendFormTest() {
	assertTrue(cm1.sendForm("firstname", "name", FormType.A));
	assertTrue(!cm1.sendForm("René", "La Taupe", FormType.A));
	assertEquals(form, cm1.getFormList().get(cm1.getFormList().size() - 1));
    }

    @Test
    public void checkFormTest() {
	Form f;

	cm1.sendForm("firstname", "name", FormType.A);
	f = cm1.checkForm("firstname", "name", FormType.A);
	assertEquals(FormType.A, f.getType());
	assertEquals("firstname", f.getClient().getFirstName());
	assertEquals("name", f.getClient().getName());
	assertNull(cm1.checkForm("firstname", "false", FormType.A));
	assertNull(cm1.checkForm("firstname", "name", FormType.B));
	assertTrue(!cm1.getFormList().contains(f));
    }

    @After
    public void tearDown() {
	cm = null;
	cm1 = null;
	clientList = null;
	cl = null;
	formList = null;
	form = null;
	cl1 = null;
	cl2 = null;
	list = null;
	cm2 = null;
	claim = null;
    }
}
