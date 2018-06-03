<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/mainHeader.jsp" %>

<title>Home</title>
<script type="text/javascript">

$(document).ready(function(){
	
}); 

$(function(){
	var	 paramIdValue = getParameters('id');
	var	 paramTitleValue = $("#title").val();
	var	 paramContentsValue = $("#contents").val();
	

	$("#edit").click(function() {
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
					alert("글 작성 성공");
					location.href =  document.referrer; 
				}
		        }
		    });

	});
	
});



</script>
</head>
<body>
<div class="container">
    <h1>editForm.jsp</h1>
     <form action="../board/editArticle.bim" method="post">
        <table class="table">
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
        </table>
 <input type="button" id="edit" value="수정" class="btn btn-primary"/>
 <input type="button" id="cancel" value="목록" class="btn btn-primary"/>
 
    </form>
</div>

 
</body>
</html>


