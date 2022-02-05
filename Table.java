import java.io.Serializable;
import java.util.ArrayList;

public class Table implements Serializable{
    
    public String tabname;
    public ArrayList<Attribute> attr_list;

    Table() {
        attr_list = new ArrayList<>();
    }

    @Override
    public String toString(){
        return tabname + " " + attr_list;
    }
}
