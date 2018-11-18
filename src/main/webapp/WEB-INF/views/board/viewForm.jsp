<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>브리지 임팩트 - ${article.boardName}</title>
<link rel="stylesheet" href="../resources/css/article.css">    <!-- article CSS -->
<link rel="stylesheet" href="../resources/css/board/board-layout.css">    <!-- board-layout CSS -->
<%@ include file="/WEB-INF/include/navbar-header.jsp" %>
<script src="../resources/js/article.js"></script>
<script src="../resources/js/comment.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	getCommentList();

}); 
function getCommentList(){
	var num = getParameters('num');
	var data = { num : num };
		$.ajax({
	        type : "POST",
	        url : "../comment/getCommentList.bim",
	        data : data,
	        dataType : "html",
	        success : function(data){
	           	$("#main_view").html(data);
	        },
	        error : function(data){
	            alert(' 실패!!');
	        }
		});
		
	}
</script>
</head>
<body>
	<form>
<!-- wrapper -->
<div id="wrapper">
	<!-- sidebar area -->
	<%@ include file="/WEB-INF/include/sidebar.jsp" %>
	<div class="container viewArticle">
		<div class="main_view">
			<div class="article_boardName">
				<h3 class="mb-1"><c:forEach items="${boardList}" var="board"><c:if test="${param.id eq board.id}"><a href="../board/viewList.bim?id=${board.id }">${board.name}</a></c:if></c:forEach></h3>
			</div>
	 		<div class="article_title"><h4 class="mt-2">${article.title}</h4></div>
	 		<div class="article_info1">
		 			<div class="article_writer"><span>${article.writeId}</span></div>
		 			<div class="article_stat">
						<span class="glyphicon glyphicon-sunglasses" aria-hidden="true"></span>
			 			<span class="article_hitCnt">${article.hitCnt}</span>
		 			</div>
	 		</div>
	 		<div class="article_info2">
		 			<span class="article_date">${article.writeDt}</span>
	 		</div>
			<div class="article_contents">${article.contents}</div>
			<div class="article_files">
				<c:forEach items="${fileList}" var="file">
				<div id="fileList"><span>첨부파일 : </span>
					<a href="../download.bim?num=${file.articleIdx }&storedNm=${file.storedFileName}">${file.originalFileName }</a>				
				</div>
				</c:forEach>
			</div>
			<div class="article_edit">
				<c:if test="${sessionScope.loginInfo.id == article.writeId}">
				<input type="button" id="edit-form" value="수정" class="btn btn-primary"/>
				<input type="button" id="delete-submit" value="삭제" class="btn btn-primary"/>
				</c:if>
				<input type="button" id="list" value="목록" class="btn btn-primary"/>
			</div>
	 		<div class="main_view" id="main_view">
				<%@ include file="/WEB-INF/views/board/commentForm.jsp" %>
	 		</div>
			</div>
		</div>
</div>
	</form>
<%@ include file="/WEB-INF/views/main/footer.jsp" %>
</body>
</html>


