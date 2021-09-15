package study.javaweb.socketserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8081);
            while (true) {
                // 监听当前8080端口的网络连接，都接受
                Socket socket = serverSocket.accept();
                Thread handle = new Thread(new Handle(socket));
                handle.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket == null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
