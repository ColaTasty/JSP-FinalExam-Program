<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="jsp_header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我的</title>
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
        时间：2019/6/5
        描述：个人信息界面
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
                        我的信息
                    </h4>
                </a>
                <!-- 主体 -->
                <div>
                    <ul id="myTab" class="nav nav-tabs">
                        <li class="active">
                            <a href="#message" data-toggle="tab">
                                个人信息
                            </a>
                        </li>
                        <li><a href="#post" data-toggle="tab">我的帖子</a></li>
                        <!--                        <li class="dropdown">-->
                        <!--                            <a href="#" id="myTabDrop1" class="dropdown-toggle"-->
                        <!--                               data-toggle="dropdown">Java-->
                        <!--                                <b class="caret"></b>-->
                        <!--                            </a>-->
                        <!--                            <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">-->
                        <!--                                <li><a href="#jmeter" tabindex="-1" data-toggle="tab">jmeter</a></li>-->
                        <!--                                <li><a href="#ejb" tabindex="-1" data-toggle="tab">ejb</a></li>-->
                        <!--                            </ul>-->
                        <!--                        </li>-->
                    </ul>
                </div>
                <div id="myTabContent" class="tab-content">
                    <!-- 个人信息 -->
                    <div id="message" class="tab-pane fade in active">
                        <br>
                        <div class="container">
                            <div class="row clearfix">
                                <div class="col-md-8 column">
                                    <form class="form-horizontal" id="alter-information" action="/alter-information"
                                          method="post" role="form">
                                        <div class="form-group">
                                            <label for="inputEmail3" class="col-sm-2 control-label">账号</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="account" name="account"
                                                       readonly="readonly"
                                                       value="<jsp:getProperty name="userBean" property="account"/>"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputEmail3" class="col-sm-2 control-label">昵称</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="user_name" name="user_name"
                                                       value="<jsp:getProperty name="userBean" property="user_name"/>"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputPassword3" class="col-sm-2 control-label">邮箱</label>
                                            <div class="col-sm-10">
                                                <%
                                                    if (userBean.getEmail() == null || userBean.getEmail().length() <= 0) {
                                                        out.print("<input type=\"email\" class=\"form-control\" name=\"email\" id=\"email\" value=\"\" placeholder=\"你还没有填写邮箱\"/>");
                                                    } else {
                                                        out.print("<input type=\"email\" class=\"form-control\" name=\"email\" id=\"email\" value=\"" + userBean.getEmail() + "\" placeholder=\"你还没有填写邮箱\"/>");
                                                    }
                                                %>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputPassword3" class="col-sm-2 control-label">手机号</label>
                                            <div class="col-sm-10">
                                                <%
                                                    if (userBean.getMobile() == null || userBean.getMobile().length() <= 0) {
                                                        out.print("<input type=\"text\" class=\"form-control\" name=\"mobile\" id=\"mobile\" maxlength=\"11\" placeholder=\"你还没有填写手机号\"/>");
                                                    } else {
                                                        out.print("<input type=\"text\" class=\"form-control\" name=\"mobile\" id=\"mobile\" maxlength=\"11\" value=\"" + userBean.getMobile() + "\" placeholder=\"你还没有写手机号\"/>");
                                                    }
                                                %>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputPassword3" class="col-sm-2 control-label">性别</label>
                                            <div class="col-sm-10">
                                                <select class="form-control" id="gender" name="gender">
                                                    <option value="1" <%=userBean.getGender() == 1 ? "selected=\"\"" : ""%>>
                                                        男
                                                    </option>
                                                    <option value="0" <%=userBean.getGender() == 0 ? "selected=\"\"" : ""%>>
                                                        女
                                                    </option>
                                                </select>
                                                <%--                                                <input type="text" class="form-control" id="gender"--%>
                                                <%--                                                       value="<%= userBean.getGender()==1?"男":"女"%>"/>--%>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputPassword3" class="col-sm-2 control-label">状态</label>
                                            <div class="col-sm-10">
                                                <%
                                                    switch (userBean.getStatus()) {
                                                        case 0:
                                                            out.print("<input type=\"text\" class=\"form-control\" name=\"status\" id=\"status\" value=\"拉黑\" readonly=\"readonly\"/>");
                                                            break;
                                                        case 1:
                                                            out.print("<input type=\"text\" class=\"form-control\" name=\"status\" id=\"status\" value=\"正常\" readonly=\"readonly\"/>");
                                                            break;
                                                        case 2:
                                                            out.print("<input type=\"text\" class=\"form-control\" name=\"status\" id=\"status\" value=\"封禁\" readonly=\"readonly\"/>");
                                                            break;
                                                        default:
                                                            out.print("<script>alert(\"登录异常，返回登录\");windows.location.href=\"/index.jsp\"</script>");
                                                            break;
                                                    }
                                                %>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-2 col-sm-10">
                                                <button id="btn-submit" type="button" class="btn btn-default">修改
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="col-md-4 column">
                                    <img alt="140x140" src="src/img/user.ico" class="img-rounded"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 我的帖子 -->
                    <div id="post" class="tab-pane fade">
                        <!-- 内容 -->
                        <!-- start -->
                        <div class="list-group-item item_article">
                            <div class="row">
                                <div class="div_center col-xs-9">
                                    <p class="list-group-item-text div_article_content">
                                        今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么
                                    </p>
                                    <p class="list-group-item-text div_article">
                                        2019/06/05
                                    </p>
                                    <button type="button" class="btn btn-danger btn-default" style="float: right;">删除
                                    </button>
                                </div>
                                <!-- 右侧图片，信息 -->
                                <div class="col-xs-3 div_right_info">
                                    <img class="iv_article img-rounded" src="/src/img/user.ico" alt="">
                                </div>
                            </div>
                        </div>
                        <div class="list-group-item item_article">
                            <div class="row">
                                <div class="div_center col-xs-9">
                                    <p class="list-group-item-text div_article_content">
                                        今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么
                                    </p>
                                    <p class="list-group-item-text div_article">
                                        2019/06/05
                                    </p>
                                    <button type="button" class="btn btn-danger btn-default" style="float: right;">删除
                                    </button>
                                </div>
                                <!-- 右侧图片，信息 -->
                                <div class="col-xs-3 div_right_info">
                                    <img class="iv_article img-rounded" src="/src/img/user.ico" alt="">
                                </div>
                            </div>
                        </div>
                        <div class="list-group-item item_article">
                            <div class="row">
                                <div class="div_center col-xs-9">
                                    <p class="list-group-item-text div_article_content">
                                        今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么
                                    </p>
                                    <p class="list-group-item-text div_article">
                                        2019/06/05
                                    </p>
                                    <button type="button" class="btn btn-danger btn-default" style="float: right;">删除
                                    </button>
                                </div>
                                <!-- 右侧图片，信息 -->
                                <div class="col-xs-3 div_right_info">
                                    <img class="iv_article img-rounded" src="/src/img/user.ico" alt="">
                                </div>
                            </div>
                        </div>
                        <!-- end -->
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $("#myTab a").click(function (e) {
            e.preventDefault();
            $(this).tab('show')
        });
    });
    $("#btn-submit").click(function () {
        $("#alter-information").submit();
    });
</script>
</body>
</html>