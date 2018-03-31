<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/mainHeader.jsp" %>
<%@ include file="/WEB-INF/include/common.jsp" %>
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
		        url : "${pageContext.request.contextPath }/board/writeArticle.bim",
		        type: "post",
		        data : { 
		        	"boardName" : paramIdValue,
		        	"title" : paramTitleValue,
		        	"contents" : paramContentsValue
		        },
		        success : function(data){
		         if ( data.result == "success") {
		     		location.href=  "${pageContext.request.contextPath }/board/viewList.bim?id="+paramIdValue;
				}
		        }
		    });
		 
		 */ 
		
	});

	
	$("#writelist").click(function() {
		if (confirm("정말로 글쓰기를 취소 하시겠습니까?")) {
     		location.href=  "${pageContext.request.contextPath }/board/viewList.bim?id="+paramIdValue;
		}
	});

});

</script>
</head>
<body>
<div class="container">
    <h1>writeForm.jsp</h1>
     <form id="writeForm" action="${pageContext.request.contextPath }/board/writeArticle.bim" method="post" enctype="multipart/form-data">
        <table>
            <tbody>
                <tr>
                    <th>제목</th>
                    <td><input type="text" id="title" name="title"  class="form-control"/></td>
                </tr>
                
                <tr>
					<th>내용</th>                   
                       <td> <textarea rows="20" cols="100"  id="contents" name="contents" class="form-control"></textarea></td>
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
 <input type="button" id="writelist" value="목록"  class="btn btn-lg btn-primary btn-block"/>
 <input type="hidden" id="boardName" name="boardName" value="${param.id }"/>
 
    </form>
</div>

 
</body>
</html>


