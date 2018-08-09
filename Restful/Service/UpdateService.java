package Service;

import Base.Request;

import java.util.Map;

public class UpdateService {
    static String httpPost = "http://127.0.0.1:8080";

    public String update(String endurl){
        String s = "<center>\n" +
                "<form action=\""+httpPost+"/put/update\" method=\"POST\">\n" +
                "EmployeeID:<br>\n" +
                "<input type=\"text\" name=\"EmployeeID\" value=\""+endurl+"\" readonly=\"readonly\">\n" +
                "<br>\n" +
                "Employee:<br>\n" +
                "<input type=\"text\" name=\"Employee\">\n" +
                "<br><br>\n" +
                "<input type=\"submit\" value=\"Update Employee\">\n" +
                "</form> \n" +
                "</center>";
        return s;
    }

    public String updateEmployee(Map map, Request request){
        String s = null;
        if (map.containsKey(request.map.get("EmployeeID"))){
            map.put(request.map.get("EmployeeID"),request.map.get("Employee"));
            s = "<meta http-equiv=\"refresh\" content=\"3;" +
                    "url="+httpPost+"/get/allemployee\" />\n" +
                    "<h2>update成功！3秒后跳转Home页</h2>";
        }else {
            s = "<h2>update失败！该员工不存在<h2>";
        }
        return s;
    }
}
