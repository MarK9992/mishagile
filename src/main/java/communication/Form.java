package communication;

import java.util.HashMap;

/**
 * Created by root on 12/10/14.
 */
public class Form extends HashMap<String, String> {

    // Attributes

    private FormType type;

    // Constructors

    public Form() {
        this(FormType.A);
    }

    public Form(FormType formType) {
        type = formType;

        switch (type) {

        }
    }

    // Accessors

    public FormType getType() {
        return type;
    }

}
