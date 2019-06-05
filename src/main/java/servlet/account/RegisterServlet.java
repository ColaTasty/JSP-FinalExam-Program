package servlet.account;

import bean.AccountBean;
import table.UserRegisterTableItem;
import global.ResponseToClient;
import global.config.DBConnecter;
import java.io.IOException;
import javax.servlet.http.*;

/**
 * @author 黎江
 */
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setHeader("content-type", "application/json;charset=utf-8");
        ResponseToClient rtc = new ResponseToClient();
        String account = request.getParameter("account");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        if (account == null || (email == null && mobile==null) ){
            rtc.responseToClient(false,"请输入正确的信息",response.getWriter());
            return;
        }
        AccountBean accountBean = new AccountBean();
        UserRegisterTableItem urtItem = new UserRegisterTableItem(DBConnecter.connecter);
        if (urtItem.isExistAccount(account)) {
            rtc.responseToClient(false, "这个帐号已被注册了！", response.getWriter());
        }
        if (urtItem.isExistEmail(email) && email != null) {
            rtc.responseToClient(false, "这个邮箱已被注册了！", response.getWriter());
        }
        if (urtItem.isExistMobile(mobile) && mobile != null) {
            rtc.responseToClient(false, "这个手机号已被注册了！", response.getWriter());
        }
        accountBean.setAccount(request.getParameter("servlet/account"));
        accountBean.setPassword(request.getParameter("password"));
        accountBean.setEmail(request.getParameter("email"));
        accountBean.setMobile(request.getParameter("mobile"));
        accountBean.setRegisterTime(System.currentTimeMillis() / 1000);
        rtc.responseToClient(true, "注册成功！", response.getWriter());
    }
}
