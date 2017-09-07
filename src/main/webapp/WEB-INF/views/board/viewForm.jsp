<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Home</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">

var localhost= "${pageContext.request.contextPath }";

function commentEdit(idx) {
	var idx = idx;
	 $.ajax({
	        url : localhost+"/comment/commentEdit.bim",
	        type: "post",
	        data: 
	        		{ 
	        		"idx" : idx,
					"contents" : $("#comment").val()
					},
	        success : function(data){
	         if ( data.result == "success") {
				alert("댓글 작성 완료");
			}else {
				alert("댓글 작성 실패");
			}
	        }
	    });
	 
}

function commentDelete(idx) {
	var idx = idx;	
	alert(idx);
	 $.ajax({
	        url : localhost+"/comment/commentDelete.bim",
	        type: "post",
	        data: 
	        		{ 
	        		"idx" : idx,
					"contents" : $("#comment").val(),
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


$(document).ready(function(){ 
	




	$("#edit").click(function() {
		location.href="${pageContext.request.contextPath }/board/editForm.bim";
	})
	$("#delete").click(function() {
		if (confirm("정말로 게시물을 삭제 하시겠습니까?")) 
		location.href="${pageContext.request.contextPath }/board/boardDelete.bim";
	})
	$("#list").click(function() {
		if (confirm("정말로 글쓰기를 취소 하시겠습니까?")) {
		location.href="${pageContext.request.contextPath }/board/boardList.bim";
			
		}
	})
	$("#commentWrite").click(function() {
		var contents = $("#comment").val();
		var comment = {};
		comment.contents = contents;
		alert(comment.contents);
		
		 $.ajax({
		        url : localhost+"/comment/commentWrite.bim",
		        type: "post",
		        data: 
		        		{ 
		        		"parentIdx" : "${sessionScope.boardInfo.idx}",
						"contents" : $("#comment").val(),
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
	


	
		$("#commentDelete").click(function() {
		var contents = $("#comment").val();
		var comment = {};
		comment.contents = contents;
		alert(comment.contents);
		
		 $.ajax({
		        url : localhost+"/comment/commentWrite.bim",
		        type: "post",
		        data: 
		        		{ 
		        		"parentIdx" : "${sessionScope.boardInfo.idx}",
						"contents" : $("#comment").val(),
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
						<th width="50" height="50">${board.writeId}</th>
						<th width="50" height="50">조회수</th>
						<th width="50" height="50">${board.hitCnt}</th>
						<th width="50" height="50">작성일</th>
						<th height="50">${board.writeDt}</th>
					</tr>

					<tr>
						<th>제목</th>
						<td colspan="5" width="150" height="150">${board.title}</td>
					</tr>

					<tr>
						<th>내용</th>
						<td colspan="5" width="150" height="150">${board.contents}</td>
					</tr>
				</tbody>

			</table>
		<input type="submit" id="boardSubmit" value="글쓰기 완료" />
		<c:if test="${sessionScope.loginInfo.id == board.writeId}">
		<input type="button" id="edit" value="수정" />
		<input type="button" id="delete" value="삭제" />
		</c:if>
		<input type="button" id="list" value="목록" />
			</form>

	세션 아이디 : "${sessionScope.loginInfo.id}"
	작성자 아이디 : "${board.writeId}"
	
	
	<c:forEach items="${commentList}" var="comment">
			<table>

				<tbody>

					<tr>
						<th>댓글 </th>
						<td colspan="2" width="300" height="50">작성자 : ${comment.writeId} 댓글번호? : ${comment.idx}  </td>
						<td width="150" height="50">내용 :${comment.contents}</td>
						<td width="150">
						<c:set var="commentWriteId" value="${comment.writeId}" />
						<c:set var="sessionId" value="${sessionScope.loginInfo.id}" />
						
						<c:if test="${commentWriteId == sessionId}">
						   <input type="button" id="commentEdit" value="댓글수정" onclick="commentEdit(${comment.idx})">
						   <input type="button" id="commentDelete" value="댓글삭제" onclick="commentDelete(${comment.idx})">
						    
						</c:if>
						</td>
					</tr>

				
				</tbody>
			</table>
	</c:forEach>


			<table border="2">

				<tbody>

					<tr>
						<th>댓글 내용</th>
						<td colspan="5" width="250" height="50"><input type="text" size="40" value="" id="comment" placeholder="댓글을 입력하세요"></td>
						<td><input type="button" id="commentWrite" value="댓글작성"></td>
					</tr>

				
				</tbody>

			</table>
</body>
</html>

