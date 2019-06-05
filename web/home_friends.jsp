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


            </div>
        </div>
    </div>
</div>
</body>
</html>