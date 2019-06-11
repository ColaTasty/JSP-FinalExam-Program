package servlet.user;
/*
 * CREATE TIME 2019/6/12 0:55
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

import bean.PostBean;
import bean.PostListBean;
import com.alibaba.fastjson.JSON;
import global.ResponseToClient;
import global.config.DBConnecter;
import javafx.geometry.Pos;
import table.CollectionsTableItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 黎江
 */
public class GetMyCollectionsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str_user_id = request.getParameter("user_id");
        String str_page = request.getParameter("page");
        ResponseToClient responseToClient = new ResponseToClient();
        if (str_user_id == null) {
            responseToClient.responseToClient(false, "请正确的访问", response);
            return;
        }
        int user_id = Integer.parseInt(str_user_id);
        int page = Integer.parseInt(str_page);
        CollectionsTableItem collectionsTableItem = new CollectionsTableItem(DBConnecter.connecter);
        PostListBean myCollectedPost = collectionsTableItem.getMyCollectedPost(user_id, page);
        if (myCollectedPost == null){
            responseToClient.responseToClient(false,"你还没有收藏！",response);
        }else {
            responseToClient.setResultStatus(true);
            responseToClient.setMsg("找到啦");
            responseToClient.setJsonValue("posts", JSON.toJSONString(myCollectedPost));
            responseToClient.responseToClient(response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseToClient.doNotSupportGet(response);
    }
}
