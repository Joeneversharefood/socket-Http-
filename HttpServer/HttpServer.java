package HttpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private static int port = 8080;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                //监听方法
                Socket socketConnection = serverSocket.accept();
                //调用线程，传入客户端和服务器连接
                /**使用多线程多客户端访问，单线程多客户端访问，服务器出错链接断开
                 * 多线程是线程数量过多也会造成堆栈内存溢出错误**/
                Thread thread = new HttpSocketThread(socketConnection);
                thread.start();
//                Request request = new Request(socketConnection.getInputStream());
//                request.parseRequest();
//                Response response = new Response();
//                response.setRequest(request);
//                response.setOutputStream(socketConnection.getOutputStream());
//                response.sendResponse();
//                socketConnection.close();
                System.out.println("HTTP服务器正在运行，端口："+port);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
