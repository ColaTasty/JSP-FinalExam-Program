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
        作者：吴迈星、杜健聪、蒋斌
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
                        <li>
                            <a href="#post" data-toggle="tab">
                                我的帖子
                            </a>
                        </li>
                        <li>
                            <a href="#collection" data-toggle="tab">
                                我的收藏
                            </a>
                        </li>
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
                                    <img alt="140x140" src="/src/img/user.ico" class="img-rounded"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 我的帖子 -->
                    <div id="post" class="tab-pane fade">
                        <!-- 内容 -->
                        <!-- start -->
                        <!-- 我的帖子，JS备份 -->
                        <!--<script>
                            $(function () {
                                var ajax_error = function () {
                                    alert("请求失败，请检查网络");
                                };
                                var getTotalPage = function (postTotal) {
                                    var tmp = postTotal % 10;
                                    var pagetotal;
                                    if (tmp > 0){
                                        pagetotal = ((postTotal - tmp)/10) +1;
                                    }else {
                                        pagetotal = postTotal / 10;
                                    }
                                    return pagetotal;
                                };
                                var myPostsPage = 1;
                                var myPostsList = undefined;
                                /**
                                 *
                                 * @param data = {page}
                                 * @param success = Function
                                 */
                                var getMyPostsList = function (data, success) {
                                    $.ajax({
                                        url: "/get-my-posts",
                                        type: "post",
                                        dataTpye: "json",
                                        data: {
                                            user_id:<%= userBean.getUser_id()%>,
                                            page: data.page
                                        },
                                        success: success,
                                        error: ajax_error
                                    });
                                };
                                /**
                                 *
                                 * @param pageTotal = number
                                 * @param page = number
                                 */
                                var setMyPostsNavBar = function (pageTotal, page) {
                                    var nav = $("#my-posts-nav");
                                    nav.empty();
                                    var getInit_ul = function () {
                                        var ul = $("ul");
                                        ul.addClass("pagination");
                                        return ul;
                                    };
                                    var getInit_a = function () {
                                        var a = $("a");
                                        a.attr("href", "javascript:void(0)");
                                        a.attr("aria-label", "Previous");
                                        return a;
                                    };
                                    var getInit_li = function () {
                                        return $("li");
                                    };
                                    var getInit_span = function () {
                                        var span = $("span");
                                        span.attr("aria-hidden", "true");
                                        return span;
                                    };
                                    var ul = getInit_ul();
                                    if (page !== 1 && pageTotal > 1) {
                                        var last_li = getInit_li();
                                        var last_a = getInit_a();
                                        var last_span = getInit_span();
                                        last_span.text("&laquo;");
                                        last_a.append(last_span);
                                        last_a.attr("id", "last-my-posts-page");
                                        last_a.attr("page", page - 1);
                                        last_li.append(last_a);
                                        ul.append(last_li);
                                    }
                                    var pageIdx = 0;
                                    while (pageIdx <= pageTotal) {
                                        var m_li = getInit_li();
                                        var m_a = getInit_a();
                                        var m_span = getInit_span();
                                        m_span.text(pageIdx);
                                        m_a.append(m_span);
                                        m_a.attr("onclick","myPostsPageOnClick(this)");
                                        m_a.attr("page",pageIdx++);
                                        if (pageIdx === page)
                                            m_a.attr("style","background-color:#ccc");
                                        m_li.append(m_a);
                                        ul.append(m_li);
                                    }
                                    if (page < pageTotal) {
                                        var next_li = getInit_li();
                                        var next_a = getInit_a();
                                        var next_span = getInit_span();
                                        next_span.text("&raquo;");
                                        next_a.append(next_span);
                                        next_a.attr("id", "next-my-posts-page");
                                        next_a.attr("page", page - 1);
                                        next_li.append(next_a);
                                        ul.append(next_li);
                                    }
                                    nav.append(ul);
                                };
                                getMyPostsList({page:myPostsPage},function (res) {
                                    var posts = res.posts;
                                    setMyPostsNavBar(getTotalPage(posts.posts_total),posts.page);
                                });
                                $("#post").find(".list-group-item").remove();
                            })
                        </script>-->
                        <!-- 我的帖子，JS备份 end -->
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
                        <!-- 导航条 -->
                        <nav id="my-posts-nav" aria-label="Page navigation" style="text-align: center">
                            <ul class="pagination">
                                <!-- 上一页 -->
                                <li>
                                    <a href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <!-- 页码 -->
                                <li>
                                    <a href="#" style="background-color:#ccc">1</a>
                                </li>
                                <!-- 下一页 -->
                                <li>
                                    <a href="#" aria-label="Previous">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                    <!-- end -->
                    <!-- 我的收藏 -->
                    <div id="collection" class="tab-pane fade">
                        <!-- 内容 -->
                        <div class="list-group-item item_article">
                            <div class="row">
                                <div class="div_center col-xs-9">
                                    <p class="list-group-item-text div_article_content">
                                        今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么今晚吃什么
                                    </p>
                                    <p class="list-group-item-text div_article">
                                        2019/06/05
                                    </p>
                                    <button type="button" class="btn btn-danger btn-default" style="float: right;">
                                        取消收藏
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
                                    <button type="button" class="btn btn-danger btn-default" style="float: right;">
                                        取消收藏
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
                                    <button type="button" class="btn btn-danger btn-default" style="float: right;">
                                        取消收藏
                                    </button>
                                </div>
                                <!-- 右侧图片，信息 -->
                                <div class="col-xs-3 div_right_info">
                                    <img class="iv_article img-rounded" src="/src/img/user.ico" alt="">
                                </div>
                            </div>
                        </div>
                        <!-- 导航条 -->
                        <nav aria-label="Page navigation" style="text-align: center">
                            <ul class="pagination">
                                <!-- 上一页 -->
                                <li>
                                    <a href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <!-- 页码 -->
                                <li>
                                    <a href="#" style="background-color:#ccc">1</a>
                                </li>
                                <!-- 下一页 -->
                                <li>
                                    <a href="#" aria-label="Previous">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
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