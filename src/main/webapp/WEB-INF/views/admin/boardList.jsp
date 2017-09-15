<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/includeHeader.jsp" %>
<title>Home</title>
 <style>
  table {
    width: 40%;
    border: 1px solid #444444;
  }
  th, td {
    border: 1px solid #444444;
  }
</style>
</head>
<body>
    <h1>게시판 관리</h1>

 
 
    <table>
        <thead>
            <tr>
                <th>회원번호</th>
                <th>아이디</th>
                <th>이름</th>
                <th>삭제 구분</th>
				<th>보기</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${boardList}" var="board">
                <tr>
                    <td>${board.idx}</td>
                    <td>${board.id}</td>
                    <td>${board.name}</td>
                    <td>${board.delGb}</td>
					<td><a href="${pageContext.request.contextPath }/test/boardView.bim?id=${board.id}">${board.id} 게시판 이동</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
 
 
</body>
</html>


