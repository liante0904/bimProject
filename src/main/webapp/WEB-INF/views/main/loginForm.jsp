<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/navbar-header.jsp" %>
<script src="../resources/js/member.js"></script>
<script src="../resources/js/common.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
});
</script>
<title>로그인 페이지</title>
</head>
<body>
<div class="container">
	<h1>로그인</h1>
	<h2> 로그인 상태 : ${msg} ${sessionScope.msg}</h2>
	<form action="${pageContext.request.contextPath}/main/login.bim" method="POST">
		<pre>
ID:	<input type="text" id="id" name="id" class="form-control">
PASSWORD: <input type="password" id="password" name="password" class="form-control">
		</pre>
		<div class="container">
			<div class="row">
				<div class="col-md-6"><button type="button" class="btn btn-default btn-block" onclick="handleTo('findId')">아이디 찾기</button></div>
				<div class="col-md-6"><button type="button" class="btn btn-default btn-block" onclick="handleTo('findPassword')">비밀번호 찾기</button></div>
			</div>
			<div class="row">
				<button type="submit" value="로그인" class="btn btn-lg btn-primary btn-block">로그인</button>
			</div>
		</div>

	</form>
</div>
</body>
</html>