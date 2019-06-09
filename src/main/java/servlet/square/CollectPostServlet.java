package servlet.square;
/*
 * CREATE TIME 2019/6/9 20:15
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

import global.ResponseToClient;
import global.config.DBConnecter;
import table.CollectionsTableItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 黎江、杨子豪、温剑
 */
public class CollectPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str_collect_user_id = request.getParameter("user_id");
        String str_collect_post_id = request.getParameter("post_id");
        String str_collect_is_coled = request.getParameter("is_coled");
        ResponseToClient responseToClient = new ResponseToClient();
        CollectionsTableItem collectionsTableItem = new CollectionsTableItem(DBConnecter.connecter);
        if (str_collect_post_id == null || str_collect_user_id == null || str_collect_is_coled == null) {
            responseToClient.responseToClient(false, "错误的访问！", response);
            return;
        }
        int collect_post_id = Integer.parseInt(str_collect_post_id);
        int collect_user_id = Integer.parseInt(str_collect_user_id);
        boolean collect_is_coled = str_collect_is_coled.equals("1");
        responseToClient.setJsonValue("statement",collect_is_coled ? "cancel_collect":"collect");
        if (!collect_is_coled) {
            if (CollectionsTableItem.isCollected(collect_post_id, collect_user_id)) {
                responseToClient.responseToClient(false, "已经收藏过啦！", response);
                return;
            }
            if (collectionsTableItem.collectPost(collect_post_id, collect_user_id)) {
                responseToClient.responseToClient(true, "收藏成功！", response);
            } else {
                responseToClient.responseToClient(false, "收藏失败，请刷新页面！", response);
            }
        } else {
            if (!CollectionsTableItem.isCollected(collect_post_id, collect_user_id)) {
                responseToClient.responseToClient(false, "你还未收藏过！", response);
                return;
            }
            if (collectionsTableItem.cancelCollectPost(collect_post_id, collect_user_id)) {
                responseToClient.responseToClient(true, "取消收藏成功！", response);
            } else {
                responseToClient.responseToClient(false, "取消收藏失败，请刷新页面！", response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doPost(request, response);
        ResponseToClient.illegalVisit(response);
    }
}
