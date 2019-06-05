package servlet.account;

import table.UserRegisterTableItem;
import global.ResponseToClient;
import global.config.DBConnecter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 黎江
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accout = request.getParameter("account");
        String password = request.getParameter("password");
        UserRegisterTableItem urtitem = new UserRegisterTableItem(DBConnecter.connecter);
        ResponseToClient responseToClient = new ResponseToClient();
        if (urtitem.isVaildAccount(accout,password)){
            responseToClient.responseToClient(true,"登陆成功",response);
        }
        else {
            if (!urtitem.isExistAccount(accout)){
                responseToClient.responseToClient(false,"账号不存在",response);
                return;
            }
            responseToClient.responseToClient(false,"密码错误",response);
        }
    }
}
