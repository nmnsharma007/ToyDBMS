import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

class QueryParser {
    // the parser method
    public String parser(String query) {
        StringTokenizer stringTokenizer = new StringTokenizer(query);
        String intermediateCode = "";
        while(stringTokenizer.hasMoreTokens()){
            String token = stringTokenizer.nextToken();
            // if create keyword occurs
            if(token.equals("create")){
                intermediateCode += "create_table ";
                String tableName = stringTokenizer.nextToken();
                intermediateCode += tableName + "\n";
                int number = Integer.parseInt(stringTokenizer.nextToken());
                for(int i = 0; i < number;++i){
                    String type = stringTokenizer.nextToken();
                    String name = stringTokenizer.nextToken();
                    intermediateCode += "add_attribute " + type + " " + name + "\n";
                }
                intermediateCode += "save_tab " + tableName + "\n";
                intermediateCode += "exists_tab " + tableName + "\n";
            }
            else if(token.equals("insert")){// if insert keyword occurs
                token = stringTokenizer.nextToken();// into keyword
                String tableName = stringTokenizer.nextToken();// get the table name
                token = stringTokenizer.nextToken();// values keyword
                intermediateCode += "insert_into " + tableName + " ";
                token = stringTokenizer.nextToken();// get the values of the attributes
                intermediateCode += token + "\n"; 
            }
        }
        return intermediateCode;
    }

}

class RuntimeEngine{

    private Table createTable(String token){
        String tabname = token.split(" ")[1];
        Table table = new Table();
        table.tabname = tabname;
        return table;
    }

    private void addAttribute(String token){
        String type = token.split(" ")[1];
        String name = token.split(" ")[2];

    }

    public void executeCode(String code){
        StringTokenizer sTokenizer = new StringTokenizer(code,"\n");
        while(sTokenizer.hasMoreTokens()){
            String token = sTokenizer.nextToken();
            if(token.startsWith("create_table")){
                Table tb = createTable(token);
            }
            else if(token.startsWith("insert_into")){

            }
            else if(token.startsWith("add_attribute")){
                addAttribute(token);
            }
            else if(token.startsWith("save_tab")){

            }
            else if(token.startsWith("exists_tab")){

            }
        }
    }
}

public class CS19B029 {

    private static String getQuery(String progfile){
        BufferedReader bReader;
        String code = "";
        try {
            bReader = new BufferedReader(new FileReader(progfile));
            String query = "";
            String contentLine = bReader.readLine();
            while(contentLine != null){
                query += contentLine + "\n";
                contentLine = bReader.readLine();// read the commands line by line
            }
            QueryParser queryparser = new QueryParser();
            code = queryparser.parser(query);
            bReader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return code;
    }

    public static void main(String[] args) {
       String intermediateCode = getQuery("prog1.txt");
       RuntimeEngine engine = new RuntimeEngine();
       engine.executeCode(intermediateCode);// execute the steps of the intermediate code
    }
}