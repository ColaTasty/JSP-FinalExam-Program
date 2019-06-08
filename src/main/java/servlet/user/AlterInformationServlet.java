package servlet.user;
/*
 * CREATE TIME 2019/6/7 19:44
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

import bean.UserBean;
import global.ResponseToClient;
import global.config.DBConnecter;
import table.UserRegisterTableItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.*;

/**
 * @author 黎江
 */
public class AlterInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=utf-8");
        try {
            UserBean session_userBean = (UserBean) request.getSession().getAttribute("userBean");
            if (session_userBean == null || session_userBean.getAccount() == null) {
                ResponseToClient.illegalVisit(response);
                return;
            }
            UserRegisterTableItem userRegisterTableItem = new UserRegisterTableItem(DBConnecter.connecter);
            UserBean userBean = userRegisterTableItem.getUserBean(session_userBean.getUser_id());
            HashMap<String, String> receive_keys = new HashMap<String, String>();
//            用户名
            String user_name = request.getParameter("user_name");
            if (user_name != null && !user_name.equals(userBean.getUser_name()))
                receive_keys.put("user_name", user_name);
//            邮箱
            String email = request.getParameter("email");
            if (email != null && !email.equals(userBean.getEmail())) {
                if (!userRegisterTableItem.isExistEmail(email))
                    receive_keys.put("email", email);
                else {
                    ResponseToClient.alertMsg("邮箱已被人注册了！",response);
                    return;
                }
            }
//            手机号
            String mobile = request.getParameter("mobile");
            if (mobile != null && !mobile.equals(userBean.getMobile())) {
                if (!userRegisterTableItem.isExistMobile(mobile))
                    receive_keys.put("mobile", mobile);
                else {
                    ResponseToClient.alertMsg("手机号已被人注册了！", response);
                    return;
                }
            }
//            性别
            String gender = request.getParameter("gender");
            if (gender != null && Integer.parseInt(gender) != userBean.getGender())
                receive_keys.put("gender", gender);
//            生日
            String birthday = request.getParameter("birthday");
            if (birthday != null && Integer.parseInt(birthday) != userBean.getBirthday())
                receive_keys.put("birthday", birthday);
            if (userRegisterTableItem.alterInformation(userBean.getUser_id(), receive_keys)) {
                ResponseToClient.alertMsg("修改成功", response);
            } else {
                ResponseToClient.alertMsg("修改失败！请返回重试！", response);
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseToClient.illegalVisit(response);
    }
}
