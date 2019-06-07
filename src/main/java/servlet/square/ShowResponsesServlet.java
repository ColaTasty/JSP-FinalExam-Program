package servlet.square;

import table.Post_ResponseTableItem;
import global.ResponseToClient;
import global.config.DBConnecter;
import java.io.IOException;
import javax.servlet.http.*;

/**
 * @author 杨子豪，温剑
 */
public class ShowResponsesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setHeader("content-type", "application/json;charset=utf-8");
		Post_ResponseTableItem  rptitem= new Post_ResponseTableItem(DBConnecter.connecter);			//实例化，调用操作post_Response表方法
        ResponseToClient responseToClient = new ResponseToClient();
        if (rptitem.isQuery()){
            responseToClient.responseToClient(true,"查询成功",response);
            return;
        }
        else{
             responseToClient.responseToClient(false," 查询失败",response);
             return;
        }   

    }
}
