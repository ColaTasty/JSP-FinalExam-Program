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
        ResponseToClient rtc = new ResponseToClient();
        String pUser_id = request.getParameter("pUser_id");    //用户名
        String pContent = request.getParameter("pContent");    //发布内容
        String pImage = request.getParameter("pImage");        //发布图片
        long nowTime = System.currentTimeMillis() / 1000;    //发布时间
        int pCount = 0;                            //点赞
        int pStatus = 1;                                        //发布状态
        if (pContent == null && pImage == null) {
            rtc.responseToClient(false, "请输入发布的信息或图片", response);
            return;
        }
        int user_id = Integer.parseInt(pUser_id);
        UserRegisterTableItem urtitem = new UserRegisterTableItem(DBConnecter.connecter);
        String pname = urtitem.getUserName(user_id);
        ResponseToClient responseToClient = new ResponseToClient();
        PostTableItem ptitem = new PostTableItem(DBConnecter.connecter);            //实例化，调用操作post表方法
        if (ptitem.isRelease(user_id, pContent, pImage, nowTime, pCount, pStatus)) {
            responseToClient.responseToClient(true, "发布成功", response);
        } else {
            responseToClient.responseToClient(false, " 发布失败，输入内容有误", response);
        }
    }
}
