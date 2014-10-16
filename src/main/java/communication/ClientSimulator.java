package communication;

import java.util.Iterator;
import java.util.Set;

// TODO singleton

/**
 * Created by root on 13/10/14.
 */
public final class ClientSimulator {

    // Attributes

    private static int CORRECTLY = 1, INCORRECTLY = 2;
    public static int RANGE = 3;

    private static ClientSimulator instance = null;

    // Constructors

    private ClientSimulator() {

    }

    public static ClientSimulator getInstance() {
	if (instance == null) {
	    instance = new ClientSimulator();
	}
	return instance;
    }

    // Methods

    /**
     * Fills a given form. If rand < 1, fills it correctly, if rand < 2, fills
     * it incorrectly, otherwise doesn't fill it.
     * 
     * @param form
     *            the form to fill
     * @param rand
     *            the filling pattern
     * @return the filled form
     */
    public Form fillForm(Form form, double rand) {
	Set<String> keys = form.keySet();
	Iterator<String> it = keys.iterator();
	String key;

	if (rand < CORRECTLY) {
	    while (it.hasNext()) {
		key = it.next();
		form.put(key, "goodanswer");
	    }
	} else if (rand < INCORRECTLY) {
	    while (it.hasNext()) {
		key = it.next();
		form.put(key, "falseanswer");
	    }
	}
	return form;
    }
}
