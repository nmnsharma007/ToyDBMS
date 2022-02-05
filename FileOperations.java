import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileOperations {

    // write the table contents to a file in form of bytes
    public void writeToFile(Table table){
        try {
            FileOutputStream f = new FileOutputStream(new File(table.tabname+".db"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(table);
            o.close();
            f.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // read the byte array from a file and convert into object again to 
    // be operated on
    public Table readFromFile(String tablename){
        Table table = null;
        try{
            FileInputStream f = new FileInputStream(new File(tablename+".db"));
            ObjectInputStream o = new ObjectInputStream(f);
            table = (Table)o.readObject();
            o.close();
            f.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return table;
    }
}
