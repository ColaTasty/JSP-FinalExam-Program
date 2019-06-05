package servlet.user; /**
 * CREATE TIME 2019/6/5 22:30
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

import bean.UserBean;
import global.ResponseToClient;
import global.config.DBConnecter;
import table.UserRegisterTableItem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 黎江
 */
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseToClient responseToClient = new ResponseToClient();
        UserRegisterTableItem userRegisterTableItem = new UserRegisterTableItem(DBConnecter.connecter);
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        System.out.println("user-login : ");
        System.out.println(account);
        System.out.println(password);
        if (account.length() <= 0 || password.length() <= 0){
            request.getRequestDispatcher("/index.jsp").forward(request,response);
            return;
        }
        UserBean userBean = userRegisterTableItem.getUserBean(account,password);
        if (userBean != null){
            request.getSession().setAttribute("userBean",userBean);
            request.getRequestDispatcher("/home_friends.jsp").forward(request,response);
            return;
        }else {
            request.getRequestDispatcher("/index.jsp").forward(request,response);
            return;
        }
    }
}
