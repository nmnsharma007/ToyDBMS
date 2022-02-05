import java.util.StringTokenizer;

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
        String [] arr = token.split(" ");
        String tablename = arr[1];
        Table table = loadTable(tablename);
        
    }

    // method to save the table to file
    private void saveTable(Table table){
        FileOperations fOperations = new FileOperations();
        fOperations.writeToFile(table);
    }

    // method to load the table from file
    private Table loadTable(String tablename){
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