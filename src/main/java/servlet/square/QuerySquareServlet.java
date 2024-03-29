package servlet.square;

import bean.PostBean;
import bean.PostListBean;
import table.PostTableItem;
import global.ResponseToClient;
import global.config.DBConnecter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 杨子豪、温剑
 */
public class QuerySquareServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "application/json;charset=utf-8");
        PostTableItem ptitem = new PostTableItem(DBConnecter.connecter);            //实例化，调用操作post表方法
        String str_page = request.getParameter("page");
//        没有页码，返回错误
        if (str_page == null) {
            System.out.println("page : " + str_page);
            ResponseToClient.illegalVisit(response);
            return;
        }
        int page = Integer.parseInt(str_page);
        List<PostBean> posts = ptitem.getSquarePostList(page);
//        查询失败，返回错误
        if (posts == null && PostTableItem.countPosts() > 0) {
            System.out.println("posts_count : error");
            ResponseToClient.illegalVisit(response);
            return;
        }
        PostListBean postListBean = new PostListBean();
        postListBean.setPosts(posts);
        postListBean.setPage(page);
        postListBean.setPosts_total(PostTableItem.countPosts());
        request.setAttribute("postListBean", postListBean);
        request.getRequestDispatcher("/home_ground.jsp").forward(request, response);
    }
}
