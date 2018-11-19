<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<!-- common jstl -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no,maximum-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="${pageContext.request.contextPath}/resources/favicon.ico"> <!--  favicon.ico -->
<!-- jquery.min -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>		<!-- jquery 1.11.2 -->
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>		<!-- Bootstrap core js(TODO locate footer) -->

<!-- bootstrap -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">    <!-- Bootstrap core CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css">    <!-- Bootstrap core CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/carousel.css">    <!-- Custom styles for this template -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/layout.css">    <!-- Bootstrap custom layout CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/sticky-footer.css">    <!-- bootstrap sticky-footer.css CSS -->
<!-- common.css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">    <!-- common CSS -->
<!-- sidebar test area end-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/simple-sidebar.css">
<!-- sidebar test area end-->

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/ie10-viewport-bug-workaround.js"></script>
<script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>	<!-- ckeditor -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script> <!-- jquery.validate -->
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<body>
<header class="navbar navbar-default navbar-fixed-top fix-navbar" role="navigation">
	<nav class="navbar navbar-fixed-top navbar-default">
		<div class="container">
			<div class="navbar-header">
				<button id="menu-toggle" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
	                <span class="sr-only">Toggle navigation</span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
				</button>
     			<a class="navbar-brand" href="${pageContext.request.contextPath}/">Bridge impact</a>
			</div>
			<%-- toogle bar menu --%>
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
						<li><a href="#" onclick="logout()">로그아웃</a></li>
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
		</div>
	</nav>	<!-- navbar navbar-inverse navbar-static-top -->
</header>
</body>