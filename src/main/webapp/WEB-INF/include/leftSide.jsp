<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 게시판 관리 기능에 의하여 게시판의 추가, 삭제, 변경 등에 대한 처리를 하는 javascript 파일 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/header.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css"/>
<script type="text/javascript" charset="utf-8">
	sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
</script>

<body>
<div style="background-color:#aa00aa;height:1000px;width:20%;float:left;"></div>
</body>