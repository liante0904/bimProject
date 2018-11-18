/*viewForm*/
$(document).ready(function(){

}); 

$(function(){
	var idParam = "?" + "id=" + getParameters('id');
	var param =  idParam +"&num=" + getParameters('num');
	$("#edit-form").on('click',function() {
    		location.href="../board/editForm.bim"+param;
	});

	$("#delete-submit").on('click',function() {
		if (confirm("정말로 게시물을 삭제 하시겠습니까?")) 
		location.href="../board/deleteArticle.bim"+param;
	});
	
	$("#edit-submit").on("click", function(e) {
		e.preventDefault();
		$("form").submit();
	});
	
});

