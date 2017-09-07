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

	
}); 



</script>
</head>
<body>
    <h1>boardList.jsp</h1>
 
    <table>
        <thead>
            <tr>
                <th>글번호</th>
                <th>제목</th>
                <th>조회수</th>
				<th>작성자</th>
                <th>삭제여부</th>
                <th>날짜</th>
            </tr>
        </thead>
        <tbody>
         
            <c:forEach items="${boardList}" var="board">
                <tr>
                    <td>${board.idx}</td>
                    <td><a href="${pageContext.request.contextPath }/board/boardView.bim?num=${board.idx}">${board.title}</a></td>
                    <td>${board.hitCnt}</td>
                    <td>${board.writeId}</td>
                    <td>${board.delGb}</td>
                    <td>${board.writeDt}</td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
 
 <input type="button" id="write" value="글쓰기"/>
 
</body>
</html>


