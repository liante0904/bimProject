<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/mainHeader.jsp" %>
<%@ include file="/WEB-INF/include/include.jsp" %>

<title>브리지 임팩트 - 글수정</title>
<script type="text/javascript">

$(document).ready(function(){

}); 

$(function(){
	$('form').validate({
		rules : {
			
			title: {
				required : true,
				minlength: 1,
				maxlength: 40
			},
			contents: {
				required : true,
				minlength: 3
			}
		},
		messages : {
			title:{
				required : "글 제목을 입력하세요",	
				minlength : "글 제목을 입력하세요",
				maxlength : "글 제목이 너무 길어요"
			},
			contents: {
				required : "본문을 입력하세요",
				minlength : "본문을 입력하세요"
			}
		},
	});
	
	
	var	 paramIdValue = getParameters('id');
	var	 paramTitleValue = $("#title").val();
	var	 paramContentsValue = $("#contents").val();
	

	$("#edit").on("click", function(e) {

		e.preventDefault();
		$("form").submit();
	/* 	
		var	 paramIdValue = getParameters('id');
		var	 paramTitleValue = $("#title").val();
		var	 paramContentsValue = $("#contents").val();
		 $.ajax({
		        url : "../board/editArticleAjax.bim",
		        type: "post",
		        data : { 
		        	"boardId" : paramIdValue,
		        	"title" : paramTitleValue,
		        	"contents" : paramContentsValue
		        },
		        success : function(data){
		         if ( data.result == "success") {
					location.href =  document.referrer; 
				}
		        }
		    });
	 */	 
	});
	
	
	
});



</script>
</head>
<body>
<div class="container editForm">
    <h1 class="mb-1">글수정</h1>
     <form action="../board/editArticle.bim" method="post">
<!--         <table class="table">
            <tbody>
                <tr>
                    <th>제목</th>
                    <td><input type="text" id="title" name="title" class="form-control" size="98" /></td>
                </tr>
                
                <tr>
					<th>내용</th>                   
  						<td> <textarea id="contents" name="contents" class="form-control"></textarea>
								<script>
					                // Replace the <textarea id="editor1"> with a CKEditor
					                // instance, using default configuration.
					                CKEDITOR.replace( 'contents' );
					            </script>
			            </td>
                </tr>
            </tbody>
        </table> -->
        <div class="container">
        		<div class="mt-2">
        			<input type="text" id="title" name="title" class="form-control" placeholder="제목을 입력하세요." />
        		</div>
        		<div class="mt-2">
        			<textarea id="contents" name="contents" class="form-control"></textarea>
					<script>
		                // Replace the <textarea id="editor1"> with a CKEditor
		                // instance, using default configuration.
		                CKEDITOR.replace( 'contents' );
		            </script>
        		</div>
			<div class="mt-2">
        			<input type="file" name="files1" class="form-control">
        			<input type="file" name="files2" class="form-control">
        			<input type="file" name="files3" class="form-control">
        			<input type="file" name="files4" class="form-control">
        			
        		</div>
        		<div class="mt-2">
			 <input type="button" id="cancel" value="목록" class="btn btn-default"/>
			 <input type="button" id="edit" value="수정" class="btn btn-primary pull-right"/>
        			
        		</div>
        </div>
 
    </form>
</div>

 
</body>
</html>


