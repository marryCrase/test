<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="lyear-layout-sidebar">

    <!-- logo -->
    <div id="logo" class="sidebar-header">
<%--        <a href="${pageContext.request.contextPath}/article/findAllArticle.do"><img src="${pageContext.request.contextPath}/img/logo-sidebar.png" title="LightYear" alt="LightYear" /></a>--%>
    <a href="${pageContext.request.contextPath}/index.jsp"></a>
    </div>
    <div class="lyear-layout-sidebar-scroll">

        <nav class="sidebar-main">
            <ul class="nav nav-drawer">
                <li class="nav-item active"> <a href="${pageContext.request.contextPath}/index.jsp"><i class="mdi mdi-home"></i> 后台首页</a> </li>

                <li class="nav-item nav-item-has-subnav">
                    <a href="javascript:void(0)"><i class="mdi mdi-file-outline"></i> 器材管理</a>
                    <ul class="nav nav-subnav">
                        <li> <a href="${pageContext.request.contextPath}/article/findAllArticle.do">器材列表</a> </li>
                    </ul>
                </li>

            </ul>
        </nav>

    </div>

</aside>
