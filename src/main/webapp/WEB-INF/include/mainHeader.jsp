<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/include/common.jsp" %>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/bootstrap/css/bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
var localhost = '${pageContext.request.contextPath }';

$(document).ready(function(){
//	getMenuInfo();
	
	function getMenuInfo() {
		$.get(localhost + "/getBoardList.bim", function(data, status) {
			var str = "";
			$(data).each(
				function() {
					str += "<li><a href='" + localhost + "/board/viewList.bim?id=" + this.id + "'>" + this.name + "</a></li>";
			});
			$('#board').prepend(str);
//			alert("Data: " + data + "\nStatus: " + status);
		});
	}

	
});
$(function(){
	

 
	function initBoard(data){
		alert("도착");
		$("#board").append("<b>Hello Test</b>");
	} 
	
	$("#logout").click(function(){
		$.ajax({
	        type : "GET",
	        url : "${pageContext.request.contextPath }/logout.bim",
	        dataType : "text",
	        error : function(){
	            alert('로그아웃 실패!!');
	        },
	        success : function(data){
	            if(confirm("로그아웃 하시겠습니까?") == true)
	             location.href="${pageContext.request.contextPath }/";
	        }

		});
	});
	
});
</script>

<body>
<div style="background-color:#aaaa00;height: 100px;">

<!-- 
<a href="#this">사역원 소개</a>
<a href="#this">캠프</a>
<a href="#this">교육 세미나</a>
<a href="#this">등록안내 신청서</a>

 -->
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div id="navbar" class="navbar-collapse collapse">
					<ul id="board" class="nav navbar-nav">
					
					


            <c:forEach items="${boardList}" var="board" >
<li><a href="${pageContext.request.contextPath }/board/viewList.bim?id=${board.id}">${board.name}</a></li>
                
            </c:forEach>

<c:if test="${empty sessionScope.loginInfo.id }">  <!-- sessionScopre.id가 없으면 -->
<li><a href="${pageContext.request.contextPath }/main/loginForm.bim">로그인</a></li>
<li><a href="${pageContext.request.contextPath }/member/joinForm.bim">회원가입</a></li>
</c:if>


<c:if test="${not empty sessionScope.loginInfo.id }"> <!-- sessionScopre.id가 있으면(로그인 성공시) -->
	<li><a href="#"> ${sessionScope.loginInfo.name } 님</a></li> 
	<li><a href="#" id="logout">로그아웃</a></li>
</c:if>


<li><a href="${pageContext.request.contextPath }/admin/admin.bim">관리자 페이지(예시)</a></li>
<li><a href="http://www.bridgeimpact.com/">이전 BIM 페이지</a></li>
<li><a href="#">test1</a></li>
<li><a href="#">test2</a></li>
					
					</ul>
				</div>
			</div>
		</nav>




	</div>

<div id="dataArea"></div>


</body>