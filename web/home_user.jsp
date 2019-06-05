<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
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
                                    <form class="form-horizontal" role="form">
                                        <div class="form-group">
                                            <label for="inputEmail3" class="col-sm-2 control-label">账号</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="username" readonly="readonly" value="123456"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputEmail3" class="col-sm-2 control-label">昵称</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="nickname" value="mike"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputPassword3" class="col-sm-2 control-label">邮箱</label>
                                            <div class="col-sm-10">
                                                <input type="email" class="form-control" id="email" value="8412@qq.com"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputPassword3" class="col-sm-2 control-label">手机号</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="phone" value="123456"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputPassword3" class="col-sm-2 control-label">性别</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="gender" value="男"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputPassword3" class="col-sm-2 control-label">状态</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="status" value="正常"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-2 col-sm-10">
                                                <button type="submit" class="btn btn-default">修改</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="col-md-4 column">
                                    <img alt="140x140" src="src/img/user.ico" class="img-rounded" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 我的帖子 -->
                    <div id="post" class="tab-pane fade">
                        <!-- 内容 -->


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
</script>
</body>
</html>