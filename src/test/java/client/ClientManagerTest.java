package client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by root on 11/10/14.
 */
public class ClientManagerTest {

    private ClientManager cm, cm1;
    private ArrayList<Client> clientList;

    @Before
    public void setUp() {
        cm = new ClientManager();
        clientList = new ArrayList<Client>();
        clientList.add(new Client());
        cm1 = new ClientManager(clientList);
    }

    @Test
    public void testClientManager() {
        assertEquals(new ArrayList<Client>(), cm.getClientList());
        assertEquals(clientList, cm1.getClientList());
    }

    @After
    public void tearDown() {
        cm = null;
        cm1 = null;
        clientList = null;
    }
}
