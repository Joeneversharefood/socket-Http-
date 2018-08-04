package HttpServer;

import java.io.*;

public class Response {
    //外部文件获取配置的登录数据
    ReadFile readFiles = new ReadFile();
    String username = (String) readFiles.readFile().get("username");
    String password = (String) readFiles.readFile().get("password");

    private Request request;//获取请求对象
    private OutputStream outputStream;
    public void setRequest(Request requests){
        this.request = requests;
    }
    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
    /**response响应方法**/
    public void sendResponse(){
        //登陆表单
        String form =   "<center>\n" +
                        "<h2>Welcome to Login!</h2>\n" +
                        "<form action=\"http://127.0.0.1:8080/login\" method=\"post\">\n" +
                        "username:<input type=\"text\" name=\"username\">\n" +
                        "<br>\n" +
                        "password:<input type=\"password\" name=\"password\">\n" +
                        "<br><br>\n" +
                        "<input type=\"submit\" value=\"Submit\">\n" +
                        "</form>\n" +
                        "</center>";
        //请正确访问地址
        String rightPath =     "<center>\n" +
                        "<h2>请正确输入首页访问地址：127.0.0.1:8080/index</h1>\n" +
                        "</center>";
        //登陆成功/失败
        String success = "<center>\n" +
                        "<h2>登陆成功！Welcome</h1>\n" +
                        "</center>";
        String fail =   "<center>\n" +
                        "<h2>账号密码错误！登录失败！</h1>\n" +
                        "</center>";

        /** Response响应头输出，此处简单处理状态码**/
        PrintWriter printWriter = new PrintWriter(outputStream);
        //返回一个状态行
        printWriter.println("HTTP/1.0 200 OK");
        //返回一个首部
        printWriter.println("Content-Type:text/html;charset=" + request.getEncoding());
        //根据HTTP协议空行将结束头部信息
        printWriter.println();

        /**
         * response响应资源
         * 判断请求路径*
         **/
        //路径只有/的情况
        if (request.getUrl().length() == 1){
            printWriter.println(rightPath);
        }
        //请求路径有添加时
        else if (request.getUrl().length()>1){
            //提交form表单时
            if (request.getUrl().equals("/login")){
                //System.out.println("********************"+request.map.get("username"));
                if (request.map!=null){
                    //使用equals比较，登陆成功
                    if (request.map.get("username").equals(username) && request.map.get("password").equals(password)){
                        printWriter.println(success);
                    }else {
                        //登陆失败
                        printWriter.println(fail);
                    }
                }

            }else if (request.getUrl().equals("/index")){
                printWriter.println(form);
            }
            else {
                //随便乱输入路径时
                printWriter.println(rightPath);
            }
        }
        printWriter.close();
    }
}
