<%@ page import="bean.PostBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="javafx.geometry.Pos" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.lang.reflect.Array" %>
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
        作者：吴迈星
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
                    for(PostBean postBean:postListBean.getPosts()){
                        String content =  postBean.getContent();
                        int post_id = postBean.getPost_id();
                        int user_id = postBean.getUser_id();
                        long time = postBean.getTime();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = new Date(time*1000);
                        String str_time = simpleDateFormat.format(date);
                        out.println("<div class=\"list-group-item item_article\">\n" +
                                "                    <div class=\"row\">\n" +
                                "                        <div class=\"div_center col-xs-9\">\n" +
                                "                            <p class=\"list-group-item-text div_article_content\">\n" +
                                content+"\n" +
                                "                            </p>\n" +
                                "                            <p class=\"list-group-item-text div_article\">\n" +
                                str_time+"\n" +
                                "                            </p>\n" +
                                "                            <a href=\"#\">收藏</a>\n" +
                                "                            &nbsp;\n" +
                                "                            <a href=\"#\" data-toggle=\"modal\" data-target=\"#myModal\">点击联系我</a>\n" +
                                "                        </div>\n" +
                                "                        <!-- 右侧图片，信息 -->\n" +
                                "                        <div class=\"col-xs-3 div_right_info\">\n" +
                                "                            <img class=\"iv_article img-rounded\" src=\"/src/img/user.ico\" alt=\"\">\n" +
                                "                        </div>\n" +
                                "                    </div>\n" +
                                "                </div>");
                    }
                %>
                <!-- 模态框 -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">用户详细资料</h4>
                            </div>
                            <div class="modal-body">
                                <!-- 详细信息 -->
                                <div class="row">
                                    <div class="col-md-4">姓名</div>
                                    <div class="col-md-8">蒋斌</div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-md-4">性别</div>
                                    <div class="col-md-8">女</div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-md-4">手机号</div>
                                    <div class="col-md-8">110110110110</div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-md-4">邮箱</div>
                                    <div class="col-md-8">jbjbjbjb@qq.com</div>
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
                    <li>
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <%
                        int page_index = 1;
                        int tmp = postListBean.getPosts_total() % 10;
                        System.out.println(postListBean.getPosts_total());
                        int total_posts = postListBean.getPosts_total();
                        int total_page = tmp > 0 ? ((total_posts - tmp)/10)+1:(total_posts - tmp)/10;
                        while (page_index <= total_page){
                            out.println("<li><a href=\"/query-square?page="+page_index+"\">"+(page_index++)+"</a></li>");
                        }
                    %>
                    <li>
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <!-- 右侧 -->
        <div class="col-xs-3 div_record">
            <!-- 用户信息 -->
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
            <textarea class="form-control" rows="3" name="text" placeholder="内容:今晚吃什么"></textarea>
            <br>
            选择图片：
            <div class="div_save">
                <input type="file" accept="image/*"/>
            </div>
            <br>
            <div class="div_save">
                <button type="button" class="btn btn-primary btn_save_record">发布</button>
            </div>
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
</body>
</html>