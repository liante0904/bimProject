<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/includeHeader.jsp" %>
<%@ include file="/WEB-INF/include/articleHeader.jsp" %>
<title>Home</title>
<script type="text/javascript">

$(document).ready(function(){
	 idParam = "?" + "id=" + getParameters('id');
	 param =  idParam +"&num=" + getParameters('num');

}); 


$(function(){
	$("#edit").click(function() {
		var sessionId = "${sessionScope.loginInfo.id}";
		var writerId =  "${article.writeId}";
	    if (sessionId == writerId) {
	    	location.href="${pageContext.request.contextPath }/board/editForm.bim"+param;
	    }else{
	    	alert("잘못된 접근 입니다.");
	    	location.href="${pageContext.request.contextPath }/board/viewArticle.bim"+param;
	    }
	});

	$("#delete").click(function() {
		if (confirm("정말로 게시물을 삭제 하시겠습니까?")) 
		location.href="${pageContext.request.contextPath }/board/deleteArticle.bim"+param;
	});
	
	$("#list").click(function() {
		if (confirm("정말로 글쓰기를 취소 하시겠습니까?")) {
		location.href="${pageContext.request.contextPath }/board/viewList.bim"+idParam;
		}
	});

	$("#writeComment").click(function() {
		 $.ajax({
		        url : "/comment/writeComment.bim",
		        type: "post",
		        data: 
		        		{ 
		        		"parentIdx" : "${sessionScope.articleInfo.idx}",
						"contents" : $("#writeCommentContents").val(),
						"writeId" : "${sessionScope.loginInfo.id}"
						},
		        success : function(data){
		         if ( data.result == "success") {
					alert("댓글 작성 완료");
				}else {
					alert("댓글 작성 실패");
				}
		        }
		    });
	})



});

function editCommentInit(idx) {
	var editDiv = $('div[id=editDiv][data-idx='+idx+']');
	editDiv.show();
	
	var selectComment= $('p[data-idx='+idx+']');
	var selectCommentValue = selectComment.text();
	
	var beforeComment = $('#editCommentContents[data-idx='+idx+']');
	var beforeCommentValue = beforeComment.val();
	
	console.log(selectCommentValue);
	console.log(beforeCommentValue);



}

function editComment(idx){


	
	
	 $.ajax({
	        url : "/comment/editComment.bim",
	        type: "post",
	        data: { 
	        		"idx" : idx, 
	        		"contents" : $("#editCommentContents").val(),
					"writeId" : "${sessionScope.loginInfo.id}"
					},
	        success : function(data){
	         if ( data.result == "success") {
				alert("댓글 수정 완료");
				editCommentAction(idx);
			}else {
				alert("댓글 수정 실패");
			}
	         
	        }
	    });
	 
}
function editCommentAction(idx){
	var selectComment= $('p[data-idx='+idx+']');
	var selectCommentValue = selectComment.text();
	
	var beforeComment = $('#editCommentContents[data-idx='+idx+']');
	var beforeCommentValue = beforeComment.val();
	
	console.log(selectCommentValue);
	console.log(beforeCommentValue);

};

function deleteComment(idx){
	 $.ajax({
	        url : "/comment/deleteComment.bim",
	        type: "post",
	        data: { 
	        		"idx" : idx,
					"writeId" : "${sessionScope.loginInfo.id}"
					},
	        success : function(data){
	         if ( data.result == "success") {
				alert("댓글 삭제 완료");
			}else {
				alert("댓글 삭제 실패");
			}
	         
	        }
	    });
	 
}
 
 




</script>
</head>
<body>
	<h1>viewForm.jsp</h1>
	<form action="${pageContext.request.contextPath }/board/boardWrite.bim"
		method="post">
			<table border="2">

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
		<input type="button" id="edit" value="수정" />
		<input type="button" id="delete" value="삭제" />
		</c:if>
		<input type="button" id="list" value="목록" />
			</form>

	세션 아이디 : "${sessionScope.loginInfo.id}"
	작성자 아이디 : "${article.writeId}"
	
	
						<h4>댓글</h4>
	<c:forEach items="${commentList}" var="comment">
					<p data-idx=${comment.idx }>
						작성자 : ${comment.writeId} 댓글번호 : ${comment.idx}  
						내용 :${comment.contents}
						<c:set var="writeCommentId" value="${comment.writeId}" />
						<c:set var="sessionId" value="${sessionScope.loginInfo.id}" />
						
						<c:if test="${writeCommentId == sessionId}">
						   <input type="button" id="editCommentInit" value="댓글수정" onclick="editCommentInit(${comment.idx})" data-idx="${comment.idx}">
						   <input type="button" id="deleteComment" value="댓글삭제" onclick="deleteComment(${comment.idx})">
						    
						</c:if>
					</p>
						<div id="editDiv"  data-idx="${comment.idx}" style="display:none; margin: 50px 1px 50px 1px">
							<p>
						<input type="text" size="40" id="editCommentContents" value="${comment.contents}" data-idx="${comment.idx}" placeholder="댓글을 수정하세요"> 
						<input type="button" onclick="editComment(${comment.idx})" value="댓글수정">
							</p>
						</div> 
						
	</c:forEach>
		<c:if test="${!empty sessionScope.loginInfo}">
			<table border="2">
				<tbody>
					<tr>
						<th>댓글 내용</th>
						<td colspan="5" width="250" height="50"><input type="text" size="40" id="writeCommentContents" placeholder="댓글을 입력하세요"></td>
						<td><input type="button" id="writeComment" value="댓글작성"></td>
					</tr>
				</tbody>
			</table>
		</c:if>
</body>
</html>


