<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/css/main/layout.css">    <!-- common CSS -->
<%@ include file="/WEB-INF/include/mainHeader.jsp" %>
<script type="text/javascript">
$(document).ready(function(){

});
</script>
<title>브리지 임팩트 사역원입니다.</title>
</head>
<body>

<div class="container">
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
<%--
			<div class="col-md-6" id="freeboard">
			  	<c:forEach items="${articleList }" var="articleList">
			  		<c:if test="${articleList.boardId eq 'freeboard' }">
						<div>
							<a href="${pageContext.request.contextPath}//board/viewArticle.bim?id=${articleList.boardId }&num=${articleList.idx } ">${articleList.title }</a>
						</div>
			  		</c:if>
			  	</c:forEach>
			</div>
			 --%>
		</div>
</div>
<%@ include file="/WEB-INF/views/main/footer.jsp" %>
</body>
</html>
