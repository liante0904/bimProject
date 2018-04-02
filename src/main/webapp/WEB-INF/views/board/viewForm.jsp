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
<h4>${article.boardName}</h4>
<h3 id="article_title">${article.title}</h3>
	<form action="../board/boardWrite.bim"
		method="post">
		<div class="container viewArticle">
			<table class="table">
				<tbody>
					<tr>
						<th width="70" height="30">작성자</th>
						<td colspan="2">${article.writeId}</td>
						<th width="60" height="30">조회수</th>
						<th width="50" height="30">${article.hitCnt}</th>
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
			
				<c:forEach items="${fileList}" var="file">
				<div id="fileList"><span>첨부파일 : </span>
					<a href="../download.bim?num=${file.idx }">${file.originalFileName }</a>				
				</div>
				</c:forEach>
		<c:if test="${sessionScope.loginInfo.id == article.writeId}">
		<input type="button" id="edit" value="수정" class="btn btn-primary"/>
		<input type="button" id="delete" value="삭제" class="btn btn-primary"/>
		</c:if>
		<input type="button" id="list" value="목록" class="btn btn-primary"/>
		</div>
			</form>

<div id="commentContent"></div>

</body>
</html>


