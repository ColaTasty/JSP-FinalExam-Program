package servlet.user;
/*
 * CREATE TIME 2019/6/12 0:21
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

import bean.PostListBean;
import global.ResponseToClient;
import global.config.DBConnecter;
import table.PostTableItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 黎江
 */
public class DeletePostByMyselfServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str_post_id = request.getParameter("post_id");
        String str_user_id = request.getParameter("user_id");
        ResponseToClient responseToClient = new ResponseToClient();
        if (str_post_id == null || str_user_id == null){
            responseToClient.responseToClient(false,"请正确访问",response);
            return;
        }
        int post_id = Integer.parseInt(str_post_id);
        int user_id = Integer.parseInt(str_user_id);
        PostTableItem postTableItem = new PostTableItem(DBConnecter.connecter);
        boolean deleted = postTableItem.deletePostByMyself(post_id,user_id);
        if (deleted){
            responseToClient.responseToClient(true,"删除成功",response);
        }else {
            responseToClient.responseToClient(false,"删除失败",response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseToClient.doNotSupportGet(response);
    }
}
