package communication;

import java.util.Iterator;
import java.util.Set;

// TODO singleton

/**
 * Created by root on 13/10/14.
 */
public class ClientSimulator {

    public static int CORRECTLY = 1, INCORRECTLY = 2;

    // Methods

    /**
     * Fills a given form. If rand < 1, fills it correctly, if rand < 2, fills it incorrectly, otherwise doesn't fill it.
     * @param form the form to fill
     * @param rand the filling pattern
     * @return the filled form
     */
    public Form fillForm(Form form, int rand) {
        Set<String> keys = form.keySet();
        Iterator<String> it = keys.iterator();
        String key;

        if(rand < CORRECTLY) {
            while(it.hasNext()) {
                key = it.next();
                form.put(key, "goodanswer");
            }
        }
        else if (rand < INCORRECTLY) {
            while(it.hasNext()) {
                key = it.next();
                form.put(key, "falseanswer");
            }
        }
        return form;
    }
}
