<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>this is index.jsp </h1>

<a href="${pageContext.request.contextPath }/main/loginForm.bim">로그인</a>
<a href="${pageContext.request.contextPath }/member/joinForm.bim">회원가입</a>
<a href="${pageContext.request.contextPath }/board/boardList.bim">게시판</a>
<a href="#this">사역원 소개</a>
<a href="#this">캠프</a>
<a href="#this">교육 세미나</a>
<a href="#this">등록안내 신청서</a>
<a href="#this">게시판(공지사항)</a>
<a href="#this">통합게시판(?)</a>
<a href="${pageContext.request.contextPath }/admin/admin.bim">관리자 페이지(예시)</a>


<a href="http://www.bridgeimpact.com/">이전 BIM 페이지</a>


<%-- 

<form action="${pageContext.request.contextPath }/login" method="post">
    <input type="text" name="id"/>
    <input type="password" name="password"/>
    <input type="submit" value="전송" />
</form>

 --%>
</body>
</html>