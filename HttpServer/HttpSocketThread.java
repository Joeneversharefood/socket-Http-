package HttpServer;

import java.io.IOException;
import java.net.Socket;

//使用多线程实现简单的并发
public class HttpSocketThread extends Thread {
    private Socket socketConnection;//客户端与服务器之间的连接
    public HttpSocketThread(Socket socketConnection){
        this.socketConnection = socketConnection;
    }

    //重写线程的run方法
    public void run(){
        if (socketConnection != null){
            System.out.println("线程"+Thread.currentThread().getName());
            System.out.println("客户端名："+socketConnection);
            try {
                //获取连接传输的数据并调用解析方法
                Request request = new Request(socketConnection.getInputStream());
                request.parseRequest();

                Response response = new Response();
                response.setRequest(request);
                response.setOutputStream(socketConnection.getOutputStream());
                response.sendResponse();

                //关闭连接
               socketConnection.close();
            } catch (IOException e) {
                System.out.println("HTTP服务器错误"+e.getLocalizedMessage());
            }
        }
    }
}
