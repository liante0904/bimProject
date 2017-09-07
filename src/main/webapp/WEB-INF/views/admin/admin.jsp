<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/includeHeader.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>this is admin.jsp </h1>

<a href="${pageContext.request.contextPath }/member/memberList.bim">회원관리 (회원리스트, 회원가입설정)</a>
<a href="#this">홈페이지 관리(게시판 관리)</a>
<a href="#this">메인 사진 등록 시스템(메인 사진 등록 관리)</a>
<a href="#this">온라인 신청서</a>
<a href="#this">온라인 신청서 생성 및 관리</a>


<a href="http://www.bridgeimpact.com/">이전 BIM 페이지</a>


</body>
</html>