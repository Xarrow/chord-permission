<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <script src="/main/public/js/libs/mdui.js"></script>
    <script src="/main/public/js/libs/jquery-3.1.0.min.js"></script>
    <link rel="stylesheet" href="/main/public/css/mdui.css"/>
    <title>WEIBO VM</title>
    <style>
        body {
            background-image: url(http://cdn1.mikecrm.com/images/formTemplate/101_bg.png);
            background-position: center center;
            background-repeat: repeat;
            overflow-y: hidden;
            background-color: rgb(245, 245, 245);
        }
    </style>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar  mdui-theme-primary-indigo mdui-theme-accent-pink mdui-loaded mdui-locked">
<header class="mdui-appbar mdui-appbar-fixed">
    <div class="mdui-toolbar mdui-color-theme">
        <span class="mdui-btn mdui-btn-icon mdui-ripple mdui-ripple-white" mdui-drawer="{target: '#main-drawer'}">
            <i class="mdui-icon material-icons">&#xE8FE;</i></span>
        <a href="./" class="mdui-typo-headline mdui-hidden-xs">weibo</a>
        <a href="" class="mdui-typo-title">微博</a>
        <div class="mdui-toolbar-spacer"></div>

        <a href="https://github.com/zdhxiong/mdui" target="_blank"
           class="mdui-btn mdui-btn-icon mdui-ripple mdui-ripple-white" mdui-tooltip="{content: 'Github'}">
            <svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg"
                 x="0px" y="0px" viewBox="0 0 36 36"
                 enable-background="new 0 0 36 36"
                 xml:space="preserve"
                 class="mdui-icon"
                 style="width: 24px;height:24px;">
        <path fill-rule="evenodd" clip-rule="evenodd" fill="#ffffff" d="M18,1.4C9,1.4,1.7,8.7,1.7,17.7c0,7.2,4.7,13.3,11.1,15.5
	c0.8,0.1,1.1-0.4,1.1-0.8c0-0.4,0-1.4,0-2.8c-4.5,1-5.5-2.2-5.5-2.2c-0.7-1.9-1.8-2.4-1.8-2.4c-1.5-1,0.1-1,0.1-1
	c1.6,0.1,2.5,1.7,2.5,1.7c1.5,2.5,3.8,1.8,4.7,1.4c0.1-1.1,0.6-1.8,1-2.2c-3.6-0.4-7.4-1.8-7.4-8.1c0-1.8,0.6-3.2,1.7-4.4
	c-0.2-0.4-0.7-2.1,0.2-4.3c0,0,1.4-0.4,4.5,1.7c1.3-0.4,2.7-0.5,4.1-0.5c1.4,0,2.8,0.2,4.1,0.5c3.1-2.1,4.5-1.7,4.5-1.7
	c0.9,2.2,0.3,3.9,0.2,4.3c1,1.1,1.7,2.6,1.7,4.4c0,6.3-3.8,7.6-7.4,8c0.6,0.5,1.1,1.5,1.1,3c0,2.2,0,3.9,0,4.5
	c0,0.4,0.3,0.9,1.1,0.8c6.5-2.2,11.1-8.3,11.1-15.5C34.3,8.7,27,1.4,18,1.4z">

        </path>
      </svg>
        </a>
    </div>
</header>


<div class="mdui-drawer mdui-drawer-open" id="main-drawer">
    <div class="mdui-list" mdui-collapse="{accordion: true}" style="margin-bottom: 76px;">
        <c:if test="${null!=userDetail.permissionList&&userDetail.permissionList.size()>0}">
            <c:forEach var="item" items='${userDetail.permissionList}'>
                <div class="mdui-collapse-item ">
                    <div class="mdui-collapse-item-header mdui-list-item mdui-ripple">
                        <i class="mdui-list-item-icon mdui-icon material-icons <c:out value="${item.url.split(',')[3]}"/> ">
                            <c:out value="${item.url.split(',')[2]}"/></i>
                        <div class="mdui-list-item-content">
                            <c:out value="${item.name}"/>
                        </div>
                        <i class="mdui-collapse-item-arrow mdui-icon material-icons">&#xE313;</i>
                    </div>
                    <c:if test="${item.childNodes.size()>0}">
                        <c:forEach var="subItem" items="${item.childNodes}">
                            <div class="mdui-collapse-item-body mdui-list">
                                <a href="javascript:void (0)" onclick="load_data(this)" title="${subItem.url}"
                                   class="mdui-list-item mdui-ripple ">${subItem.name}</a>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>
<div class="mdui-container doc-container doc-no-cover">
    <h1 class="doc-title mdui-text-color-theme">颜色与主题</h1>
    <div class="doc-chapter">
        <div class="mdui-typo">
            <pre id="code"></pre>

        </div>
    </div>
</div>

<script>
    var load_data = function (e) {
        $.get("https://api.weibo.com/oauth2/authorize?client_id=2625619306&redirect_uri=http://127.0.0.1:8080/oauth/weibo/invoke&response_type=code", function (data) {


        });
        $("#code").load(e.title, function (data) {
            console.log("i am ok");
        });
    };

    $(function () {
        var instance = new mdui.Drawer("#drawer");
        document.getElementById('toggle').addEventListener('click', function () {
            instance.toggle();
        });


    });
</script>
</body>
</html>
