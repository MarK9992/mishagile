package communication;

import client.Client;

import java.util.HashMap;

/**
 * Created by root on 12/10/14.
 */
public class Form extends HashMap<String, String> {

    // Attributes

    private FormType type;
    private Client client;

    // Constructors

    public Form() {
        this(FormType.A, new Client());
    }

    public Form(FormType formType, Client client) {
        type = formType;
        this.client = client;

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

    // Methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Form)) return false;
        if (!super.equals(o)) return false;

        Form form = (Form) o;

        if (!client.equals(form.client)) return false;
        if (type != form.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + client.hashCode();
        return result;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return the string representation of the object
     */
    @Override
    public String toString() {
        return "Form{" +
                "type=" + type +
                ", client=" + client +
                "} " + super.toString();
    }

    // Accessors

    public FormType getType() {
        return type;
    }

    public Client getClient() {
        return client;
    }

}
