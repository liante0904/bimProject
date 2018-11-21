<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../resources/css/comment.css">    <!-- comment CSS -->
<script src="../resources/js/comment.js"></script>
</head>
<body>
	<div class="comment_title">
	<c:set var="sessionId" value="${sessionScope.loginInfo.id}" />
		<h4>댓글</h4>
	</div>
	<div class="comment_list">
		<c:forEach items="${commentList}" var="comment" varStatus="status">
		<div class="comment_info">
			<div class="comment_writer">
				<span>${comment.writeId}님</span>
				<c:set var="writeCommentId" value="${comment.writeId}" />
				<c:set var="sessionId" value="${sessionScope.loginInfo.id}" /> 
					<c:if test="${writeCommentId == sessionId}">
					</c:if>
			</div>
			<div class="comment_date">
				<span>${comment.writeDt}</span>
			</div>
		</div>
		<div class="comment_contents">
			<span class="comment_contents">${comment.contents}</span>
		</div>
		<div class="comment-button">
			<a class="reWriteComment" data-idx="${comment.idx}"><span>대댓글</span></a>
			<a class="editComment" data-idx="${comment.idx}"><span>수정</span></a>
			<a class="deleteComment_submit" data-idx="${comment.idx}"><span>삭제</span></a>
		</div>
		<!-- 댓글 수정 영역 -->
		<c:set var="writeCommentId" value="${comment.writeId}" />
		<c:set var="sessionId" value="${sessionScope.loginInfo.id}" /> 
		<c:if test="${writeCommentId == sessionId}">
			<div class="comment_editForm_div" id="${comment.idx}">
				<div class="comment_depth_picker"></div>
				<div class="comment_editForm">
					<c:if test="${!empty sessionScope.loginInfo}">
						댓글 수정
						<textarea rows="" cols="5px" class="form-control" id="editCommentContents" data-idx="${comment.idx}" placeholder="댓글을 수정하세요" >${comment.contents}</textarea>
						<input type="button" id="editComment_submit" value="댓글수정" class="editComment_submit btn btn-primary" data-idx="${comment.idx}">
					</c:if>
				</div>
			</div>
		</c:if>
		<!-- /댓글 수정 영역/ -->
		</c:forEach>
	</div>
	<div class="comment_writeForm">
		<c:if test="${!empty sessionScope.loginInfo}"><!-- 로그인 상태일 경우 댓글 작성가능 -->
			댓글 작성
			<textarea rows="" cols="5px" class="form-control" id="writeCommentContents" placeholder="댓글을 입력하세요" ></textarea>
			<input type="button" id="writeComment" value="댓글작성" class="btn btn-primary" data-parentIdx="${parentIdx }" data-writerId="${sessionScope.loginInfo.id }">
		</c:if>
	</div>
<%-- 
<h4>댓글</h4>
	<table class="table table-hover">
		<tbody>
			<tr>
				<th width="15%">댓글(갯수)</th>
				<th width="60%">댓글 내용</th>
				<th width="25%">댓글로 소통하세요!</th>
			</tr>
			<c:forEach items="${commentList}" var="comment">
				<tr>
					<td>${comment.writeId}님</td>
					<td>${comment.contents}</td>
					<td><c:set var="writeCommentId" value="${comment.writeId}" />
						<c:set var="sessionId" value="${sessionScope.loginInfo.id}" /> <c:if test="${writeCommentId == sessionId}">
							<input type="button" id="editCommentInit" value="댓글수정"	onclick="editCommentInit(${comment.idx})" data-idx="${comment.idx}" class="btn btn-primary btn-xs">
							<input type="button" id="deleteComment" value="댓글삭제" onclick="deleteComment(${comment.idx})" class="btn btn-primary btn-xs">
						</c:if></td>
					<td></td>
				</tr>
				
			</c:forEach>
		</tbody>
	</table>

	<!-- 댓글 수정 영역 -->
	<c:forEach items="${commentList}" var="comment">
		<div id="editDiv"  data-idx="${comment.idx}" style="display:none; margin: 50px 1px 50px 1px">
			<p>
		<input type="text" size="40" id="editCommentContents" value="${comment.contents}" data-idx="${comment.idx}" placeholder="댓글을 수정하세요" class="form-control"> 
		<input type="button" onclick="editComment(${comment.idx})" value="댓글수정" class="btn btn-primary">
			</p>
		</div> 
	</c:forEach>
	<!-- /댓글 수정 영역/ -->
	
	 --%>
		
<%-- 

	<c:forEach items="${commentList}" var="comment">
		<p data-idx=${comment.idx }>
			작성자 : ${comment.writeId} 댓글번호 : ${comment.idx}  
			내용 :${comment.contents}
			
			<c:if test="${writeCommentId == sessionId}">
			   <input type="button" id="editCommentInit" value="댓글수정" onclick="editCommentInit(${comment.idx})" data-idx="${comment.idx}" class="btn btn-primary">
			   <input type="button" id="deleteComment" value="댓글삭제" onclick="deleteComment(${comment.idx})" class="btn btn-primary">
			    
			</c:if>
		</p>
	</c:forEach>

 --%>

</body>
</html>