import java.util.StringTokenizer;

// database runtime engine
public class RuntimeEngine{

    // create a new table
    private Table createTable(String token){
        String tabname = token.split(" ")[1];
        Table table = new Table();
        table.tabname = tabname;
        return table;
    }

    // add an attribute to the given table
    private void addAttribute(Table table,String token){
        String type = token.split(" ")[1];
        String name = token.split(" ")[2];
        Attribute attribute = new Attribute();
        attribute.name = name;// assign the name of attribute
        // assign the type of attribute
        if(type.equals("int")){
            attribute.type = Integer.class;
        }
        else if(type.equals("string")){
            attribute.type = String.class;
        }
        // add the attribute to the list of attributes in the table
        table.attr_list.add(attribute);
    }

    // execute the insert into statement
    private void insertInto(String token){
        String [] arr = token.split("[ ,]+");
        String tablename = arr[1];
        Table table = loadTable(tablename);
        int position = 0;// pointer in the attribute list of the table
        DataRow row = new DataRow();// create a new row
        for(int i = 2; i < arr.length;++i){
            if(table.attr_list.get(position).type == Integer.class){
                row.contents.add(Integer.parseInt(arr[i]));   
            }
            else if(table.attr_list.get(position).type == String.class){
                row.contents.add(arr[i].substring(1, arr[i].length()-1));
            }
            ++position;
        }
        table.rows.add(row);// add the row to the table
        saveTable(table);// save the table to the file
    }

    // method to save the table to file
    private void saveTable(Table table){
        FileOperations fOperations = new FileOperations();
        fOperations.writeToFile(table);
    }

    // method to load the table from file
    public Table loadTable(String tablename){
        FileOperations fOperations = new FileOperations();
        return fOperations.readFromFile(tablename);
    }

    // print if the table exists and is saved to the memory
    private void existTable(String token){
        String tablename = token.split(" ")[1];
        if(loadTable(tablename) != null){
            System.out.println("Table exists");
        }
        else{
            System.out.println("Table doesn't exist");
        }
    }

    // execute the intermediate code line by line
    public void executeCode(String code){
        StringTokenizer sTokenizer = new StringTokenizer(code,"\n");
        Table tb = null;
        while(sTokenizer.hasMoreTokens()){
            String token = sTokenizer.nextToken();
            if(token.startsWith("create_table")){
                tb = createTable(token);
            }
            else if(token.startsWith("insert_into")){
                insertInto(token);
            }
            else if(token.startsWith("add_attribute")){
                addAttribute(tb,token);
            }
            else if(token.startsWith("save_tab")){
                saveTable(tb);
            }
            else if(token.startsWith("exists_tab")){
                existTable(token);
            }
        }
    }
}