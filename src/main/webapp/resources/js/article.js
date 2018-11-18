/*viewForm*/
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
    		location.href="../board/editForm.bim"+param;
	});

	$("#delete").on('click',function() {
		if (confirm("정말로 게시물을 삭제 하시겠습니까?")) 
		location.href="../board/deleteArticle.bim"+param;
	});
	

	
});

