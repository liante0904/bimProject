<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="#">
                        Start Bootstrap
                    </a>
                </li>
                <li>
                    <a href="#">About</a>
                </li>
                <li>
                    <a href="#">Services</a>
                </li>
                <li>
                    <a href="#">Contact</a>
                </li>
                <!-- 게시판 세팅 -->
   				<c:forEach items="${boardList}" var="board">
					<li class="<c:if test='${param.id eq board.id }'>active</c:if>">
						<a href="${pageContext.request.contextPath }/board/viewList.bim?id=${board.id}" id="${board.id}">${board.name}</a></li>
				</c:forEach>
				<!-- 로그인 메뉴 세팅 -->
				<c:if test="${empty sessionScope.loginInfo.id }">
					<!-- sessionScopre.id가 없으면 -->
					<li><a href="${pageContext.request.contextPath }/main/loginForm.bim">로그인</a></li>
					<li><a href="${pageContext.request.contextPath }/member/joinForm.bim">회원가입</a></li>
				</c:if>
			
				<c:if test="${not empty sessionScope.loginInfo.id }">
					<!-- sessionScopre.id가 있으면(로그인 성공시) -->
					<li class="active"><a href="${pageContext.request.contextPath }/member/editForm.bim"> ${sessionScope.loginInfo.name } 님</a></li>
					<li><a href="#" id="logout">로그아웃</a></li>
				</c:if>
            </ul>
        </div>
</body>
</html>