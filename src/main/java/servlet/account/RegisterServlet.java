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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws NullPointerException,javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws NullPointerException,javax.servlet.ServletException, IOException {
        ResponseToClient rtc = new ResponseToClient();
        String account = request.getParameter("account");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        if (account == null || (email == null && mobile == null)) {
            rtc.responseToClient(false, "请输入正确的信息", response);
            return;
        }
        AccountBean accountBean = new AccountBean();
        UserRegisterTableItem urtItem = new UserRegisterTableItem(DBConnecter.connecter);
        if (urtItem.isExistAccount(account)) {
            System.out.println(account);
            rtc.responseToClient(false, "这个帐号已被注册了！", response);
            return;
        }
        if (urtItem.isExistEmail(email) && email != null && email.length() > 0) {
            System.out.println(email);
            rtc.responseToClient(false, "这个邮箱已被注册了！", response);
            return;
        }
        if (urtItem.isExistMobile(mobile) && mobile != null && mobile.length() > 0) {
            System.out.println(mobile);
            rtc.responseToClient(false, "这个手机号已被注册了！", response);
            return;
        }
        accountBean.setAccount(account);
        accountBean.setUser_name(request.getParameter("nickname"));
        accountBean.setPassword(request.getParameter("password"));
        accountBean.setEmail(email.length() > 0 ? email:null);
        accountBean.setMobile(mobile.length() > 0 ? mobile:null);
        accountBean.setRegisterTime(System.currentTimeMillis() / 1000);
        if (urtItem.register(accountBean)) {
            rtc.responseToClient(true, "注册成功！", response);
        } else {
            rtc.responseToClient(false, "注册失败！", response);
        }
    }
}
