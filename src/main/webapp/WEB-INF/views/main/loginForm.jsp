<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/mainHeader.jsp" %>
<%@ include file="/WEB-INF/include/common.jsp" %>
<link rel="stylesheet" href="../resources/bootstrap/css/signin.css">    <!-- Custom styles for this template -->
<script type="text/javascript">
$(document).ready(function(){
	
});
</script>
<title>Insert title here</title>
</head>
<body>
	<h1>로그인</h1>
	<h2> 로그인 상태 : ${msg} ${sessionScope.msg}</h2>
	<form action="${pageContext.request.contextPath}/main/login.bim" method="POST">
		
		<pre>
ID:	<input type="text" id="id" name="id" class="form-control">
PASSWORD: <input type="password" id="password" name="password" class="form-control"> <input type="submit" value="로그인" class="btn btn-lg btn-primary btn-block"> 
		</pre>	

	</form>
</body>
</html>