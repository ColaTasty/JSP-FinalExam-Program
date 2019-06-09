package servlet.square;

import table.UserRegisterTableItem;
import table.PostTableItem;
import global.ResponseToClient;
import global.config.DBConnecter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 杨子豪
 */
public class PublishInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pUser_id = request.getParameter("user_id");                     //用户名
        String pContent = request.getParameter("content");                     //发布内容
        String pImage = request.getParameter("pImage");                         //发布图片
        if (pUser_id == null || pContent == null){
            ResponseToClient.illegalVisit(response);
            return;
        }
        long nowTime = System.currentTimeMillis() / 1000;                          //发布时间
        int user_id = Integer.parseInt(pUser_id);
        ResponseToClient responseToClient = new ResponseToClient();
        PostTableItem ptitem = new PostTableItem(DBConnecter.connecter);           //实例化，调用操作post表方法
        if (ptitem.isRelease(user_id, pContent, pImage, nowTime)) {
            responseToClient.responseToClient(true, "发布成功", response);
        } else {
            responseToClient.responseToClient(false, " 发布失败，输入内容有误", response);
        }
    }
}
