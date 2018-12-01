<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>브리지 임팩트 사역원 입니다.</title>
<%@ include file="/WEB-INF/include/navbar-header.jsp" %>			<!-- navbar header -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main/layout.css">    <!--main/layout CSS -->
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript">
$(document).ready(function(){

});
</script>
<title>브리지 임팩트 사역원입니다.</title>
</head>
<body>
<div class="container" id="main_page">
    <%--Carousel Area--%>
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <%--        <li data-target="#myCarousel" data-slide-to="2"></li>--%>
        </ol><!--/.Indicators -->
        <%--Carousel-inner Area--%>
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="http://www.hcc.or.kr/Attachment/media/90009_181118_001.jpg" alt="First slide">
                <div class="container">
                    <div class="carousel-caption">
                        <%--<h1>Example headline.</h1>--%>
                        <%--<p>Note: If you're viewing this page via a <code>file://</code> URL, the "next" and "previous" Glyphicon buttons on the left and right might not load/display properly due to web browser security rules.</p>--%>
                        <%--<p><a class="btn btn-lg btn-primary" href="#" role="button">Sign up today</a></p>--%>
                    </div>
                </div>
            </div>
            <div class="item">
                <img src="http://www.hcc.or.kr/Attachment/media/90008_181118_002.jpg" alt="Second slide">
                <div class="container">
                    <div class="carousel-caption">
                        <%--<h1>Another example headline.</h1>--%>
                        <%--<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>--%>
                        <%--<p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>--%>
                    </div>
                </div>
            </div>
            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div><%--/.Carousel-inner Area--%>
    </div><%--/.Carousel Area--%>
    <%--main_page_row (recent article)    --%>
    <div class="main_page row">
        <c:forEach items="${boardList }" var="boardList">
        <div class="col-md-6" >
            <div class="recent_article" id="${boardList.id }">
                <h2 class="mainTitle"><a href="${pageContext.request.contextPath}/board/viewList.bim?id=${boardList.id }">${boardList.name }</a></h2>
                <c:forEach items="${mainArticleList }" var="articleList">
                <div class="mainArticle">
                    <c:if test="${articleList.boardId eq boardList.id }">
                    <a href="${pageContext.request.contextPath}/board/viewArticle.bim?id=${articleList.boardId}&num=${articleList.idx}">
                        <span class="boardName">${articleList.boardName}</span>
                        <span class="articleTitle">${articleList.title}</span>
                    </a>
                    </c:if>
                </div>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
    </div><%--/. main_page_row (recent article)    --%>
</div>
<%@ include file="/WEB-INF/views/main/footer.jsp" %>
</body>
</html>
