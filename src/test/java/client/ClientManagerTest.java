package client;

import claim.Claim;
import communication.Form;
import communication.FormType;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
    private ArrayList<Form> formList;
    private Client cl;
    private Form form;

    @Before
    public void setUp() {
        cm = new ClientManager();
        cl = new Client();
        clientList = new ArrayList<Client>();
        clientList.add(cl);
        formList = new ArrayList<Form>();
        formList.add(new Form());
        cm1 = new ClientManager(clientList, formList);
        form = new Form();
    }

    @Test
    public void testClientManager() {
        assertEquals(new ArrayList<Client>(), cm.getClientList());
        assertEquals(clientList, cm1.getClientList());
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
    }
}
