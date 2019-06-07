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
 * 这应该返回或者设置Bean，由于没有给出JavaBean，所有暂时无法查询
 */
public class QuerySquareServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        PostTableItem ptitem= new PostTableItem(DBConnecter.connecter);			//实例化，调用操作post表方法
        ResponseToClient responseToClient = new ResponseToClient();
        if (ptitem.isQuery()){
            responseToClient.responseToClient(true,"查询成功",response);
            return;
        }
        else{
             responseToClient.responseToClient(false," 查询失败",response);
             return;
        }   
    }
}
