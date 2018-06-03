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
	$("#write").on("click", function(e) {
		e.preventDefault();
		$("form").submit();
		
		/* 
		var paramIdValue = getParameters('id');
		var paramTitleValue = $("#title").val();
		var paramContentsValue = $("#contents").val();
		
		 $.ajax({
		        url : "../board/writeArticle.bim",
		        type: "post",
		        data : { 
		        	"boardId" : paramIdValue,
		        	"title" : paramTitleValue,
		        	"contents" : paramContentsValue
		        },
		        success : function(data){
		         if ( data.result == "success") {
		     		location.href=  "../board/viewList.bim?id="+paramIdValue;
				}
		        }
		    });
		 
		 */ 
		
	});



});

</script>
</head>
<body>
<div class="container">
    <h1>writeForm.jsp</h1>
     <form id="writeForm" action="../board/writeArticle.bim" method="post" enctype="multipart/form-data">
        <table class="table">
            <tbody>
                <tr>
                    <th>제목</th>
                    <td><input type="text" id="title" name="title"  class="form-control"/></td>
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
                <tr>
                <th rowspan="5">첨부파일</th>
                <td><input type="file" name="files1" class="form-control"></td>
                </tr>
                <tr>
                		<td><input type="file" name="files2" class="form-control"></td>
                </tr>
                <tr>
                		<td><input type="file" name="files3" class="form-control"></td>
                </tr>
                <tr>
                		<td><input type="file" name="files4" class="form-control"></td>
                </tr>
            </tbody>
        </table>
 <input type="button" id="write" value="글쓰기"  class="btn btn-lg btn-primary btn-block"/>
 <input type="button" id="cancel" value="목록"  class="btn btn-lg btn-primary btn-block"/>
 <input type="hidden" id="boardId" name="boardId" value="${param.id }"/>
 
    </form>
</div>

 
</body>
</html>


