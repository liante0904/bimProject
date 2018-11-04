<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/navbar-header.jsp" %>
<title>메인 페이지</title>
</head>
<body>

<div class="container">
<h1>메인 페이지</h1>
<a href="../member/editForm.bim">회원정보 수정,탈퇴</a>
<a href="../board/boardList.bim">게시판</a>
<h2> 로그인 상태 : ${msg}</h2>


<h3>로그인 검증</h3>
<h4>아이디 : ${sessionScope.loginInfo.id}</h4>
<h4>이름 : ${sessionScope.loginInfo.name}</h4>
</div>
</body>
</html>