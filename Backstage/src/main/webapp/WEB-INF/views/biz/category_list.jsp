<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>油画分类列表</title>
        <link rel="stylesheet" href="../../../lib/css/index.css">
        <link rel="stylesheet" href="../../../lib/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    </head>

    <body>
        <header>
            <div class="container">
                <c:forEach var="category" items="${categories}">
                    <nav>
                        <a href="javascript:void(0)">${category.name}</a>
                    </nav>
                </c:forEach>
                <nav>
                    <a href="${pageContext.request.contextPath}/canvas/list.do">油画列表</a>
                </nav>
            </div>
        </header>
        <section class="banner">
            <div class="container">
                <div>
                    <h1>油画分类</h1>
                    <p>油画分类列表</p>
                </div>
            </div>
        </section>
        <section class="main">
            <div class="container">
                <table class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>名称</th>
                            <th>创建时间</th>
                            <th>最后修改时间</th>
                            <th>描述</th>
                            <th>编辑</th>
                            <th>删除</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${categories}" var="category">
                        <tr>
                            <td>${category.name}</td>
                            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${category.createTime}"/></td>
                            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${category.updateTime}"/></td>
                            <td>${category.description}</td>
                            <td><a href="${pageContext.request.contextPath}/canvasCategory/editPrompt.do?id=${category.id}">编辑</a></td>
                            <td><a href="${pageContext.request.contextPath}/canvasCategory/delete.do?id=${category.id}">删除</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
        <section class="page">
            <div class="container">
                <div id="fatie">
                    <a href="${pageContext.request.contextPath}/canvasCategory/addPrompt.do" class="btn btn-primary" role="button">新建</a>
                </div>


                <!-- <div id="pagefy">
                    <ul>
                        <form id="messageForm" action="#" method="post">
                            <input type="hidden" id="page" name="page" value="3">
                            <input type="hidden" id="last" name="last" value="1">
                            <li><a href="#" onclick="submitMessageForm('first')">首页</a></li>
                            <li><a href="#" onclick="submitMessageForm('pre')">上一页</a></li>
                            <li><a href="#">当前第1页</a></li>
                            <li><a href="#" onclick="submitMessageForm('next')">下一页</a></li>
                            <li><a href="#" onclick="submitMessageForm('last')">尾页</a></li>
                        </form>
                    </ul>
                </div> -->
            </div>
        </section>
        <footer>
            copy@慕课网
        </footer>
    </body>
</html>