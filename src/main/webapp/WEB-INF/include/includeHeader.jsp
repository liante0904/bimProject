<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<meta charset="UTF-8">
<script type="text/javascript">
var localhost = '${pageContext.request.contextPath }';

$(document).ready(function(){
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
	            
	        }

		});
		
	}

	

	 
});
</script>

<body>
<div style="background-color:#aaaa00;height:150px;">

<a href="${pageContext.request.contextPath }/main/loginForm.bim">로그인</a>
<a href="${pageContext.request.contextPath }/member/joinForm.bim">회원가입</a>
<a href="${pageContext.request.contextPath }/board/boardList.bim">게시판</a>
<!-- 
<a href="#this">사역원 소개</a>
<a href="#this">캠프</a>
<a href="#this">교육 세미나</a>
<a href="#this">등록안내 신청서</a>

 -->
 			 <c:set var="board" value="${boardList }"/>
 			 <c:out value="${board}" default="NULL"/>
             <c:forEach items="${board}" var="board">
             <a href="${pageContext.request.contextPath }/board/view.bim?id=${board.id}">${board.name}</a>
             </c:forEach>
             
<%--              
             <c:set var="board" value="${session.boardList }"></c:set>
             <a href="${pageContext.request.contextPath }/board/view.bim?id=${board.id}">${board.name}</a>
 --%>

<a href="#this">통합게시판(?)</a>
<a href="${pageContext.request.contextPath }/admin/admin.bim">관리자 페이지(예시)</a>


<a href="http://www.bridgeimpact.com/">이전 BIM 페이지</a>
<h4>아이디 : ${sessionScope.boardList}</h4> 
<h4>테스트: ${sessionScope.test}</h4>



</div>

<div id="dataArea"></div>


</body>