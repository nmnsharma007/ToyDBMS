import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

public class FileOperations {

    // write the table contents to a file in form of bytes
    public void writeToFile(Table table){
        try {
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            ObjectOutputStream o = new ObjectOutputStream(bStream);
            o.writeObject(table);
            byte[] byte_array = bStream.toByteArray();
            FileOutputStream outputStream = new FileOutputStream(new File(table.tabname+".db"));
            outputStream.write(byte_array);
            o.close();
            outputStream.close();
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
            byte[] byte_array = Files.readAllBytes(new File(tablename+".db").toPath());
            ByteArrayInputStream bStream = new ByteArrayInputStream(byte_array);
            ObjectInputStream o = new ObjectInputStream(bStream);
            table = (Table)o.readObject();
            o.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return table;
    }
}
