<%@ page import="table.UserRegisterTableItem" %>
<%@ page import="global.config.DBConnecter" %><%--
  Created by IntelliJ IDEA.
  User: Makia98 黎江
  Date: 2019/6/6
  Time: 0:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<jsp:useBean id="userBean" class="bean.UserBean" scope="session"/>
<%
    out.print("<!-- use jsp_header -->");
    if (userBean.getAccount() == null) {
        response.sendRedirect("/");
    } else
        session.setAttribute("userBean", UserRegisterTableItem.refreshUserBean(userBean.getUser_id()));
%>
