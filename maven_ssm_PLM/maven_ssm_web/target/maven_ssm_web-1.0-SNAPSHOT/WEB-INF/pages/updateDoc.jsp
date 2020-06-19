<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title>新增文档 - 后台管理系统模板</title>
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/ico">
    <meta name="keywords" content="后台管理系统">
    <meta name="description" content="LightYear是一个基于Bootstrap v3.3.7的后台管理系统的HTML模板。">
    <meta name="author" content="yinqi">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/materialdesignicons.min.css" rel="stylesheet">
    <!--标签插件-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery-tags-input/jquery.tagsinput.min.css">
    <link href="${pageContext.request.contextPath}/css/style.min.css" rel="stylesheet">
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
                            <div class="card-body">

                                <form action="${pageContext.request.contextPath}/article/updateArticle" method="post"
                                      class="row">
                                    <input type="hidden" id="id" name="id" value="${article.id}"/>
                                    <input type="hidden" id="delStatus" name="delStatus" value="${article.delStatus}">
                                    <div class="form-group col-md-12">
                                        <label for="name">名称</label>
                                        <input type="text" class="form-control" id="name" name="name" value="${article.name}"
                                               placeholder="请输入标题"/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="startDate">借出时间</label>
                                        <input class="js-tags-input form-control" type="text" id="startDate"
                                               name="startDate" value="${article.startDate}"/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="endDate">归还时间</label>
                                        <input class="js-tags-input form-control" type="text" id="endDate"
                                               name="endDate" value="${article.endDate}"/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="details">详情</label>
                                        <textarea class="form-control" id="details" name="details" rows="5" value=""
                                                  placeholder="描述">${article.details}</textarea>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="users">使用者</label>
                                        <input type="text" class="form-control" id="users" name="users" value="${article.users}"
                                               placeholder="人名"/>
                                    </div>

                                    <div class="form-group col-md-12">
                                        <label for="status">状态</label>
                                        <div class="clearfix">
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input type="radio" name="status" value="0" ${article.status==0?"checked":""}><span>借出</span>
                                            </label>
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input type="radio" name="status" value="1" ${article.status==1?"checked":""}><span>归还</span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <button type="submit" class="btn btn-primary ajax-post" target-form="add-form">确
                                            定
                                        </button>
                                        <button type="button" class="btn btn-default"
                                                onclick="javascript:history.back(-1);return false;">返 回
                                        </button>
                                    </div>
                                </form>

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
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<!--标签插件-->
<script src="js/jquery-tags-input/jquery.tagsinput.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.min.js"></script>


</body>
</html>