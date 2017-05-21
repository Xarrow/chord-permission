<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wb-zj268791
  Date: 2017/4/5
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title>平台</title>
    <c:import url="head.jsp"/>
    <style>
        li.active li:hover {
            background-color: #FFA726;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <!-- Navigation -->
    <nav class="top1 navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><i class="fa fa-envira"></i>平台</a>
        </div>

        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    <i class="fa fa-comments-o"></i><span class="badge">4</span></a>
                <ul class="dropdown-menu">
                    <!-- message-->
                    <li class="dropdown-menu-header">
                        <strong>Messages</strong>
                        <div class="progress thin">
                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40"
                                 aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                <span class="sr-only">40% Complete (success)</span>
                            </div>
                        </div>
                    </li>

                    <!-- avatar -->
                    <li class="avatar">
                        <a href="#">
                            <img src="images/1.png" alt="">
                            <div>New message</div>
                            <small>1 minute ago</small>
                            <span class="label label-info">NEW</span>
                        </a>
                    </li>

                    <!-- viewAllMsg -->
                    <li class="dropdown-menu-footer text-center">
                        <a href="#">View all messages</a>
                    </li>
                </ul>
            </li>

            <li class="dropdown">
                <a href="#" class="dropdown-toggle avatar" data-toggle="dropdown" aria-expanded="false">
                    <i class="fa fa-user fa-fw"></i>
                </a>
                <ul class="dropdown-menu">
                    <li class="dropdown-menu-header text-center">
                        <strong>设置</strong>
                    </li>
                    <li class="m_2">
                        <a href="/logout">
                            <i class="fa fa-lock">
                            </i> 退出
                        </a>
                    </li>
                </ul>
            </li>
        </ul>

        <form class="navbar-form navbar-right">
            <input type="text" class="form-control" value="Search..." onfocus="this.value = '';"
                   onblur="if (this.value == '') {this.value = 'Search...';}">
        </form>

        <!-- left slide -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li>
                        <a href="/index"><i class="fa fa-dashboard fa-fw nav_icon"></i>首页</a>
                    </li>
                    <c:if test="${null!=userDetail.permissionList&&userDetail.permissionList.size()>0}">
                        <c:forEach var="item" items='${userDetail.permissionList}'>
                            <li>
                                <!-- 一级菜单分割 url 和 ico -->
                                <a href="<c:out value="${item.url.split(',')[0]}" />">
                                    <i class="fa <c:out value="${item.url.split(',')[1]}" /> nav_icon"></i>
                                    <c:out value="${item.name}"/>
                                    <span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level collapse">
                                    <!-- 判断二级菜单 -->
                                    <c:if test="${item.childNodes.size()>0}">
                                        <c:forEach var="subItem" items="${item.childNodes}">
                                            <li>
                                                <a href="javascript:void (0)"
                                                   onclick="iframeChange(this)"
                                                   title="${subItem.url}">
                                                        ${subItem.name}
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </c:if>
                                </ul>
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>

    <!-- main content -->
    <div id="page-wrapper">
        <!-- context -->
        <div class="xs" id="main-content" style="padding: 2em 1em;font-family: 'Roboto', sans-serif;">
            <%--<h3 class="tips">内容</h3>--%>
            <iframe class="main-iframe" style="border: none;height: 84%;width: 100%;"
                    src="https://www.alitrip.com/">
            </iframe>
        </div>
    </div>

    <%--<c:import url="footer.jsp"/>--%>
</div>

<script>
    /**
     * 点击修改iframe 链接
     * @param s
     */
    let iframeChange = function (e) {
        debugger;
        $("li[style*='background']").prop("style", false);
        $(e.parentNode).css({"backgroundColor": "#FF3D00"});
        if (e.title != 'undefined' && e.title != '') {
            $("#main-content").empty();
            //iframe
            if (e.title.indexOf(":") > -1) {
                let iframe = document.createElement("iframe");
                iframe.src = e.title;
                iframe.style = "border: none;height: 84%;width: 100%;";
                document.getElementById("main-content").appendChild(iframe);
            }
            //ajax
            else {
                let contentDiv = document.createElement("div");
                contentDiv.id = "contentDiv";
                contentDiv.style = "border: none;height: 84%;width: 100%;";
                document.getElementById("main-content").appendChild(contentDiv);
                $("#contentDiv").load(e.title, function (data) {
                    console.log("i am ok");
                });
            }
        }
//        $(".main-iframe").attr("src", e instanceof Object && e.title != 'undefined' ? e.title : '');

    };

    $(function () {
        document.oncontextmenu = function () {
            return false;
        }
//        let checkSessionTime = function () {
//            let startTime = new Date();
//            setInterval(function () {
//                $.post("/api/checkSession", function (data) {
//                    if (data.isExpired) {
//                        let endTime = new Date();
//                        console.log("session time is " + Math.abs(endTime - startTime) / 3600);
//                        return 0;
//                    }
//                })
//            }, 1000);
//        }(document);

        $("a[href]").on("click", function () {
            $.post('/api/checkSession', function (data) {
                if (data.isExpired) {
                    $("iframe").off();
                    $.confirm({
                        title: '提示',
                        icon: "fa fa-warning",
                        content: 'Session 已经过期,请重新登录',
                        type: 'orange',
                        typeAnimated: true,
                        buttons: {
                            tryAgain: {
                                text: '请重新登录!',
                                btnClass: 'btn-red',
                                action: function () {
                                    window.location.href = "/logout";
                                }
                            },
                        }
                    });
                    $(document).off();
                }
            })
        });
    })
</script>
</body>
</html>