package servlet.contact;
/*
 * CREATE TIME 2019/6/11 21:10
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

import bean.UserListBean;
import global.ResponseToClient;
import global.config.DBConnecter;
import table.UserRegisterTableItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 黎江
 */
public class ViewUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseToClient.illegalVisit(response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str_page = request.getParameter("page");
        if (str_page == null){
            ResponseToClient.illegalVisit(response);
            return;
        }
        int page = Integer.parseInt(str_page);
        UserListBean userListBean = new UserListBean();
        UserRegisterTableItem userRegisterTableItem = new UserRegisterTableItem(DBConnecter.connecter);
        userListBean.setUser_count(UserRegisterTableItem.countUsers());
        userListBean.setPage(page);
        userListBean.setUsersBean(userRegisterTableItem.getUsersList(page));
        request.setAttribute("userListBean",userListBean);
        request.getRequestDispatcher("/home_friends.jsp").forward(request,response);
    }
}
