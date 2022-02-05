import java.util.ArrayList;

public class Table extends DbItem{
    
    public String tabname;
    public ArrayList<Attribute> attr_list;

    Table() {
        attr_list = new ArrayList<>();
    }
}
