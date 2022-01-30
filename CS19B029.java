import java.util.StringTokenizer;

class QueryParser {

    public void parser(String query) {
        StringTokenizer stringTokenizer = new StringTokenizer(query);
        String intermediateCode = "";
        while(stringTokenizer.hasMoreTokens()){
            String token = stringTokenizer.nextToken();
            if(token.equals("create")){
                intermediateCode = intermediateCode + "create_table ";
                String tableName = stringTokenizer.nextToken();
                intermediateCode = intermediateCode + tableName + "\n";
                int number = Integer.parseInt(stringTokenizer.nextToken());
                for(int i = 0; i < number;++i){
                    String type = stringTokenizer.nextToken();
                    String name = stringTokenizer.nextToken();
                    intermediateCode = intermediateCode + "add_attribute " + type + " " + name + "\n";
                }
            }
        }
        System.out.println(intermediateCode);
        // first get it printed properly
    }

}

public class CS19B029 {

    public static void main(String[] args) {
       QueryParser queryparser = new QueryParser();
       queryparser.parser("create mytab 2\nint a\nstring b");
    }
}