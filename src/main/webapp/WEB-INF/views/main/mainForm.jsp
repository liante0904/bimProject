<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/mainHeader.jsp" %>
<title>Insert title here</title>
</head>
<body>

<h1> mainForm</h1>
<a href="${pageContext.request.contextPath }/member/editForm.bim">회원정보 수정,탈퇴</a>
<a href="${pageContext.request.contextPath }/board/boardList.bim">게시판</a>
<h2> 로그인 상태 : ${msg}</h2>


<h3>로그인 검증</h3>
<h4>아이디 : ${sessionScope.loginInfo.id}</h4>
<h4>이름 : ${sessionScope.loginInfo.name}</h4>
</body>
</html>