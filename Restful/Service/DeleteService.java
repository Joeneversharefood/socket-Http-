package Service;

import java.util.Map;

public class DeleteService {
    static String httpPost = "http://127.0.0.1:8080";
    public String deleteEmployee(String endurl, Map map){
        String s = null;
        int mapStartSize = map.size();
        int mapEndSize;
        map.remove(endurl);
        mapEndSize = map.size();
        if (mapEndSize<mapStartSize){
            s = "<meta http-equiv=\"refresh\" content=\"3;" +
                    "url="+httpPost+"/get/allemployee\" />\n" +
                    "<h2>删除成功！3秒后跳转Home页</h2>";
        }else {
            s = "<h2>删除失败！请重新操作！***</h2>";
        }
        return s;
    }

}
