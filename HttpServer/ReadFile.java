package HttpServer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadFile {
    private InputStream inputStream;
    private Map map = new HashMap();
    public Map readFile(){
        try {
            inputStream = new BufferedInputStream(new FileInputStream(new File("src/HttpServer/login.properties")));
            Properties properties = new Properties();
            properties.load(inputStream);
            map.put("username",properties.getProperty("user"));
            map.put("password",properties.getProperty("password"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

//    public static void main(String[] args) {
//        ReadFile r = new ReadFile();
//        r.readFile();
//        if (r.readFile()!=null){
//            System.out.println(r.readFile().get("username"));
//        }
//    }
}
