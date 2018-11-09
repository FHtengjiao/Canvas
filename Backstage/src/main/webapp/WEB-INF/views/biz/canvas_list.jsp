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
    <script src="../../../lib/JQuery/jquery-1.12.4.min.js"></script>
    <script src="../../../lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script>
        $().ready(function (){
            // 控制左上角页面分类的样式
            let category = "${category}";
            if (category === "") {
                $("li[id=全部]").addClass("active");
                $("#selectedClass").html("全部<span class=\"caret\"></span>");
            } else {
                $("li[id=" + category + "]").addClass("active");
                $("#selectedClass").html(category+"<span class=\"caret\"></span>");
            }

            //控制分页的样式
            let page = "${page}";
            let totalPage = "${totalPage}";
            $("li[id=page_"+page+"]").addClass("active");
            if (page === "1") {
                $("#Previous").addClass("disabled");
                $("#Previous").empty().append("<span aria-hidden=\"true\">&laquo;</span>");
            }
            if (page === totalPage) {
                $("#Next").addClass("disabled");
                $("#Next").empty().append("<span aria-hidden=\"true\">&raquo;</span>");
            }
        });
    </script>
</head>
<body>
    <div class="navbar navbar-default" role="navigation">
        <div class="navbar-header pull-right">
        　   <span class="navbar-brand">你好，${username}</span>
        </div>
        <ul class="nav navbar-nav pull-right">
            <li><a href="${pageContext.request.contextPath}/canvasCategory/list.do">油画分类</a></li>
            <li class="dropdown">
                <a id="selectedClass" href="#" class="dropdown-toggle" data-toggle="dropdown">
                    全部<span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li id="全部"><a href="${pageContext.request.contextPath}/canvas/list.do">全部</a></li>
                    <c:forEach var="category" items="${categories}">
                        <li id="${category.name}"><a href="${pageContext.request.contextPath}/canvas/list.do?category=${category.name}">${category.name}</a></li>
                    </c:forEach>
                </ul>
            </li>
        </ul>
    </div>
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
            <div class="row">
                <div class="panel panel-default">
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
            <div class="row">
                <div>
                    <a href="${pageContext.request.contextPath}/canvas/addPrompt.do" class="btn btn-primary" role="button">新&nbsp;建</a>
                </div>
            </div>
        </div>
    </section>
    <section class="page">
        <div class="container">
            <div class="row text-center">
                <nav aria-label="Page navigation">
                    <ul class="pagination pagination-lg">
                        <li id="Previous">
                            <a href="${pageContext.request.contextPath}/canvas/list.do?page=${page-1}&category=${category}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <c:forEach var="i" begin="1" end="${totalPage}">
                            <li id="page_${i}"><a href="${pageContext.request.contextPath}/canvas/list.do?page=${i}&category=${category}">${i}</a></li>
                        </c:forEach>
                        <li id="Next">
                            <a href="${pageContext.request.contextPath}/canvas/list.do?page=${page+1}&category=${category}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </section>
    <footer>
        copy@慕课网
    </footer>
</body>
</html>