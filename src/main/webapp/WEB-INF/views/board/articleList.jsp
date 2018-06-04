<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/mainHeader.jsp" %>

<title>Home</title>
<script type="text/javascript">

$(document).ready(function(){ 
	var	 paramIdValue = getParameters('id');
	$("#id").val(paramIdValue);
	
}); 

$(function(){
	var	 paramIdValue = getParameters('id');
	$("#write").click(function() {
		location.href="../board/writeForm.bim?id="+ paramIdValue;
	});
	
});


</script>
</head>
<body>
 
 <div class="container articleList">
   <h4>	<a href="../board/viewList.bim?id=${board.id }">${board.name}</a></h4>
<%-- 
	<div class="table-responsive">
	    <table class="table table-hover">
	        <thead>
	            <tr>
	                <th width="8%"></th>
	                <th width="50%"></th>
					<th width="10%"></th>
	                <th width="12%"></th>
	                <th width="15%"></th>
	            </tr>
	        </thead>
	        <tbody>
	
	<c:choose>
	    <c:when test="${fn:length(articleList) > 0 }">
	            <c:forEach items="${articleList}" var="article" begin="0" end="${pageUtil.displayArticleCnt - 1}">
	                <tr>
	                    <td>${article.idx}</td>
	                    <td><a href="../board/viewArticle.bim?id=${article.boardId}&num=${article.idx}">${article.title}</a></td>
	                    <td>${article.writeId}</td>
	                    <td>${article.hitCnt}</td>
	                    <td>
	                    <jsp:useBean id="now" class="java.util.Date" />
						 <fmt:formatDate pattern = "yyyy-MM-dd"     value = "${now}" var="today"/>
							
				<c:choose>
				    <c:when test="${today eq article.writeDt}">
				    ${fn:substring(article.writeTime,0,5) }
				    </c:when>
				
				    <c:otherwise>
				    ${fn:substring(article.writeDt,5,10) }
				    </c:otherwise>
				</c:choose>
	
	                    </td>
	                </tr>
	
	            </c:forEach>
	    </c:when>
	
	
	    <c:otherwise>
	        게시글이 존재하지 않습니다. 
	    </c:otherwise>
	
	</c:choose>
	
	        </tbody>
	    </table>
	</div>
	     --%>

	
	<div class="containter">
		<c:choose>
		    <c:when test="${fn:length(articleList) > 0 }">
		    	<c:forEach items="${articleList}" var="article" begin="0" end="${pageUtil.displayArticleCnt - 1}">
		    		<ul>
		    			<li class="articleList">
		    				<a href="../board/viewArticle.bim?id=${article.boardId}&num=${article.idx}">
		    					<span>${article.title}</span>
		    					<span>
		    						<span>[${article.commentCnt}]</span>
		    						<span>${article.writeId}</span>
		    						<span>${article.hitCnt}</span>
		    						<span> 
			    						<jsp:useBean id="now" class="java.util.Date" /> <fmt:formatDate pattern="yyyy-MM-dd" value="${now}" var="today" /> 
				    						<c:choose>
												<c:when test="${today eq article.writeDt}">
												    ${fn:substring(article.writeTime,0,5) }
											    </c:when>
												<c:otherwise>
												    ${fn:substring(article.writeDt,5,10) }
											    </c:otherwise>
											</c:choose>
									</span>
		    					</span>
	    					</a>
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
 
<%@ include file="/WEB-INF/views/common/pageUtil.jsp" %>
<%@ include file="/WEB-INF/views/board/searchForm.jsp" %>



</body>
</html>


