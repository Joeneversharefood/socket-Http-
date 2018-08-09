package Base;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadFile {

    /**读取配置文件登陆信息**/
    private InputStream inputStream;
    private Map map = new HashMap();
    public Map readFile(){
        try {
            inputStream = new BufferedInputStream(new FileInputStream(new File("src/Base/admin.properties")));
            Properties properties = new Properties();
            properties.load(inputStream);
            map.put("admin",properties.getProperty("admin"));
            map.put("password",properties.getProperty("password"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**读取html文件**/
    public String readHtml(String url){
        return null;
    }



//    public static void main(String[] args) {
//        ReadFile r = new ReadFile();
//        r.readFile();
//        if (r.readFile()!=null){
//            System.out.println(r.readFile().get("username"));
//        }
//    }
}
