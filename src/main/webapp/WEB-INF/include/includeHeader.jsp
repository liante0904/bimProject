<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<meta charset="UTF-8">
<script type="text/javascript">
var localhost = '${pageContext.request.contextPath }';

$(document).ready(function(){
	getMenuInfo();
/* 	
	var sessionInitCheck = sessionStorage.getItem("boardList");
	console.log(sessionInitCheck);
	
	if(sessionInitCheck == null){
		$.ajax({
	        type : "GET",
	        url : "/getBoardList.bim",
	        dataType : "JSON",
	        error : function(){
	            alert('통신실패!!');
	        },
	        success : function(data){
	            // alert("통신데이터 값 : " + data) ;
	            $("#dataArea").html(data);
	          //  var jsonObj = JSON.parse(data);
	          sessionStorage.setItem("boardList", data);
	            alert(data);
	            console.log(data[0].idx);
	            var session = sessionStorage.getItem("boardList");
	             console.log("세션 테스트"+session);
	             initBoard(data);
	        }

		});
		
	}
 */
	function getMenuInfo() {
		$.get(localhost + "/getBoardList.bim", function(data, status) {
			var str = "";
			$(data).each(
				function() {
					str += "<li><a href='" + localhost + "/board/viewList.bim?id=" + this.id + "'>" + this.name + "</a></li>";
			});
			$('#board').html(str);
//			alert("Data: " + data + "\nStatus: " + status);
		});
	}
 
	function initBoard(data){
		alert("도착");
		$("#board").append("<b>Hello Test</b>");
	} 
	
	$("#logout").click(function(){
		$.ajax({
	        type : "GET",
	        url : "/logout.bim",
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
<div style="background-color:#aaaa00;height:150px;">


<c:if test="${empty sessionScope.loginInfo.id }">  <!-- sessionScopre.id가 없으면 -->
<a href="${pageContext.request.contextPath }/main/loginForm.bim">로그인</a>
<a href="${pageContext.request.contextPath }/member/joinForm.bim">회원가입</a>
</c:if>

<c:if test="${not empty sessionScope.loginInfo.id }"> <!-- sessionScopre.id가 있으면(로그인 성공시) -->
	${sessionScope.loginInfo.name } 님 
	<a href="#" id="logout">로그아웃</a><br/>
</c:if>



<a href="${pageContext.request.contextPath }/board/boardList.bim">게시판</a>
<!-- 
<a href="#this">사역원 소개</a>
<a href="#this">캠프</a>
<a href="#this">교육 세미나</a>
<a href="#this">등록안내 신청서</a>

 -->

<div id="board"></div>

<a href="${pageContext.request.contextPath }/admin/admin.bim">관리자 페이지(예시)</a>


<a href="http://www.bridgeimpact.com/">이전 BIM 페이지</a>



</div>

<div id="dataArea"></div>


</body>