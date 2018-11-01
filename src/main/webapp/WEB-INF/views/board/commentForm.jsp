<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/include/include.jsp" %>
<link rel="stylesheet" href="../resources/css/comment.css">    <!-- comment CSS -->
<script type="text/javascript">
$(document).ready(function() {

});

$(function(){
	$("#writeComment").on('click',function() {
		var writeCommentContentsForm = $("#writeCommentContents");
		var commentContents = writeCommentContentsForm.val();
		if (commentContents.length < 2) {
			alert("댓글의 내용이 너무 짧습니다. (3글자 이상)");
			writeCommentContentsForm.focus();
			return false;
		}
		 $.ajax({
		        url : "../comment/writeCommentAjax.bim",
		        type: "POST",
		        data: 
		        		{ 
		        		"parentIdx" : "${sessionScope.articleInfo.idx}",
						"contents" : commentContents,
						"writeId" : "${sessionScope.loginInfo.id}"
						},
		        success : function(data){
		         if ( data.result == "success") {
					console.log("댓글 작성 완료");
				}
		        },
		        error : function(error){
		        	alert("댓글 등록 실패");
		        },
		        complete : function(){
					getCommentList();
		        }
		    });
	});
	$(".deleteComment_submit").on('click', function() {
		if (!confirm("댓글을 삭제 하시겠습니까?")) {
			return false;
		}else {
		var idx = $(this).attr('data-idx');
		 $.ajax({
		        url : "../comment/deleteCommentAjax.bim",
		        type: "post",
		        data: { 
		        		"idx" : idx,
						"writeId" : "${sessionScope.loginInfo.id}"
						},
		        success : function(data){
			         if ( data.result == "success") {
						console.log("댓글 삭제 완료");
					}else {
						alert("댓글 삭제 실패");
					}
		        },
		        error : function(error){
		        	alert("댓글 삭제 실패");
		        },
		        complete : function(){
					getCommentList();
		        }
		    });
		}
	});
	
	$(".editComment").on('click', function() {
		var idx = $(this).attr('data-idx');
		var editComment_div = $('div[class=comment_editForm_div][id='+idx+']');
		var editComment_input_div = $('textarea[id=editCommentContents][data-idx='+idx+']');
		editComment_div.show();
		editComment_input_div.focus();
	});
	$(".reWriteComment").on('click', function() {
		//TODO 대댓글
		alert("TODO 대댓글 ");
		var idx = $(this).attr('data-idx');
		var editComment_div = $('div[class=comment_editForm_div][id='+idx+']');
		var editComment_input_div = $('textarea[id=editCommentContents][data-idx='+idx+']');
		editComment_div.show();
		editComment_input_div.focus();
	});
	$(".editComment_submit").on('click', function() {
		var idx = $(this).attr('data-idx');
		var editCommentContentsForm = $("#editCommentContents");
		var editCommentContents = editCommentContentsForm.val();
		if (editCommentContents.length < 2) {
			alert("댓글의 내용이 너무 짧습니다. (3글자 이상)");
			editCommentContentsForm.focus();
			return false;
		}
		$.ajax({
		        url : "../comment/editCommentAjax.bim",
		        type: "post",
		        data: { 
		        		"idx" : idx, 
		        		"contents" : editCommentContents,
						"writeId" : "${sessionScope.loginInfo.id}"
						},
		        success : function(data){
		         if ( data.result == "success") {
					console.log('댓글 수정 성공');
				}else {
					alert("댓글 수정 실패");
				}
		         
		        },
		        error : function(error){
		        	alert("댓글 수정 실패");
		        },
		        complete : function(){
					getCommentList();
		        }
		    });
	});
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


/* 
	
function editCommentAction(idx){
	var selectComment= $('p[data-idx='+idx+']');
	var selectCommentValue = selectComment.text();
	
	var beforeComment = $('#editCommentContents[data-idx='+idx+']');
	var beforeCommentValue = beforeComment.val();
	
	console.log(selectCommentValue);
	console.log(beforeCommentValue);
};

	$("#editCommentInit").on('click',function(){
		var idx = $(this).attr('data-idx');
		var listIdx = $(this).attr('data-list-idx');
		
		alert(idx);
		alert(listIdx);
		
		var editDiv = $('span[class=comment_contents][data-idx='+idx+']');
		editDiv.show();
 		
		var selectComment= $('span[class=comment_contents][data-idx='+idx+']');
		var selectCommentValue = selectComment.text();
		console.log("선택 댓글: "+selectCommentValue) 
		
		var beforeComment = $('#editCommentContents[data-idx='+idx+']');
		var beforeCommentValue = beforeComment.val();
		beforeComment.focus();
		console.log(selectCommentValue);
		console.log(beforeCommentValue);
	});
	
function editCommentInit(idx) {
	var editDiv = $('div[id=editDiv][data-idx='+idx+']');
	editDiv.show();
	
	var selectComment= $('p[data-idx='+idx+']');
	var selectCommentValue = selectComment.text();
	
	var beforeComment = $('#editCommentContents[data-idx='+idx+']');
	var beforeCommentValue = beforeComment.val();
	beforeComment.focus();
	console.log(selectCommentValue);
	console.log(beforeCommentValue);
}

function editComment(idx){
	
	 $.ajax({
	        url : "../comment/editCommentAjax.bim",
	        type: "post",
	        data: { 
	        		"idx" : idx, 
	        		"contents" : $("#editCommentContents").val(),
					"writeId" : "${sessionScope.loginInfo.id}"
					},
	        success : function(data){
	         if ( data.result == "success") {
				editCommentAction(idx);
			}else {
				alert("댓글 수정 실패");
			}
	         
	        },
	        error : function(error){
	        	alert("댓글 수정 실패");
	        },
	        complete : function(){
				getCommentList();
	        }
	    });
	 
}

function deleteComment(idx){
	 $.ajax({
	        url : "../comment/deleteCommentAjax.bim",
	        type: "post",
	        data: { 
	        		"idx" : idx,
					"writeId" : "${sessionScope.loginInfo.id}"
					},
	        success : function(data){
	         if ( data.result == "success") {
				console.log("댓글 삭제 완료");
			}else {
				alert("댓글 삭제 실패");
			}
	         
	        },
	        error : function(error){
	        	alert("댓글 삭제 실패");
	        },
	        complete : function(){
				getCommentList();
	        }
	    });
	 
}
  */
</script>
</head>
<body>

	<div class="comment_title">
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
			<!-- 			<input type="text" class="form-control" id="writeCommentContents" placeholder="댓글을 입력하세요" > -->
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
		<c:if test="${!empty sessionScope.loginInfo}">
			댓글 작성
<!-- 			<input type="text" class="form-control" id="writeCommentContents" placeholder="댓글을 입력하세요" > -->
			<textarea rows="" cols="5px" class="form-control" id="writeCommentContents" placeholder="댓글을 입력하세요" ></textarea>
			<input type="button" id="writeComment" value="댓글작성" class="btn btn-primary">
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