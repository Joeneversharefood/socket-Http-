package Service;

import Base.Request;

import java.util.Map;

public class AddService {
    static String httpPost = "http://127.0.0.1:8080";

    public static String addForm = "<center>\n" +
            "<form action=\""+httpPost+"/post/addemployee\" method=\"POST\">\n" +
            "EmployeeID:<br>\n" +
            "<input type=\"text\" name=\"EmployeeID\">\n" +
            "<br>\n" +
            "Employee:<br>\n" +
            "<input type=\"text\" name=\"Employee\">\n" +
            "<br><br>\n" +
            "<input type=\"submit\" value=\"Add Employee\">\n" +
            "</form> \n" +
            "</center>";
    public String addEmployee(Request request, Map map){
        String s = null;
        if (map.containsKey(request.map.get("EmployeeID"))){
            s = "<h2>添加失败！员工编号重复或者输入格式不正确！***********</h2>\n";
        }else {
            map.put(request.map.get("EmployeeID"),request.map.get("Employee"));
            s = "<meta http-equiv=\"refresh\" content=\"3;" +
                    "url="+httpPost+"/get/allemployee\" />\n" +
                    "<h2>添加成功！3秒后跳转到Home页</h2>";
        }

        return s;
    }
}
