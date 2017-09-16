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

<p>
<a href="${pageContext.request.contextPath }/admin/memberList.bim">회원관리 (회원리스트, 회원가입설정)</a>
<a href="${pageContext.request.contextPath }/admin/boardList.bim">홈페이지 관리(게시판 관리)</a>
<a href="#this">메인 사진 등록 시스템(메인 사진 등록 관리)</a>
<a href="#this">온라인 신청서</a>
<a href="#this">온라인 신청서 생성 및 관리</a>


<a href="http://www.bridgeimpact.com/">이전 BIM 페이지</a>
</p>

<p>

<a href="${pageContext.request.contextPath }/test/writeForm.bim">통합 글쓰기</a>
</p>

</div>

</body>