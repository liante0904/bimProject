<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/includeHeader.jsp" %>
<%@ include file="/WEB-INF/include/adminHeader.jsp" %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h2>this is test/test.jsp</h2>

<c:forEach items="#{boardList}" var="board">
<button type="button" onclick="location.href='view.bim?id=${board.id}' ">${board.idx}번 게시판</button>
</c:forEach>
</body>
</html>