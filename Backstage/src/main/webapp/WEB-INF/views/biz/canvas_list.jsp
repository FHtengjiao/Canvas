<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>油画列表</title>
        <link rel="stylesheet" href="../../../lib/css/index.css">
        <link rel="stylesheet" href="../../../lib/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    </head>

    <body>
        <header>
            <div class="container">
                <c:forEach var="category" items="${categories}">
                    <nav>
                        <a href="${pageContext.request.contextPath}/canvas/list.do?category=${category.name}">${category.name}</a>
                    </nav>
                </c:forEach>
                <nav>
                    <a href="${pageContext.request.contextPath}/canvas/list.do">全部</a>
                </nav>
                <nav>
                    <a href="${pageContext.request.contextPath}/canvasCategory/list.do">分类</a>
                </nav>
                <nav>
                    <a href="#">登录</a>
                    <a href="#" onclick="alert('功能暂未开放');">注册</a>
                </nav>
            </div>
        </header>
        <section class="banner">
            <div class="container">
                <div>
                    <h1>油画</h1>
                    <p>油画列表</p>
                </div>
            </div>
        </section>
        <section class="main">
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-body">
                    </div>
                    <table class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>名称</th>
                                <th>分类</th>
                                <th>价格</th>
                                <th>创建时间</th>
                                <th>最后修改时间</th>
                                <th>描述</th>
                                <th>编辑</th>
                                <th>删除</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${canvases}" var="canvas">
                            <tr>
                                <td>${canvas.name}</td>
                                <td>${canvas.category}</td>
                                <td>￥<fmt:formatNumber type="currency" pattern="#,#00.00#" value="${canvas.price}"/></td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${canvas.createTime}"/></td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${canvas.updateTime}"/></td>
                                <td>${canvas.description}</td>
                                <td><a href="${pageContext.request.contextPath}/canvas/editPrompt.do?id=${canvas.id}">编辑</a></td>
                                <td><a href="${pageContext.request.contextPath}/canvas/delete.do?id=${canvas.id}">删除</a>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </section>
        <section class="page">
            <div class="container">
                <div id="fatie">
                    <a href="${pageContext.request.contextPath}/canvas/addPrompt.do" class="btn btn-primary" role="button">新&nbsp;建</a>
                </div>
            </div>
        </section>
        <footer>
            copy@慕课网
        </footer>
    </body>
</html>