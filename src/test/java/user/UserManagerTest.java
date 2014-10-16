package user;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by root on 11/10/14.
 */

public class UserManagerTest {

    private UserManager um, um1;
    private ArrayList<UserAccount> ual, ual2;

    @Before
    public void setUp() {
	um = new UserManager();
	ual = new ArrayList<UserAccount>();
	ual2 = new ArrayList<UserAccount>();
	ual.add(new UserAccount());
	ual.add(new UserAccount("acd", "acd", UserRank.ACD));
	ual.add(new UserAccount("bcd", "bcd", UserRank.BCD));
	ual.add(new UserAccount("cd", "cd", UserRank.CD));
	ual.add(new UserAccount("fin", "fin", UserRank.FIN));
	ual2.add(new UserAccount("acd", "acd", UserRank.ACD));
	ual2.add(new UserAccount("bcd", "bcd", UserRank.BCD));
	ual2.add(new UserAccount("cd", "cd", UserRank.CD));
	ual2.add(new UserAccount("fin", "fin", UserRank.FIN));
	um1 = new UserManager(ual);
    }

    @Test
    public void UserManagerTest() {
	assertEquals(ual2, um.getList());
	assertEquals(ual, um1.getList());
    }

    @Test
    public void LoginTest() {
	assertNull(um.login("login", "password"));
	assertNull(um1.login("Bob", "agile"));
	assertEquals(new UserAccount(), um1.login("login", "password"));
    }

    @After
    public void tearDown() {
	um = null;
	um1 = null;
	ual = null;
	ual2 = null;
    }
}
