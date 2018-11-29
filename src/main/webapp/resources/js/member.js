/*viewForm*/
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


function deleteFile(articleIdx, storedFileName, sessionLoginId) {
    console.log(articleIdx);
    console.log(storedFileName);
    console.log(sessionLoginId);
    var FileVO = {
        'articleIdx': articleIdx,
        'storedFileName': storedFileName,
        'creaId': sessionLoginId
    }

    $.ajax({
        url : "../file/deleteFile.bim",
        type: "POST",
        data:
            FileVO,
        success : function(data){
            if ( data.result == "success") {
                console.log("첨부파일 삭제 완료");
            }
        },
        error : function(error){
            alert("첨부파일 삭제 실패");
        },
        complete : function(){
            //getArticleFileList();
        }
    });

}

