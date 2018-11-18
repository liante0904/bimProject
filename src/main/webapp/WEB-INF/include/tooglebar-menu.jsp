<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<div id="navbar" class="navbar-collapse collapse">
<ul id="board" class="nav navbar-nav">
	<c:forEach items="${boardList}" var="board">
		<li class="<c:if test='${param.id eq board.id }'>active</c:if>">
			<a href="${pageContext.request.contextPath }/board/viewList.bim?id=${board.id}" id="${board.id}">${board.name}</a></li>
	</c:forEach>

	<c:if test="${empty sessionScope.loginInfo.id }">
		<!-- sessionScopre.id가 없으면 -->
		<li><a
			href="${pageContext.request.contextPath }/main/loginForm.bim">로그인</a></li>
		<li><a
			href="${pageContext.request.contextPath }/member/joinForm.bim">회원가입</a></li>
	</c:if>

	<c:if test="${not empty sessionScope.loginInfo.id }">
		<!-- sessionScopre.id가 있으면(로그인 성공시) -->
		<li class="active"><a href="${pageContext.request.contextPath }/member/editForm.bim"> ${sessionScope.loginInfo.name } 님</a></li>
		<li><a href="#" id="logout">로그아웃</a></li>
	</c:if>

<c:if test="${sessionScope.loginInfo.id eq 'admin' }">
	<li><a href="${pageContext.request.contextPath }/admin/admin.bim">관리자 페이지(예시)</a></li>
	<li><a href="http://www.bridgeimpact.com/">이전 BIM 페이지</a></li>
             <li class="dropdown">
               <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
               <ul class="dropdown-menu" role="menu">
                 <li><a href="#">Action</a></li>
                 <li><a href="#">Another action</a></li>
                 <li><a href="#">Something else here</a></li>
                 <li class="divider"></li>
                 <li class="dropdown-header">Nav header</li>
                 <li><a href="#">Separated link</a></li>
                 <li><a href="#">One more separated link</a></li>
               </ul>
             </li>
        </c:if>
	</ul>
</div>