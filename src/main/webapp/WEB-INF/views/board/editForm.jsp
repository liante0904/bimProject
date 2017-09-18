<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/includeHeader.jsp" %>
<%@ include file="/WEB-INF/include/articleHeader.jsp" %>
<title>Home</title>
<script type="text/javascript">
function backPage(){
	var paramIdValue = getParameters('id');
	var URL = "${pageContext.request.contextPath }/board/viewList.bim?id="+paramIdValue;
	location.href= URL;
}


$(document).ready(function(){ 
	$("#edit").click(function() {
		
		
		var paramIdValue = getParameters('id');
		var paramTitleValue = $("#title").val();
		var paramContentsValue = $("#contents").val();
		
		
		 $.ajax({
		        url : "/board/editArticle.bim",
		        type: "post",
		        data : { 
		        	"boardName" : paramIdValue,
		        	"title" : paramTitleValue,
		        	"contents" : paramContentsValue
		        },
		        success : function(data){
		         if ( data.result == "success") {
					alert("글 작성 성공");
					backPage();
				}
		        }
		    });

	});


	$("#list").click(function() {
		if (confirm("정말로 글쓰기를 취소 하시겠습니까?")) {
		location.href="${pageContext.request.contextPath }/board/boardList.bim";
			
		}
	})

	
}); 



</script>
</head>
<body>
    <h1>editForm.jsp</h1>
     <form action="${pageContext.request.contextPath }/board/editArticle.bim" method="post">
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
 <input type="button" id="edit" value="수정"/>
 <input type="button" value="목록"/>
 
    </form>


 
</body>
</html>


