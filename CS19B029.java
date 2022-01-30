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

public class CS19B029 {

    public static void main(String[] args) {
       BufferedReader bReader;
       try {
           bReader = new BufferedReader(new FileReader("prog1.txt"));
           String query = "";
           String contentLine = bReader.readLine();
           while(contentLine != null){
                query += contentLine + "\n";
                contentLine = bReader.readLine();// read the commands line by line
           }
           System.out.println(query);
           QueryParser queryparser = new QueryParser();
           String code = queryparser.parser(query);
           BufferedWriter bWriter = new BufferedWriter(new FileWriter("cs19b029.query.code"));
           bWriter.write(code);
           bWriter.close();
           bReader.close();
       }
       catch(IOException e){
           e.printStackTrace();
       }
    }
}