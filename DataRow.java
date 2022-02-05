import java.io.Serializable;
import java.util.ArrayList;

// a data row in a table
public class DataRow implements Serializable {
    
    public ArrayList<Object> contents;

    DataRow(){
        contents = new ArrayList<>();
    }

    @Override
    public String toString(){
        return contents.toString();
    }
}
