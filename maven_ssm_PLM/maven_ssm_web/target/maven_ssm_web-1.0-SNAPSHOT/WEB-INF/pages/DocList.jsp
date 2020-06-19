<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title>首页 - 后台管理系统模板</title>
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/ico">
    <meta name="keywords" content="后台管理系统">
    <meta name="description" content="LightYear是一个基于Bootstrap v3.3.7的后台管理系统的HTML模板。">
    <meta name="author" content="yinqi">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/materialdesignicons.min.css" rel="stylesheet">

    <style>
        .text-status{
            color: red;
        }
    </style>
</head>

<body>
<div class="lyear-layout-web">
    <div class="lyear-layout-container">
        <!--左侧导航-->
        <jsp:include page="/sidebar.jsp"></jsp:include>
        <!--End 左侧导航-->

        <!--头部信息-->
        <jsp:include page="/header.jsp"></jsp:include>
        <!--End 头部信息-->

        <!--页面主要内容-->
        <main class="lyear-layout-content">

            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-toolbar clarifix">
                                <form class="pull-right search-bar" method="get" action="#!" role="form">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <input type="hidden" name="search_field" id="search-field" value="title">
                                            <button class="btn btn-default dropdown-toggle" id="search-btn" data-toggle="dropdown" type="button" aria-haspopup="true" aria-expanded="false">
                                                标题 <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li> <a tabindex="-1" href="javascript:void(0)" data-field="title">标题</a> </li>
                                                <li> <a tabindex="-1" href="javascript:void(0)" data-field="cat_name">栏目</a> </li>
                                            </ul>
                                        </div>
                                        <input type="text" class="form-control" value="" name="keyword" placeholder="请输入名称">
                                    </div>
                                </form>
                                <div class="toolbar-btn-action">
                                    <a class="btn btn-primary m-r-5" href="${pageContext.request.contextPath}/article/page/addDoc"><i class="mdi mdi-plus"></i> 新增</a>
                                    <a class="btn btn-danger" href="javascript:void(0);" onclick="del();"><i class="mdi mdi-window-close"></i> 删除</a>
                                </div>
                            </div>
                            <div class="card-body">

                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th>
                                                <label class="lyear-checkbox checkbox-primary">
                                                    <input type="checkbox" id="check-all" class="check-all"><span></span>
                                                </label>
                                            </th>
                                            <th>编号</th>
                                            <th>名称</th>
                                            <th>借出时间</th>
                                            <th>归还时间</th>
                                            <th>详情</th>
                                            <th>使用者</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${pageInfo.list}" var="articles" varStatus="article" >
                                            <tr>
                                                <td>
                                                    <label class="lyear-checkbox checkbox-primary">
                                                        <input type="checkbox" name="ids" value="${articles.id}"><span></span>
                                                    </label>
                                                </td>
                                                <td>${(pageInfo.pageNum-1) * pageInfo.pageSize + article.count}</td>
                                                <td>${articles.name}</td>
                                                <td>${articles.startDate}</td>
                                                <td>${articles.endDate}</td>
                                                <td>${articles.details}</td>
                                                    <%--<fmt:formatDate value="${articles.details}" pattern="yyyy-MM-dd"/>--%>
                                                <td>${articles.users}</td>
                                                <td><font class="${articles.status eq 0?"text-status":"text-success"}">${articles.status eq 0?"借出":"归还"}</font></td>
                                                <td>
                                                    <div class="btn-group">
                                                        <a class="btn btn-xs btn-default" href="${pageContext.request.contextPath}/article/findArticleById/${articles.id}" title="编辑" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>
                                                        <a class="btn btn-xs btn-default" href="${pageContext.request.contextPath}/article/delArticle/${articles.id}" title="删除" data-toggle="tooltip"><i class="mdi mdi-window-close"></i></a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <ul class="pagination">
                                    <li><a href="${pageContext.request.contextPath}/article/findAllArticle.do?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}">«</a></li>
                                    <%--<li class="active"><span>1</span></li>--%>
                                    <c:forEach begin="1" end="${pageInfo.pages}" var="pageNow" varStatus="now" >
                                        <li class="${pageInfo.pageNum eq pageNow?"active":""}"><a href="${pageContext.request.contextPath}/article/findAllArticle.do?page=${pageNow}&size=${pageInfo.pageSize}">${pageNow}</a></li>
                                    </c:forEach>
                                    <li><a href="${pageContext.request.contextPath}/article/findAllArticle.do?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}">»</a></li>
                                </ul>

                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </main>
        <!--End 页面主要内容-->
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.min.js"></script>
<script>
    $(function(){
        $('.search-bar .dropdown-menu a').click(function() {
            var field = $(this).data('field') || '';
            $('#search-field').val(field);
            $('#search-btn').html($(this).text() + ' <span class="caret"></span>');
        });
    });

    function del() {
        // 获取input标签下type类型为checkbox的所有选中的checked框 
        var option = $("input:checkbox:checked");
        //取出checked框中的值
        var checkedId = "";
        var boo=true;
        if(option.length==0){
            alert("请选择删除项");
            return;
        }
        for (var i = 0, len = option.length; i < len; i++) {
            if (boo) {
                boo=false;
                checkedId += option[i].value;
            }
            else
                checkedId += ","+option[i].value;
        }
        var flag = window.confirm("你确定要删除这条记录吗");
        //alert(checkedId);
        if(flag){
            window.location.href = '${pageContext.request.contextPath}/article/delArticles?checkedId='+checkedId;
        }
    }
</script>
</body>
</html>
