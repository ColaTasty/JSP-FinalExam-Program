<%@ page import="bean.PostBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="javafx.geometry.Pos" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="bean.PostListBean" %>
<%@ page import="table.CollectionsTableItem" %>
<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@include file="jsp_header.jsp" %>
<jsp:useBean id="postListBean" class="bean.PostListBean" scope="request"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>广场</title>
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
        时间：2019/6/4
        描述：首页广场界面
    -->
<!-- 头部 -->
<div class="header"></div>
<!-- 主体 -->
<!-- 技术日记 -->
<div class="container div_divider">
    <div class="row">
        <!-- 文章列表 -->
        <div class="col-xs-9">
            <div class="list-group div_article">
                <!-- 子头栏 -->
                <a href="#" class="list-group-item active item_article_first">
                    <h4 class="list-group-item-heading">
                        发现
                    </h4>
                </a>
                <!-- 帖子列表 -->
                <%
                    if (postListBean.getPosts() != null) {
                        for (PostBean postBean : postListBean.getPosts()) {
                            String content = postBean.getContent();
                            int post_id = postBean.getPost_id();
                            int user_id = postBean.getUser_id();
                            String image_path = postBean.getImages_path();
                            long time = postBean.getTime();
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = new Date(time * 1000);
                            String str_time = simpleDateFormat.format(date);
                            out.println("<div class=\"list-group-item item_article\">\n" +
                                    "                    <div class=\"row\">\n" +
                                    "                        <div class=\"div_center col-xs-9\">\n" +
                                    "                            <p class=\"list-group-item-text div_article_content\">\n" +
                                    content + "\n" +
                                    "                            </p>\n" +
                                    "                            <p class=\"list-group-item-text div_article\">\n" +
                                    str_time + "\n" +
                                    "                            </p>\n"
                            );
                            if (CollectionsTableItem.isCollected(post_id, userBean.getUser_id()))
                                out.println("<a href=\"javascript:void(0)\" id=\"col\" pid=" + post_id + " bool=\"1\" onclick=\"col_onclick(this)\">取消收藏</a>\n");
                            else
                                out.println("<a href=\"javascript:void(0)\" id=\"col\" pid=" + post_id + " bool=\"0\" onclick=\"col_onclick(this)\">收藏</a>\n");
                            out.println("                        &nbsp;\n" +
                                    "                            <a href=\"javascript:void(0)\" id=\"user\" uid=" + user_id + " onclick=\"user_onclick(this)\" data-toggle=\"modal\" data-target=\"#myModal\">点击联系我</a>\n" +
                                    "                        </div>\n" +
                                    "                        <!-- 右侧图片，信息 -->\n");
                            if (image_path != null) {
                                out.println("                        <div class=\"col-xs-3 div_right_info\">\n" +
                                        "                            <img class=\"iv_article img-rounded\" src=\"" + image_path + "\" alt=\"\">\n" +
                                        "                        </div>\n");
                            } else {
                                out.println("                        <div class=\"col-xs-3 div_right_info\">\n" +
                                        "                            <img class=\"iv_article img-rounded\" src=\"/src/img/user.ico\" alt=\"\">\n" +
                                        "                        </div>\n");
                            }
                            out.println("                    </div>\n" +
                                    "                </div>\n");
                        }
                    } else {
                        out.println("<script>");
                        out.println("alert(\"还没有人发帖噢~\");");
                        out.println("</script>");
                    }
                %>
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
                                    <div class="col-md-8" id="modal-name">正在加载...</div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-md-4">性别</div>
                                    <div class="col-md-8" id="modal-gender">正在加载...</div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-md-4">手机号</div>
                                    <div class="col-md-8" id="modal-mobile">正在加载...</div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-md-4">邮箱</div>
                                    <div class="col-md-8" id="modal-email">正在加载...</div>
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
            <!-- 导航条 -->
            <nav aria-label="Page navigation" style="text-align: center">
                <ul class="pagination">
                    <!-- 上一页 -->
                    <li>
                        <%
                            int page_num = postListBean.getPage();
                            int tmp = postListBean.getPosts_total() % 10;
                            int total_posts = postListBean.getPosts_total();
                            int total_page = tmp > 0 ? ((total_posts - tmp) / 10) + 1 : (total_posts - tmp) / 10;
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
        </div>
        <!-- 右侧 -->
        <div class="col-xs-3 div_record">
            <!-- 用户信息 -->
            <form action="/publish-information" method="post" enctype="multipart/form-data">
                <div class="jumbotron div_userinfo">
                    <img class="iv_user_head img-circle" src="/src/img/user.ico">
                    <div style="display: inline-block; margin-left: 12px;font-size: 18px;">用户名</div>
                </div>
                <!-- 随手记录 -->
                <div style="display: flex;">
                    <div style="flex: 1">
                        <hr>
                    </div>
                    <div style="text-align: center;line-height: 48px;color: #34374C">发布交友信息</div>
                    <div style="flex: 1">
                        <hr>
                    </div>
                </div>
                <textarea class="form-control" rows="3" id="post-content" name="content"
                          placeholder="随手写下你的心情吧~"></textarea>
                <br>
                选择图片：
                <div class="div_save">
                    <input type="file" id="post-picture" accept="image/*"/>
                </div>
                <br>
                <div class="div_save">
                    <button type="button" id="btn-submit" class="btn btn-primary btn_save_record">发布</button>
                </div>
                <script>
                    $(function () {
                        $("#btn-submit").click(function () {
                            var formData = new FormData();
                            formData.append("content", $("#post-content").val());
                            formData.append("post_picture", $("#post-picture")[0].files[0]);
                            $.ajax({
                                url: "/publish-information",
                                dataType: "json",
                                data: formData,
                                type: "post",
                                contentType: false,
                                processData: false,
                                success: function (res) {
                                    alert(res.msg);
                                    if (!res.isOK) {
                                        if (res.deleted)
                                            alert("图片上传失败！");
                                        return;
                                    }
                                    window.location.reload();
                                },
                                error: function () {
                                    alert("提交失败！请检查网络！");
                                }
                            })
                        })
                    })
                </script>
            </form>
            <hr>
            <!-- 退出 -->
            <div class="row div_little_func">
                <div class="col-xs-12" style="text-align: center">
                    <button class="btn btn-default btn-cricle btn_stay" id="btn-logout" data-toggle="modal"
                            data-target="#loginModal">
                        Exit
                    </button>
                    <script>
                        $("#btn-logout").click(function () {
                            if (confirm("确定退出登录吗？"))
                                window.location.href = "/logout";
                        });
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var user_onclick = function (a) {
        $(function () {
            var _this = a;
            var user_id = _this.getAttribute("uid");
            var name_item = $("#modal-name");
            var gender_item = $("#modal-gender");
            var email_item = $("#modal-email");
            var mobile_item = $("#modal-mobile");
            name_item.text("正在加载...");
            gender_item.text("正在加载...");
            email_item.text("正在加载...");
            mobile_item.text("正在加载...");
            $.ajax({
                url: "/contact-user",
                method: "post",
                dataType: "json",
                data: {user_id: user_id},
                success: function (res) {
                    if (!res.isOK) {
                        alert(res.msg);
                        name_item.text("查找失败");
                        gender_item.text("查找失败");
                        email_item.text("查找失败");
                        mobile_item.text("查找失败");
                        return;
                    }
                    name_item.text(res.user_name);
                    gender_item.text(res.gender);
                    email_item.text(res.email);
                    mobile_item.text(res.mobile);
                },
                error: function () {
                    alert("查询失败！请检查网络连接！");
                }
            })
        })
    };
    var col_onclick = function (a) {
        $(function () {
            var _this = a;
            var pid = _this.getAttribute("pid");
            var is_coled = _this.getAttribute("bool");
            $.ajax({
                url: "/collect-post",
                method: "post",
                dataType: "json",
                data: {
                    post_id: pid,
                    is_coled: is_coled,
                    user_id:<%= userBean.getUser_id()%>
                },
                success: function (res) {
                    alert(res.msg);
                    if (!res.isOK) {
                        return;
                    }
                    _this.setAttribute("bool", is_coled === "1" ? "0" : "1");
                    _this.innerText = _this.getAttribute("bool") === "1" ? "取消收藏" : "收藏";
                },
                error: function () {
                    alert("网络连接错误！");
                }
            })
        })
    }
</script>
</body>
</html>