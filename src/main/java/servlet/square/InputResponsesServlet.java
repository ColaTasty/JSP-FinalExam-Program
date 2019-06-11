package servlet.square;

import global.ResponseToClient;
import global.config.DBConnecter;
import table.Post_ResponseTableItem;

import java.io.IOException;
import javax.servlet.http.*;

/**
 * @author 杨子豪，温剑
 */
public class InputResponsesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setHeader("content-type", "application/json;charset=utf-8");
        String Post_id = request.getParameter("Post_id");    //主贴人id
        String User_id = request.getParameter("User_id");    //回帖人id
        String Content = request.getParameter("Content");    //回复内容
        long nowTime = System.currentTimeMillis() / 1000;    //回复时间

        int post_id = Integer.parseInt(Post_id);
        int user_id = Integer.parseInt(User_id);
        ResponseToClient responseToClient = new ResponseToClient();
        Post_ResponseTableItem rptitem = new Post_ResponseTableItem(DBConnecter.connecter);            //实例化，调用操作post_Response表方法
        if (rptitem.isRelease(post_id, user_id, Content, nowTime)) {
            responseToClient.responseToClient(true, "回复成功", response);
        } else {
            responseToClient.responseToClient(false, " 回复失败，输入内容有误", response);
        }

    }
}
