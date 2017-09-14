<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/includeHeader.jsp" %>
<title>Insert title here</title>
</head>
<body>
	<h1>로그인</h1>
	<h2> 로그인 상태 : ${msg} ${sessionScope.msg}</h2>
	<form action="${pageContext.request.contextPath}/main/login.bim" method="POST">
		<pre>
		id:		<input type="text" id="id" name="id"><br>
		 password:	<input type="password" id="password" name="password"><br> <input type="submit" value="로그인"> 
			
	</pre>
	</form>
</body>
</html>