import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// the main class
public class CS19B029 {

    // get the query string from a file to be run
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
       String intermediateCode = getQuery("cs19b029.query");// get the query string from a file
       RuntimeEngine engine = new RuntimeEngine();// get the runtime engine
       engine.executeCode(intermediateCode);// execute the steps of the intermediate code
       for(int i = 1; i <= 10;++i){
            System.out.println(engine.loadTable("mytab" + i).toString());
       }
    }
}