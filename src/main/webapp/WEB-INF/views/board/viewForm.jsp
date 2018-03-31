<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/mainHeader.jsp" %>
<%@ include file="/WEB-INF/include/common.jsp" %>
<title>Home</title>
<script type="text/javascript">
$(document).ready(function(){
	getCommentList();

function getCommentList(){
	var num = getParameters('num');
	var data = { num : num };
		$.ajax({
	        type : "POST",
	        url : "${pageContext.request.contextPath }/comment/getCommentList.bim",
	        data : data,
	        dataType : "html",
	        success : function(data){
	           	$("#commentContent").html(data);
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
	    	location.href="${pageContext.request.contextPath }/board/editForm.bim"+param;
	    }else{
	    	alert("잘못된 접근 입니다.");
	    	location.href="${pageContext.request.contextPath }/board/viewArticle.bim"+param;
	    }
	});
	$("#delete").on('click',function() {
		if (confirm("정말로 게시물을 삭제 하시겠습니까?")) 
		location.href="${pageContext.request.contextPath }/board/deleteArticle.bim"+param;
	});
	
	$("#list").on('click',function() {
		if (confirm("정말로 글쓰기를 취소 하시겠습니까?")) {
		location.href="${pageContext.request.contextPath }/board/viewList.bim"+idParam;
		}
	});
	
});


</script>
</head>
<body>
	<h1>viewForm.jsp</h1>
	<form action="${pageContext.request.contextPath }/board/boardWrite.bim"
		method="post">
		<div class="table-responsive">
			<table class="table" border="2">

				<tbody>
					<tr>
						<th width="50" height="50">작성자</th>
						<th width="50" height="50">${article.writeId}</th>
						<th width="50" height="50">조회수</th>
						<th width="50" height="50">${article.hitCnt}</th>
						<th width="50" height="50">작성일</th>
						<th height="50">${article.writeDt}</th>
					</tr>

					<tr>
						<th>제목</th>
						<td colspan="5" width="150" height="150">${article.title}</td>
					</tr>

					<tr>
						<th>내용</th>
						<td colspan="5" width="150" height="150">${article.contents}</td>
					</tr>
				</tbody>

			</table>
		<c:if test="${sessionScope.loginInfo.id == article.writeId}">
		<input type="button" id="edit" value="수정" class="btn btn-primary"/>
		<input type="button" id="delete" value="삭제" class="btn btn-primary"/>
		</c:if>
		<input type="button" id="list" value="목록" class="btn btn-primary"/>
		</div>
			</form>

	세션 아이디 : "${sessionScope.loginInfo.id}"
	작성자 아이디 : "${article.writeId}"
<div id="commentContent"></div>

</body>
</html>


