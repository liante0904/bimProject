$(document).ready(function() {

});

$(function(){
	$("#writeComment").on('click',function() {
		var writeCommentContentsForm = $("#writeCommentContents");
		var commentContents = writeCommentContentsForm.val();
		var parentIdx = $(this).attr('data-parentIdx');
		var writeId = $(this).attr('data-writerId');
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
		        		"parentIdx" : parentIdx,
						"contents" : commentContents,
						"writeId" : writeId
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