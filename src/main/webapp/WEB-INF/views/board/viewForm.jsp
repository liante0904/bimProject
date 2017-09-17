<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/includeHeader.jsp" %>
<title>Home</title>
<script type="text/javascript">




function commentEditInit(idx) {
	var editDiv = $('div[id=editDiv][data-idx='+idx+']');
	
	editDiv.show();

}

function commentDelete(idx){
	 $.ajax({
	        url : localhost+"/comment/commentDelete.bim",
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
 
 
$(document).ready(function(){ 
	var getParameters = function (paramName) {
	    // 리턴값을 위한 변수 선언
	    var returnValue;

	    // 현재 URL 가져오기
	    var url = location.href;

	    // get 파라미터 값을 가져올 수 있는 ? 를 기점으로 slice 한 후 split 으로 나눔
	    var parameters = (url.slice(url.indexOf('?') + 1, url.length)).split('&');

	    // 나누어진 값의 비교를 통해 paramName 으로 요청된 데이터의 값만 return
	    for (var i = 0; i < parameters.length; i++) {
	        var varName = parameters[i].split('=')[0];
	        if (varName.toUpperCase() == paramName.toUpperCase()) {
	            returnValue = parameters[i].split('=')[1];
	            return decodeURIComponent(returnValue);
	        }
	    }
	};
	alert(getParameters('id'));
	console.log(getParameters('id'));

	$("#edit").click(function() {
        if (window.sessionStorage) {

           // sessionStorage.setItem('저장할 이름 - 문자열', '저장할 객체');
            var position = sessionStorage.getItem("articleInfo");
            console.log(position);
        }
		//location.href="${pageContext.request.contextPath }/board/editForm.bim";
	})
	
	$("#delete").click(function() {
		if (confirm("정말로 게시물을 삭제 하시겠습니까?")) 
		location.href="${pageContext.request.contextPath }/board/deleteArticle.bim";
	})
	$("#list").click(function() {
		if (confirm("정말로 글쓰기를 취소 하시겠습니까?")) {
		location.href="${pageContext.request.contextPath }/board/boardList.bim";
		}
	})
	
	$("#commentWrite").click(function() {
		/* 
		var contents = $("#commentWriteContents").val();
		var comment = {};
		comment.contents = contents;
		alert(comment.contents);
		 */
		 
		 $.ajax({
		        url : localhost+"/comment/commentWrite.bim",
		        type: "post",
		        data: 
		        		{ 
		        		"parentIdx" : "${sessionScope.articleInfo.idx}",
						"contents" : $("#commentWriteContents").val(),
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
					<p>
						작성자 : ${comment.writeId} 댓글번호 : ${comment.idx}  
						내용 :${comment.contents}
						<c:set var="commentWriteId" value="${comment.writeId}" />
						<c:set var="sessionId" value="${sessionScope.loginInfo.id}" />
						
						<c:if test="${commentWriteId == sessionId}">
						   <input type="button" id="commentEditInit" value="댓글수정" onclick="commentEditInit(${comment.idx})" data-idx="${comment.idx}">
						   <input type="button" id="commentDelete" value="댓글삭제" onclick="commentDelete(${comment.idx})">
						    
						</c:if>
					</p>
						<div id="editDiv"  data-idx="${comment.idx}" style="display:none; margin: 50px 1px 50px 1px">
							<p>
						<input type="text" size="40" id="commentEditContents" value="${comment.contents}" placeholder="댓글을 수정하세요"> 
						<input type="button" onclick="commentEdit(${comment.idx})" value="댓글수정">
							</p>
						</div> 
						
	</c:forEach>




			<table border="2">

				<tbody>

					<tr>
						<th>댓글 내용</th>
						<td colspan="5" width="250" height="50"><input type="text" size="40" id="commentWriteContents" placeholder="댓글을 입력하세요"></td>
						<td><input type="button" id="commentWrite" value="댓글작성"></td>
					</tr>

				
				</tbody>

			</table>
</body>
</html>


