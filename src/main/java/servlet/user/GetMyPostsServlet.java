package servlet.user;
/*
 * CREATE TIME 2019/6/12 1:22
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

import bean.PostListBean;
import com.alibaba.fastjson.JSON;
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
public class GetMyPostsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str_user_id = request.getParameter("user_id");
        String str_page = request.getParameter("page");
        ResponseToClient responseToClient = new ResponseToClient();
        if (str_user_id == null || str_page == null){
            responseToClient.responseToClient(false,"请正确的访问",response);
            return;
        }
        int user_id = Integer.parseInt(str_user_id);
        int page = Integer.parseInt(str_page);
        PostTableItem postTableItem = new PostTableItem(DBConnecter.connecter);
        PostListBean postListBean = new PostListBean();
        postListBean.setPosts(postTableItem.getPostWriteByMyself(user_id,page));
        postListBean.setPage(page);
        postListBean.setPosts_total(PostTableItem.countMyPosts(user_id));
        if (postListBean.getPosts() != null){
            responseToClient.setResultStatus(true);
            responseToClient.setMsg("找到啦");
            responseToClient.setJsonValue("posts", JSON.toJSONString(postListBean));
            responseToClient.responseToClient(response);
        }else {
            responseToClient.responseToClient(false,"你还没有发过帖子！",response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseToClient.doNotSupportGet(response);
    }
}
