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
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

    $(document).mouseup(function (e){
	    	var container = $("#wrapper");
	    		if( container.hasClass("toggled"))
	            $("#wrapper").toggleClass("toggled");
    	});
});

</script>
<title>브리지 임팩트 사역원입니다.</title>
</head>
<body>

<!-- wrapper -->
<div id="wrapper">
	<!-- sidebar area -->
	<%@ include file="/WEB-INF/include/sidebar.jsp" %>
	 <!-- /#sidebar area -->
		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<div class="jumbotron">
					<h2> 브리지 임팩트에 오신 것을 환영합니다!</h2>
		        	<p> 청소년들이 하나님의 은혜를 체험하고 그 은혜 안에서 자라갈수 있기를 원합니다. </p>
		        	<p>
		          		<a class="btn btn-lg btn-primary" href="#" role="button">View navbar docs »</a>
		        	</p>
				</div>
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
				</div>
			</div>
               <div class="row">
                   <div class="col-lg-12">
                       <h1>Simple Sidebar</h1>
                       <p>This template has a responsive menu toggling system. The menu will appear collapsed on smaller screens, and will appear non-collapsed on larger screens. When toggled using the button below, the menu will appear/disappear. On small screens, the page content will be pushed off canvas.</p>
                       <p>Make sure to keep all page content within the <code>#page-content-wrapper</code>.</p>
                       <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Toggle Menu</a>
                   </div>
               </div>
		<%@ include file="/WEB-INF/views/main/footer.jsp" %>
           </div>
</div>
<!-- /#wrapper -->
        <!-- /#page-content-wrapper -->
<!-- sidebar area end -->
</body>
</html>
