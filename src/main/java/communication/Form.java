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
            case A:
                formA();
                break;
            case B:
                formB();
                break;
            case C:
                formC();
                break;
            default:
        }
    }

    private void formA() {
        put("field1", null);
    }

    private void formB() {
        put("field1", null);
        put("field2", null);
    }

    private void formC() {
        put("field1", null);
        put("field2", null);
        put("field3", null);
    }

    // Accessors

    public FormType getType() {
        return type;
    }

}
