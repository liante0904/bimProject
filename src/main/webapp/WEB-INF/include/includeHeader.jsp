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
	
    $("#test").click(function() {// ajax 페이지 이동  test
		$.ajax({
	        type : "GET",
	        url : "${pageContext.request.contextPath }/board/viewList.bim?id=test2",
//	        url : "/test/test.bim",
	        dataType : "text",
	        error : function(){
	            alert(' 실패!!');
	        },
	        success : function(data){
	           	alert('성공');
	           	$("#context").html(data);
	        }

		});
     });
    
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


var localhost = '${pageContext.request.contextPath }';

	var getParameters = function (paramName) {
	    // 리턴값을 위한 변수 선언
	    var returnValue;

	    // 현재 URL 가져오기
	    var url = location.href;

	    // get 파라미터 값을 가져올 수 있는 ? 를 기점으로 slice 한 후 split 으로 나눔
	    var parameters = (url.slice(url.indexOf('?') + 1, url.length)).split('&');

	    // 나누어진 값의 비교를 통해 paramName 으로 요청된 데이터의 값만 return
	    for (var i = 0; i < parameters.length; i++) {
	        var varName = parameters[i].split('=')[0];
	        if (varName.toUpperCase() == paramName.toUpperCase()) {
	            returnValue = parameters[i].split('=')[1];
	            return decodeURIComponent(returnValue);
	        }
	    }
	};
	// 파라미터 호출 예시 (id) console.log(getParameters('id'));
	

</script>

<body>
<div style="background-color:#aaaa00;height:150px;">


<c:if test="${empty sessionScope.loginInfo.id }">  <!-- sessionScopre.id가 없으면 -->
<a href="${pageContext.request.contextPath }/main/loginForm.bim">로그인</a>
<a href="${pageContext.request.contextPath }/member/joinForm.bim">회원가입</a>
</c:if>

<c:if test="${not empty sessionScope.loginInfo.id }"> <!-- sessionScopre.id가 있으면(로그인 성공시) -->
	${sessionScope.loginInfo.name } 님 
	<a href="#" id="logout">로그아웃</a>
</c:if>



<a href="${pageContext.request.contextPath }/admin/admin.bim">관리자 페이지(예시)</a>
<a href="http://www.bridgeimpact.com/">이전 BIM 페이지</a>
<a href="#" id="test">ajax page test</a>
<a href="${pageContext.request.contextPath }/test/171002">file upload test</a>

<!-- 
<a href="#this">사역원 소개</a>
<a href="#this">캠프</a>
<a href="#this">교육 세미나</a>
<a href="#this">등록안내 신청서</a>

 -->

<ul id="board"></ul>




</div>

<div id="dataArea"></div>


</body>