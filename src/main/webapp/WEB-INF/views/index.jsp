<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/mainHeader.jsp" %>
<%@ include file="/WEB-INF/include/leftSide.jsp" %>
<title>Insert title here</title>
</head>
<h1>this is index.jsp </h1>
<body>

<div id="context"></div>


            <c:forEach items="${boardList}" var="board" >
                <tr>
                    <td>${board.idx}</td>
                    <td>${board.id}</td>
                    <td>${board.name}</td>
                    <td>${board.delGb}</td>
                </tr>
                
            </c:forEach>

</body>
</html>