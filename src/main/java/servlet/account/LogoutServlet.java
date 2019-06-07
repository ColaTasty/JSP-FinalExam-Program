package servlet.account; /**
 * CREATE TIME 2019/6/7 19:08
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

import bean.UserBean;
import com.sun.corba.se.impl.transport.ReadTCPTimeoutsImpl;
import global.ResponseToClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 黎江
 */
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type","text/html;charset=utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        session.setAttribute("userBean",new UserBean());
        out.println("<html>");
        out.println("<head>");
        out.println("<style>");
        out.println("a:link,a:visited,a:active{color:black;text-decoration:none}");
        out.println("a:hover{color:#576b95;text-decoration:none}");
        out.println("</style>");
        out.println("<title>");
        out.println("退出登录成功");
        out.println("</title>");
        out.println("<body>");
        out.println("<h1 style=\"text-align:center;\">");
        out.println("退出成功");
        out.println("</h1>");
        out.println("<h1 style=\"text-align:center;\">");
        out.println("<a href=\"/\">");
        out.println("点我返回");
        out.println("</a>");
        out.println("</h1>");
        out.println("</body>");
        out.println("</head>");
        out.println("</html>");
    }
}
