package Base;

import Service.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Response {
    private Request request;//获取请求对象
    private String url;//获取请求路径
    String endurl;//获取url最后一位
    private OutputStream outputStream;
    public void setRequest(Request requests){
        this.request = requests;
    }
    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /** service对象 **/
    AdminLogin adminLogin = new AdminLogin();
    AddService addService = new AddService();
    UpdateService updateService = new UpdateService();
    DeleteService deleteService = new DeleteService();
    SelectService selectService = new SelectService();
    //员工信息库
    //此map需要共享里面的数据，必须为静态，static的作用
    private static Map<String,String> employeeMap = new HashMap<String, String>(){{
        //put(名字，密码)
        put("1","juju");
        put("2","kojuju");
        put("3","harry");
    }};

    /**response响应方法**/
    public void sendResponse(){
        url = request.getUrl();
        endurl = url.substring(url.length()-1,url.length());

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
        //url不为空
        if (url!=null){
            /**返回管理员登陆表单**/
            if (url.equalsIgnoreCase("/get/login")){
                printWriter.println(AdminLogin.loginForm);
            }
            /**提交管理员登陆数据**/
            else if (url.equalsIgnoreCase("/post/login")){
                //判断是否为管理员
                printWriter.println(adminLogin.login(request));
            }
            /** 添加员工 获取add表单 **/
            else if (url.equalsIgnoreCase("/get/addemployee")){
                printWriter.println(AddService.addForm);
            }
            /**处理add逻辑**/
            else if (url.equalsIgnoreCase("/post/addemployee")){
                //判断employeeMap中是否存在添加的键（员工编号）
                //employee中已存在该键return添加失败
                printWriter.println(addService.addEmployee(request,employeeMap));
            }
            /** 修改员工信息 获取修改表单 **/
            else if (url.equalsIgnoreCase("/get/update/"+endurl)){
                printWriter.println(updateService.update(endurl));
            }
            /**处理update逻辑**/
            else if (url.equalsIgnoreCase("/put/update")){
                //如果map键已存在则put为修改该键的value
               printWriter.println(updateService.updateEmployee(employeeMap,request));
            }
            /** 删除员工信息 **/
            else if (url.equalsIgnoreCase("/delete/employee/"+endurl)){
                printWriter.println(deleteService.deleteEmployee(endurl,employeeMap));
            }
            /**查询方法 模仿jsp实现**/ //需要判断管理员是否登陆才能进入首页
            else if (url.equalsIgnoreCase("/get/allemployee")){
                printWriter.println(selectService.selectAllEmployee(employeeMap));
            }else {
                printWriter.println("<center><h2>请访问正确的路径！管理员登陆页面/get/login</h2></center>");
            }
        }
        printWriter.println("您的请求路径是："+request.getUrl());
        printWriter.close();
    }
}
