package client;

import claim.Claim;
import communication.Form;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by root on 11/10/14.
 */
public class ClientManagerTest {

    private ClientManager cm, cm1;
    private ArrayList<Client> clientList;
    private Client cl;

    @Before
    public void setUp() {
        cm = new ClientManager();
        cl = new Client();
        clientList = new ArrayList<Client>();
        clientList.add(cl);
        cm1 = new ClientManager(clientList);
    }

    @Test
    public void testClientManager() {
        assertEquals(new ArrayList<Client>(), cm.getClientList());
        assertEquals(clientList, cm1.getClientList());
    }

    @Test
    public void testCheckClient() {
        assertNull(cm.checkClient("Antoine", "DeCaunes"));
        assertEquals(new Client(), cm1.checkClient("firstname", "name"));
    }

    @Test
    public void sendFormTest() {
        assertTrue(cm1.sendForm("firstname", "name", Form.A));
        assertTrue(!cm1.sendForm("René", "La Taupe", Form.A));
    }

    @After
    public void tearDown() {
        cm = null;
        cm1 = null;
        clientList = null;
        cl = null;
    }
}
