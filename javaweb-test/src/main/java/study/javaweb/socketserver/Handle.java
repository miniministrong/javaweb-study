package study.javaweb.socketserver;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Handle implements Runnable{
    private Socket socket;

    public Handle(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
            process(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void process(InputStream in, OutputStream out) throws IOException {
        // 将我们的输入数据存储到缓冲流当中，效率较高，全部加载完直接输出
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        // 布尔标记，表示请求是否成功
        boolean acceptOK = false;
        String line = reader.readLine();
        // 判断HTTP请求头的方法，GET方法，HTTP/1开始的协议
        if (line.startsWith("GET / HTTP/1")) {
            acceptOK = true;
        }
        // 遍历请求并打印输出
        while (true) {
            line = reader.readLine();
            System.out.println(line);
            if (line.isEmpty()) {
                break;
            }
        }
        System.out.println(acceptOK ? "response ok!" : "response error!");

        if (acceptOK) {
            // 成功访问之后页面返回的信息
            String htmlStr = "<html><head><title>HelloWorld</title></head><body><h1>Hello World!</h1></body></html>";
            // 协议以及状态
            writer.write("HTTP/1.0 200 OK \r\n");
            // 长度
            writer.write("Content-Length : " + htmlStr.getBytes(StandardCharsets.UTF_8).length + "\r\n");
            // 响应类型
            writer.write("Content-Type : text/html \r\n");
            // 关闭连接
            writer.write("Connection : close \r\n");
            writer.write("\r\n");
            writer.write(htmlStr);
            writer.flush();
        } else {
            // 表示访问失败之后显示404
            writer.write("HTTP/1.0 404 NOT FOUND \r\n");
            writer.write("<html><head><title>404 NOT FOUND</title></head><body><h1>404 NOT FOUND</h1></body></html>");
            writer.write("Content-Length : 0 \r\n");
            writer.write("\r\n");
            writer.flush();
        }
    }
}
