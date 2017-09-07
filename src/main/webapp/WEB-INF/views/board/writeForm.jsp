<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/includeHeader.jsp" %>
<title>Home</title>
<script type="text/javascript">

$(document).ready(function(){ 

	$("#write").click(function() {
		location.href="${pageContext.request.contextPath }/board/writeForm.bim";
	})


	$("#list").click(function() {
		if (confirm("정말로 글쓰기를 취소 하시겠습니까?")) {
		location.href="${pageContext.request.contextPath }/board/boardList.bim";
			
		}
	})

	
}); 



</script>
</head>
<body>
    <h1>writeForm.jsp</h1>
     <form action="${pageContext.request.contextPath }/board/boardWrite.bim" method="post">
        <table class="board_view">
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
 <input type="submit" value="글쓰기"/>
 <input type="button" value="목록"/>
 
    </form>


 
</body>
</html>


