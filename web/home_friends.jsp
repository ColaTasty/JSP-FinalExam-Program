<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="bean.UserListBean" %>
<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="jsp_header.jsp" %>
<jsp:useBean id="userListBean" class="bean.UserListBean" scope="request"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>交友</title>
    <link rel="stylesheet" href="src/libs/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="src/css/index.css">
    <link rel="shortcut icon" href="src/img/touch-icon.png" type="img/x-icon">
    <script src="src/libs/jq-3.2.1/jquery-3.2.1.min.js"></script>
    <script src="src/libs/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $(".header").load("header.jsp");
        });
    </script>
</head>
<body>
<!--
        作者：吴迈星、杜健聪、蒋斌
        时间：2019/6/6
        描述：交友界面
    -->
<!-- 头部 -->
<div class="header"></div>
<!-- 主体 -->
<div class="container div_divider">
    <div class="row">
        <!-- 文章列表 -->
        <div class="col-xs-12">
            <div class="list-group div_article">
                <!-- 子头栏 -->
                <a href="#" class="list-group-item active item_article_first">
                    <h4 class="list-group-item-heading">
                        交友
                    </h4>
                </a>
                <!-- 帖子列表 -->
                <!-- start -->
                <c:if test="${userListBean != null}">
                    <c:forEach items="${userListBean.getUsersBean()}" var="user">
                        <div class="col-xs-6 col-md-3">
                            <a href="#" class="thumbnail">
                                <img alt="100%x180" src="/src/img/ic_p8.jpg"
                                     style="height: 180px; width: 100%; display: block;">
                            </a>
                            <div class="caption">
                                <c:if test="${user.user_name != null}">
                                    <h3>${user.user_name}</h3>
                                </c:if>
                                <c:if test="${user.user_name == null}">
                                    <h3>没有姓名的用户</h3>
                                </c:if>
                                <c:if test="${user.gender == 1}">
                                    <h4>男</h4>
                                </c:if>
                                <c:if test="${user.gender == 0}">
                                    <h4>女</h4>
                                </c:if>
                                <div style="display: none;"></div>
                                <p id="user-detail">
                                    <a href="javascript:void(0)" onclick="user_detail_onClick(this)"
                                       uid="${user.user_id}" class="btn btn-primary" role="button" data-toggle="modal"
                                       data-target="#myModal">
                                        详细资料
                                    </a>
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <script>
                    var user_detail_onClick = function (e) {
                        $(function () {
                            var _this = e;
                            var modal = $("#myModal");
                            var uid = _this.getAttribute("uid");
                            console.log(uid);
                            modal.find('#username').val("正在加载...");
                            modal.find('#gender').val("正在加载...");
                            modal.find('#phone').val("正在加载...");
                            modal.find('#email').val("正在加载...");
                            $.ajax({
                                url: "/contact-user",
                                type: "post",
                                dataType: "json",
                                data: {user_id: uid},
                                success: function (res) {
                                    if (!res.isOK) {
                                        alert(res.msg);
                                        modal.find('#username').val("查找失败");
                                        modal.find('#gender').val("查找失败");
                                        modal.find('#phone').val("查找失败");
                                        modal.find('#email').val("查找失败");
                                        return;
                                    }
                                    modal.find('#username').val(res.user_name);
                                    modal.find('#gender').val(res.gender);
                                    modal.find('#phone').val(res.email);
                                    modal.find('#email').val(res.mobile);
                                },
                                error: function () {
                                    alert("查询失败！请检查网络连接！");
                                }
                            });
                        });
                    }
                </script>
                <!-- End -->
                <!-- 模态框 -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;
                                </button>
                                <h4 class="modal-title" id="myModalLabel">用户详细资料</h4>
                            </div>
                            <div class="modal-body">
                                <!-- 详细信息 -->
                                <div class="row">
                                    <div class="col-md-4">姓名</div>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="username" readonly="readonly"/>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-md-4">性别</div>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="gender" readonly="readonly"/>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-md-4">手机号</div>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="phone" readonly="readonly"/>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-md-4">邮箱</div>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="email" readonly="readonly"/>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 模态框End -->
            </div>
        </div>
    </div>
    <!-- 导航条 -->
    <nav aria-label="Page navigation" style="text-align: center">
        <ul class="pagination">
            <!-- 上一页 -->
            <li>
                <%
                    int page_num = userListBean.getPage();
                    int tmp = userListBean.getUser_count() % 10;
                    int total_users = userListBean.getUser_count();
                    int total_page = tmp > 0 ? ((total_users - tmp) / 10) + 1 : (total_users - tmp) / 10;
                    if (page_num != 1) {
                        out.println("<a href=\"/query-square?page=" + (page_num - 1) + "\" aria-label=\"Previous\">\n" +
                                "                            <span aria-hidden=\"true\">&laquo;</span>\n" +
                                "                        </a>");
                    }
                %>
            </li>
            <!-- 页码 -->
            <li>
                <%
                    int page_index = 1;
                    while (page_index <= total_page) {
                        if (page_index == page_num)
                            out.println("<a href=\"/query-square?page=" + page_index + "\" style=\"background-color:#ccc\">" + (page_index++) + "</a>");
                        else
                            out.println("<a href=\"/query-square?page=" + page_index + "\">" + (page_index++) + "</a>");
                    }
                %>
            </li>
            <!-- 下一页 -->
            <li>
                <%
                    if (page_num < total_page) {
                        out.println("<a href=\"/query-square?page=" + (page_num + 1) + "\" aria-label=\"Previous\">\n" +
                                "                            <span aria-hidden=\"true\">&raquo;</span>\n" +
                                "                        </a>");
                    }
                %>
            </li>
        </ul>
    </nav>
    <!-- 导航条End -->
</div>
</body>
</html>