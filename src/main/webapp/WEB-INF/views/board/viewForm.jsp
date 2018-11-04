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
<script type="text/javascript">
$(document).ready(function(){
	getCommentList();

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
}); 

$(function(){
	var idParam = "?" + "id=" + getParameters('id');
	var param =  idParam +"&num=" + getParameters('num');
	$("#edit").on('click',function() {
		var sessionId = "${sessionScope.loginInfo.id}";
		var writerId =  "${article.writeId}";
	    if (sessionId == writerId) {
	    	location.href="../board/editForm.bim"+param;
	    }else{
	    	alert("잘못된 접근 입니다.");
	    	location.href="../board/viewArticle.bim"+param;
	    }
	});
	$("#delete").on('click',function() {
		if (confirm("정말로 게시물을 삭제 하시겠습니까?")) 
		location.href="../board/deleteArticle.bim"+param;
	});
	

	
});


</script>
</head>
<body>
	<form>
		<div class="container viewArticle">
			<div class="main_view">
				<div class="article_boardName">
					<h3 class="mb-1"><c:forEach items="${boardList}" var="board"><c:if test="${param.id eq board.id}"><a href="../board/viewList.bim?id=${board.id }">${board.name}</a></c:if></c:forEach></h3>
				</div>
	 <%-- 
					<table class="table">
						<tbody>
							<tr>
								<td class="title" colspan="5"><h3 id="article_title">${article.title}</h3></td>
							</tr>
							<tr >
								<td class="writer" width="70" height="30">작성자</td>
								<td class="writer" colspan="2">${article.writeId}</td>
								<td class="writer" width="60" height="30">조회수</td>
								<td class="writer" width="50" height="30">${article.hitCnt}</td>
							</tr>
		
							<tr>
								<th height="50">작성일</th>
								<td colspan="5">${article.writeDt}</td>
							</tr>
		
							<tr>
								<th>내용</th>
								<td colspan="5" width="150" height="150">${article.contents}</td>
							</tr>
						</tbody>
		
					</table>
	 		  --%>
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
						<a href="../download.bim?num=${file.idx }">${file.originalFileName }</a>				
					</div>
					</c:forEach>
				</div>
				<div class="article_edit">
					<c:if test="${sessionScope.loginInfo.id == article.writeId}">
					<input type="button" id="edit" value="수정" class="btn btn-primary"/>
					<input type="button" id="delete" value="삭제" class="btn btn-primary"/>
					</c:if>
					<input type="button" id="list" value="목록" class="btn btn-primary"/>
				</div>
			</div>
 		<div class="main_view" id="main_view"></div>
		<%-- <%@ include file="/WEB-INF/views/board/commentForm.jsp" %> --%>
		</div>
	</form>
<%@ include file="/WEB-INF/views/main/footer.jsp" %>
</body>
</html>


