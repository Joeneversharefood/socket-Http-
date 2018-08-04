package HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class Request {
    protected Map map = new HashMap();//储存post请求的参数
    private String url;//请求路径
    private InputStream inputStream;
    private String encoding = "UTF-8";//请求编码

    Request(InputStream inputStream){
        this.inputStream = inputStream;
    }
    public String getUrl(){
        return  url;
    }
    public  String getEncoding(){
        return encoding;
    }
    //解析请求
    public void parseRequest() throws IOException {
        System.out.println("****************客户端发送的消息");
        //请求地址
        String line = readLine(inputStream,0);
        System.out.println("request头部："+line);

        //获取资源路径，最后一个斜杠（版本协议）出现时往前五个即请求路径
        url = line.substring(line.indexOf('/'),line.lastIndexOf('/') - 5);
        //获取请求方法
        /**此处简单判断request的GET和POST请求方法**/
        String method = new StringTokenizer(line).nextElement().toString();
        //如果是post方法，则会有消息体长度
        int contentLength = 0;
        //读取HTTP首部
        do {
            line =readLine(inputStream,0);
            //如果有Content-Length消息头时输出
            if (line.startsWith("Content-Length")){
                contentLength = Integer.parseInt(line.split(":")[1].trim());
            }
            //打印元数据信息
            System.out.println("request头部："+line);
        }while (!line.equals("\r\n"));//如果遇到回车，表示请求头结束

        /**如果是post请求，消息体的读取**/
        if ("POST".equalsIgnoreCase(method)){
            //简单的处理表单提交的参数，而对于上传文件这里是不能这样处理的
            //因为上传的文件时消息体不止一行，会有多行消息体
            //POST数据：username=juju&password=123456
            String postValue = readLine(inputStream,contentLength);
            if (postValue!=null){
                //当post数据是参数类型
                if (postValue.contains("&") && postValue.contains("=")){
                    //拆分参数字符串
                    for (String param : postValue.split("&")){
                        String[] pairParam =param.split("=");
                        //将参数的键值对存入map
                        map.put(pairParam[0],pairParam[1]);
//                        System.out.println("22222222222222222"+pairParam[0]+pairParam[1]);
                    }
                }else {
                    //当post数据非参数类型时
                }
            }
            System.out.println("POST数据："+postValue);
        }
        //客户端发送消息结束
        System.out.println("客户端发送消息结束！********");
//        System.out.println("请求的路径是："+url);
//        System.out.println("请求方法是："+method);
    }

    //自定义读取方法
    private String readLine(InputStream inputStream,int contentLength) throws IOException {
        ArrayList<Object> arrayList = new ArrayList<>();
        byte readByte = 0;
        int total = 0;
        if (contentLength !=0){//post请求
            while (total < contentLength){
                readByte = (byte) inputStream.read();
                arrayList.add(Byte.valueOf(readByte));
                total++;
            }
        }else {//get请求
            while (readByte != 10){
                readByte = (byte) inputStream.read();
                arrayList.add(Byte.valueOf(readByte));
            }
        }
        //临时数组
        byte[] tempByteArr = new byte[arrayList.size()];
        for (int i = 0;i < arrayList.size();i++){
            tempByteArr[i] = (byte) arrayList.get(i);
        }
        arrayList.clear();
        String tempStr = new String(tempByteArr,encoding);

        if (tempStr.startsWith("Referer")){//如果有Referer标签时使用
            tempStr = new String(tempByteArr,"UTF-8");
        }
        return tempStr;
    }
}
