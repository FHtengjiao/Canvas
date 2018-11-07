<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>首页</title>
    <link rel="stylesheet" href="<%=basePath%>/lib/css/common.css">
    <link rel="stylesheet" href="<%=basePath%>/lib/css/index.css">
    <link rel="stylesheet" href="<%=basePath%>/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script src="<%=basePath%>/lib/JQuery/jquery-1.12.4.js"></script>
    <script>
        $().ready(function () {
            let page = "${page}";
            let category = "${category}";
            console.log(category);
            console.log(page);
            $("li[id="+page+"]").empty();
            $("li[id="+page+"]").append("<span class='first-page'>"+page+"</span>");
            if (category != "") {
                $("li[id="+category+"]").addClass("active");
            }else
                $("li[id=all]").addClass("active");
        });
    </script>
</head>
<body>
    <div class="header">
        <div class="logo f1">
            <img src="../../../lib/image/logo.png">
        </div>
        <div class="auth fr">
            <ul>
                <li><a href="#">登录</a></li>
                <li><a href="#">注册</a></li>
            </ul>
        </div>
    </div>
    <div class="content">
        <div class="banner">
            <img class="banner-img" src="../../../lib/image/welcome.png" width="732px" height="372" alt="图片描述">
        </div>
            <ul class="nav nav-pills pull-right">
                <li id = "all"><a href="${pageContext.request.contextPath}/canvas/list.do">全部</a></li>
                <c:forEach var="category" items="${categories}">
                    <li id="${category.name}"><a href="${pageContext.request.contextPath}/canvas/list.do?&category=${category.name}">${category.name}</a></li>
                </c:forEach>
            </ul>
        <div class="img-content">
            <ul>
                <c:forEach var="canvas" items="${canvases}">
                <li>
                    <img class="img-li-fix" src="${pageContext.request.contextPath}/canvas/getImg.do?id=${canvas.id}" alt="${canvas.name}">
                    <div class="info">
                        <h3 class="img_title">${canvas.name}</h3>
                        <p>
                            ${canvas.description}
                        </p>
                        <div class="btn">
                            <a href="${pageContext.request.contextPath}/canvas/detail.do?id=${canvas.id}" class="edit">详情</a>
                        </div>
                    </div>
                </li>
                </c:forEach>
            </ul>
        </div>
        <div class="page-nav">
            <ul>
                <li><a href="#">首页</a></li>
                <li><a href="#">上一页</a></li>
                <c:forEach begin="1" end="${totalPage}" var="i">
                    <li id="${i}"><a href="${pageContext.request.contextPath}/canvas/list.do?page=${i}&category=${category}">${i}</a></li>
                </c:forEach>
                <li><a href="#">下一页</a></li>
                <li><a href="#">尾页</a></li>
            </ul>
        </div>
    </div>

    <div class="footer">
        <p><span>M-GALLARY</span>©2017 POWERED BY IMOOC.INC</p>
    </div>
</body>
</html>