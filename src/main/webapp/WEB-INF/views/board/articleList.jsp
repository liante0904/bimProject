<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.name}</title>
<%@ include file="/WEB-INF/include/navbar-header.jsp" %>
<link rel="stylesheet" href="../resources/css/article.css">    <!-- article CSS -->
<link rel="stylesheet" href="../resources/css/board/board-layout.css">    <!-- board-layout CSS -->
<link rel="stylesheet" href="../resources/bootstrap/css/simple-sidebar.css">

<script type="text/javascript">

$(document).ready(function(){ 
	var paramIdValue = getParameters('id');
	$("#id").val(paramIdValue);
	
}); 

$(function(){
	var paramIdValue = getParameters('id');
	$("#write").click(function() {
		location.href="../board/writeForm.bim?id="+ paramIdValue;
	});
	
});


</script>
</head>
<body>
 <div class="container center articleList">
 	<div class="main_view">
	   <h4 class="mb-1">	<a href="../board/viewList.bim?id=${board.id }">${board.name}</a></h4>
		<div class="articleList">
			<c:choose>
			    <c:when test="${fn:length(articleList) > 0 }">
			    	<c:forEach items="${articleList}" var="article" begin="0" end="${pageUtil.displayArticleCnt - 1}">
			    		<ul class="articleList">
			    			<li class="articleList">
			    				<div class="list-item">
			    					<div class="list-title">			    					
					    				<a href="../board/viewArticle.bim?id=${article.boardId}&num=${article.idx}">
					    					<span class="title">${article.title}</span>
				    					</a>
				    					<c:if test="${article.commentCnt > 0}">
				    						<div class="list-reply">
				    							<a href=""><span class="comment">[${article.commentCnt}]</span></a>
				    						</div>
				    					</c:if>
			    					</div>
			    					<div class="articleInfo">
			    						<div class="writeId">${article.writeId}</div>
			    						<div class="hitCnt">${article.hitCnt}</div>
			    						<div class="writeDt"> 
				    						<jsp:useBean id="now" class="java.util.Date" /> <fmt:formatDate pattern="yyyy-MM-dd" value="${now}" var="today" /> 
					    						<c:choose>
													<c:when test="${today eq article.writeDt}">
													    ${fn:substring(article.writeTime,0,5) }
												    </c:when>
													<c:otherwise>
													    ${fn:substring(article.writeDt,5,10) }
												    </c:otherwise>
												</c:choose>
										</div>
			    					</div>
			    				</div>
			    			</li>
			    		</ul>
			    	</c:forEach>
			    </c:when>
			    
		   	    <c:otherwise>
			        게시글이 존재하지 않습니다. 
			    </c:otherwise>
		    </c:choose>	
	   	 <input type="button" id="write" class="btn btn-primary pull-right" value="글쓰기"/>
		</div>
 	</div>
</div>

<%@ include file="/WEB-INF/views/common/pageUtil.jsp" %>
<%@ include file="/WEB-INF/views/board/searchForm.jsp" %>
<%@ include file="/WEB-INF/views/main/footer.jsp" %>


</body>
</html>


