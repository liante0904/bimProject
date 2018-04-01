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
    <h1>editForm.jsp</h1>
     <form action="../board/editArticle.bim" method="post">
        <table>
            <tbody>
                <tr>
                    <th>제목</th>
                    <td><input type="text" id="title" name="title" class="wdp_90" size="98"/></td>
                </tr>
                
                <tr>
					<th>내용</th>                   
                       <td> <textarea rows="20" cols="100"  id="contents" name="contents"></textarea></td>
                </tr>
            </tbody>
        </table>
 <input type="button" id="edit" value="수정" class="btn btn-primary"/>
 <input type="button" id="cancel" value="목록" class="btn btn-primary"/>
 
    </form>


 
</body>
</html>


