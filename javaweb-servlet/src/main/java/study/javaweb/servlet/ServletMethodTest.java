package study.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// WebServlet注解时Servlet包提供的注解，他可以将设置我们当前项目Servlet在网站上的目录
@WebServlet(name = "method", urlPatterns = "/method")
public class ServletMethodTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 设置响应代码，默认200
        response.setStatus(200);
        // 设置body类型
        response.setContentType("text/html");
        // 设置字符编码
        response.setCharacterEncoding("utf-8");
        // 设置一个header的值
        response.setHeader("content-type","utf-8");
        // 给响应加一个cookie
        response.addCookie(new Cookie("test", "test"));
        // 给响应加一个header
        response.addHeader("content-type", "utf-8");


        // 返回请求方法
        System.out.println("method : " + request.getMethod());  // method : GET
        // 返回请求路径
        System.out.println("uri : " + request.getRequestURI()); // uri : hello
        // 返回请求参数，例如， "name=Bob&a=1&b=2" ；
        System.out.println("queryString : "  + request.getQueryString()); // queryString : null
        // 返回请求参数，GET请求从URL读取参数，POST请求从Body中读取参数；
        System.out.println("parameter : " + request.getParameter("name")); // parameter : null
        // 获取请求Body的类型，例如， "application/x-www-form-urlencoded" ；
        System.out.println("content-type : " + request.getContentType()); // content-type : null
        // 获取当前Webapp挂载的路径，对于ROOT来说，总是返回空字符串 "" ；
        System.out.println("context-path : " + request.getContextPath()); // context-path :
        // 返回请求携带的所有Cookie；
        System.out.println("cookie : " + String.valueOf(request.getCookies()[0])); // cookie : javax.servlet.http.Cookie@30c7973
        // 获取指定的Header，对Header名称不区分大小写；
        System.out.println("header : " + request.getHeader("name")); // header : null
        // 返回所有Header名称；
        System.out.println("headername : " + String.valueOf(request.getHeaderNames())); // headername : org.apache.tomcat.util.http.NamesEnumerator@384b0de
        // 如果该请求带有HTTP Body，该方法将打开一个输入流用于读取Body；
        System.out.println("inputstream : " + String.valueOf(request.getInputStream())); // inputstream : org.apache.catalina.connector.CoyoteInputStream@6323863c
        // 和getInputStream()类似，但打开的是Reader；
        System.out.println("reader : " + String.valueOf(request.getReader()));
        // 返回客户端的IP地址；
        System.out.println("remote-addr : " + request.getRemoteAddr());
        // 返回协议类型，例如， "http" ， "https" ；
        System.out.println("scheme : " + request.getScheme());

    }
}
