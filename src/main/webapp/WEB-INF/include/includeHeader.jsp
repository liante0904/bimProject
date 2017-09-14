<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<meta charset="UTF-8">
<script type="text/javascript">
var localhost = '${pageContext.request.contextPath }';
</script>

<body>
<div style="background-color:#aaaa00;height:150px;">

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
</div>

</body>