<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<!--
        作者：吴迈星、杜健聪、蒋斌
        时间：2019/6/4
        描述：头部
    -->
<!-- 图片轮播 -->
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        <li data-target="#carousel-example-generic" data-slide-to="3"></li>
        <li data-target="#carousel-example-generic" data-slide-to="4"></li>
        <li data-target="#carousel-example-generic" data-slide-to="5"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img src="src/img/ic_p4.jpg">
        </div>
        <div class="item">
            <img src="src/img/ic_p5.jpg">
        </div>
        <div class="item">
            <img src="src/img/ic_p6.jpg">
        </div>
        <div class="item">
            <img src="src/img/ic_p7.jpg">
        </div>
        <div class="item">
            <img src="src/img/ic_p8.jpg">
        </div>
        <div class="item">
            <img src="src/img/ic_p9.jpg">
        </div>
    </div>
    <!-- 控制按钮 -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
    <!-- 图片轮播结束 -->
    <!-- 功能模块 -->
    <div class="row div_function">
        <div class="col-xs-4">
            <div class="panel panel-default" id="pFunc1">
                <a href="/query-square?page=1" style="text-decoration: none;color: black;">
                    <div class="panel-body">
                        <strong>广场</strong>
                        <img class="iv_function" src="src/img/ground.svg" alt="home">
                    </div>
                </a>
            </div>

        </div>
        <div class="col-xs-4">
            <div class="panel panel-default" id="pFunc2">
                <a href="home_friends.jsp" style="text-decoration: none;color: black;">
                    <div class="panel-body">
                        <strong>交友</strong>
                        <img class="iv_function" src="src/img/community.svg" alt="">
                    </div>
                </a>
            </div>
        </div>
        <div class="col-xs-4">
            <div class="panel panel-default" id="pFunc3">
                <a href="home_user.jsp" style="text-decoration: none;color: black;">
                    <div class="panel-body">
                        <strong>个人信息</strong>
                        <img class="iv_function" src="src/img/about.svg" alt="">
                    </div>
                </a>
            </div>
        </div>
    </div>
</div>