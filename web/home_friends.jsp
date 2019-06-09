<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@include file="jsp_header.jsp" %>
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
        作者：杜健聪
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
                <div class="col-xs-6 col-md-3">
                    <a href="#" class="thumbnail">
                        <img alt="100%x180" src="/src/img/ic_p8.jpg"
                             style="height: 180px; width: 100%; display: block;">
                    </a>
                    <div class="caption">
                        <h3>B哥</h3>
                        <p>城院第一帅</p>
                        <p>
                            <a href="#" class="btn btn-primary" role="button" data-toggle="modal" data-target="#myModal">
                                详细资料
                            </a>
                        </p>
                    </div>
                </div>
                <div class="col-xs-6 col-md-3">
                    <a href="#" class="thumbnail">
                        <img alt="100%x180" src="/src/img/ic_p8.jpg"
                             style="height: 180px; width: 100%; display: block;">
                    </a>
                    <div class="caption">
                        <h3>B哥</h3>
                        <p>城院第一帅</p>
                        <p>
                            <a href="#" class="btn btn-primary" role="button" data-toggle="modal" data-target="#myModal">
                                详细资料
                            </a>
                        </p>
                    </div>
                </div>
                <div class="col-xs-6 col-md-3">
                    <a href="#" class="thumbnail">
                        <img alt="100%x180" src="/src/img/ic_p8.jpg"
                             style="height: 180px; width: 100%; display: block;">
                    </a>
                    <div class="caption">
                        <h3>B哥</h3>
                        <p>城院第一帅</p>
                        <p>
                            <a href="#" class="btn btn-primary" role="button" data-toggle="modal" data-target="#myModal">
                                详细资料
                            </a>
                        </p>
                    </div>
                </div>
                <!-- End -->
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
        </div>
    </div>
</div>
</body>
</html>