import java.util.StringTokenizer;

public class QueryParser {
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