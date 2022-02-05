import java.io.Serializable;

// a simple attribute for a table
public class Attribute implements Serializable{

    public Class type;// type of attribute
    public String name;// name of the attribute

    @Override
    public String toString() {
        return "(" + type + " " + name + ")";
    }
}
