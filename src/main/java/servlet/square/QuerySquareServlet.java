package servlet.square;

import bean.PostBean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import global.TransJson;
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
 * @author 杨子豪
 * 这应该返回或者设置Bean，由于没有给出JavaBean，所有暂时无法查询
 */
public class QuerySquareServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostTableItem ptitem = new PostTableItem(DBConnecter.connecter);            //实例化，调用操作post表方法
        ResponseToClient responseToClient = new ResponseToClient();
        JSONObject jsonObject = responseToClient.getJson();
        int page = Integer.parseInt(request.getParameter("page"));
//        没有页码，返回错误
        if (page <= 0){
            ResponseToClient.illegalVisit(response);
            return;
        }
        List<PostBean> posts = ptitem.isQuery(page);
//        查询失败，返回错误
        if (posts == null) {
            responseToClient.responseToClient(false, "查询失败", response);
            return;
        }
        responseToClient.setResultStatus(true);
        responseToClient.setJsonValue("msg", "查询成功！");
        jsonObject.put("posts", JSON.toJSONString(posts));
        responseToClient.responseToClient(response.getWriter());
    }
}
