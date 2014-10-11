package user;

import org.junit.*;
import user.UserAccount;
import user.UserRank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by root on 11/10/14.
 */
public class UserAccountTest {

    UserAccount ua, ua1;

    @Before
    public void setUp() {
        ua = new UserAccount();
        ua1 = new UserAccount("Bob", "agile", UserRank.ACD);
    }

    @Test
    public void testUserAccount() {
        assertEquals("login", ua.getLogin());
        assertEquals("password", ua.getPassword());
        assertEquals(UserRank.CD, ua.getRank());
        assertEquals("Bob", ua1.getLogin());
        assertEquals("agile", ua1.getPassword());
        assertEquals(UserRank.ACD, ua1.getRank());
    }

    @Test
    public void testMatch() {
        assertTrue(ua.match("login", "password"));
        assertTrue(!ua.match("agile", "password"));
        assertTrue(ua1.match("Bob", "agile"));
        assertTrue(!ua1.match("Bob", "2014"));
    }

    @Test
    public void testEquals() {
        assertTrue(ua.equals(ua));
        assertTrue(!ua.equals(new Integer(1)));
        assertTrue(!ua.equals(new UserAccount("login", "agile", UserRank.CD)));
        assertTrue(!ua.equals(new UserAccount("agile", "password", UserRank.CD)));
        assertTrue(!ua.equals(new UserAccount("login", "password", UserRank.ACD)));
        assertTrue(ua.equals(new UserAccount()));
    }

    @Test
    public void testHashCode() {
        int result = ua.getLogin().hashCode();

        result = 31 * result + ua.getPassword().hashCode();
        result = 31 * result + ua.getRank().hashCode();

        assertEquals(result, ua.hashCode());
    }

    @After
    public void tearDown() {
        ua = null;
        ua1 = null;
    }
}
