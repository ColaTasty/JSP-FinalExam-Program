package servlet.square;
/*
 * CREATE TIME 2019/6/9 23:46
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

import bean.UserBean;
import global.ResponseToClient;
import table.UserRegisterTableItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 黎江
 */
public class ContactUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseToClient responseToClient = new ResponseToClient();
        String str_user_id = request.getParameter("user_id");
        if (str_user_id == null) {
            responseToClient.responseToClient(false, "错误的访问！", response);
            return;
        }
        int user_id = Integer.parseInt(str_user_id);
        UserBean userBean = UserRegisterTableItem.getUser(user_id);
        if (userBean == null) {
            responseToClient.responseToClient(false, "查找失败，可能TA已经离开这个社区咯~", response);
            return;
        }
        responseToClient.setJsonValue("user_name", userBean.getUser_name() == null ? "神秘的用户" : userBean.getUser_name());
        responseToClient.setJsonValue("gender", userBean.getGender() == 0 ? "女" : "男");
        responseToClient.setJsonValue("email", userBean.getEmail() == null ? "TA还没有填写邮箱" : userBean.getEmail());
        responseToClient.setJsonValue("mobile", userBean.getMobile() == null ? "TA还没有填写手机号" : userBean.getMobile());
        responseToClient.responseToClient(true,"查找成功",response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseToClient.doNotSupportGet(response);
    }
}
