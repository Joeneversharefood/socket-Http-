package Service;

import Base.ReadFile;

import java.util.Map;

public class SelectService {
    ReadFile readFiles = new ReadFile();
    String admin = (String) readFiles.readFile().get("admin");
    String password = (String) readFiles.readFile().get("password");
    static String httpPost = "http://127.0.0.1:8080";

    public String selectAllEmployee(Map map){
        String s;
        String s1 = "<a href=\""+httpPost+"/get/addemployee\">添加用户</a>\n" +
                "<center>\n" +
                "<h2>管理员:"+admin+"&nbsp;&nbsp;&nbsp;&nbsp;欢迎进入Harry员工管理系统</h2>\n" +
                "<table border=\"1\">\n" +
                "  <tr>\n" +
                "    <th>员工编号</th>\n" +
                "    <th>姓名</th>\n" +
                "    <th>更新</th>\n" +
                "    <th>删除</th>\n" +
                "  </tr>\n";
        String s2 = "</table>\n" +
                "</center>";
        String s4 = "";//如果初始化为null则会被当作字符串拼接
        //数组初始化，注意数组大小
        //数据量大时不能使用数组
        String[] s3 = new String[map.size()];
        int i=0;
        for (Object key:map.keySet()){
            s3[i] = "<tr>\n" +
                 "<td>"+key+"</td>\n" +
                 "<td>"+map.get(key)+"</td>\n" +
                 "<td><a href=\""+httpPost+"/get/update/"+key+"\">update</a></td>\n" +
                 "<td><a href=\""+httpPost+"/delete/employee/"+key+"\">delete</a></td>\n" +
                 "</tr>\n";
            //循环拼接<tr>,每循环一次添加一条<tr>
            s4 += s3[i];
            i++;
        }
        s =s1+s4+s2;
        return s;
    }
}
