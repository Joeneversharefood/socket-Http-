package Service;
import Base.ReadFile;
import Base.Request;

public class AdminLogin {
    ReadFile readFiles = new ReadFile();
    String admin = (String) readFiles.readFile().get("admin");
    String password = (String) readFiles.readFile().get("password");
    static String httpPost = "http://127.0.0.1:8080";

    public static String  loginForm = "<center>\n" +
            "    <h2>Welcome to Admin Login!</h2>\n" +
            "    <form action=\""+httpPost+"/post/login\" method=\"POST\">\n" +
            "        adminname:<input type=\"text\" name=\"username\">\n" +
            "        <br>\n" +
            "        password:<input type=\"password\" name=\"password\">\n" +
            "        <br><br>\n" +
            "        <input type=\"submit\" value=\"Submit\">\n" +
            "    </form>\n" +
            "</center>";

    public String login(Request request){
        String s = null;
        if (admin.equals(request.map.get("username")) && password.equals(request.map.get("password"))){
            s = "<meta http-equiv=\"refresh\" content=\"3;" +
                    "url="+httpPost+"/get/allemployee\" />\n" +
                    "<h2>登陆成功！3秒后进入Home Page</h2>";
        }else {
            s = "管理员登陆失败！重新登陆";
        }
        return s;
    }
}
