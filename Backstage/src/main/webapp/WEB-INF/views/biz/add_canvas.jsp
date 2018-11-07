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
        <meta charset="UTF-8">
        <title>新建</title>
        <link rel="stylesheet" href="../../../lib/bootstrap-3.3.7-dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../../lib/css/add.css">
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">
                        慕课网油画管理
                    </a>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="jumbotron">
                <h1>Hello,admin!</h1>
                <p>请小心的新增油画记录，要是建了一个错误的就不好了。。。</p>
            </div>
            <div class="page-header">
                <h3><small>新建</small></h3>
            </div>
            <form class="form-horizontal" action="${pageContext.request.contextPath}/canvas/add.do" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">名称 ：</label>
                    <div class="col-sm-8">
                        <input name="name" class="form-control" id="name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="category" class="col-sm-2 control-label">分类 ：</label>
                    <div class="col-sm-8">
                        <select id="category" name="category" class="form-control" style="width: auto">
                            <c:forEach var="category" items="${categories}" varStatus="status">
                                <option id="${status.index}" value="${category.name}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="price" class="col-sm-2 control-label">价格 ：</label>
                    <div class="col-sm-8">
                        <input name="price" type="number" class="form-control" id="price">
                    </div>
                </div>
                <div class="form-group">
                    <label for="smallImg" class="col-sm-2 control-label">图片 ：</label>
                    <div class="col-sm-8">
                        <input id="smallImg" name="smallImg" class="file-loading"
                               type="file" multiple accept=".jpg,.jpeg,.png" data-min-file-count="1"
                               data-show-preview="true">
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="col-sm-2 control-label">描述 ：</label>
                    <div class="col-sm-8">
                        <input name="description" type="text" class="form-control" id="description">
                    </div>
                </div>
                <div class="form-group">
                    <label for="details" class="col-sm-2 control-label">详细介绍 ：</label>
                    <div class="col-sm-8">
                        <textarea id="details" class="form-control" name="details" rows="10"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </form>
        </div>
        <footer class="text-center" >
            copy@imooc
        </footer>
    </body>
</html>
