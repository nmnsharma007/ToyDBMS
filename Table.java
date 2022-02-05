import java.io.Serializable;
import java.util.ArrayList;

// the table object consisting of a name,
// list of attributes as first row
// and object rows from then onwards
public class Table implements Serializable{
    
    public String tabname;
    public ArrayList<Attribute> attr_list;
    public ArrayList<DataRow> rows;

    Table() {
        attr_list = new ArrayList<>();
        rows = new ArrayList<>();
    }

    @Override
    public String toString(){
        return tabname + " " + attr_list + " " + rows;
    }
}
