package communication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by root on 12/10/14.
 */
public class FormTest {

    private Form f;

    @Before
    public void setUp() {
        f = new Form();
    }

    @After
    public void tearDown() {
        f = null;
    }

    @Test
    public void testForm() {
        assertEquals(FormType.A, f.getType());
    }
}
