<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/mainHeader.jsp" %>
<%-- <%@ include file="/WEB-INF/include/adminHeader.jsp" %> --%>
<%@ include file="/WEB-INF/include/common.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

$(document).ready(function() {
/*
	getAdminMenuList();
	
function getAdminMenuList() {
	$.ajax({
        type : "GET",
        url : "${pageContext.request.contextPath }/comment/getC11ommentList.bim",
        data : data,
        dataType : "html",
        success : function(data){
           	$("#commentContent").html(data);
        },
        error : function(data){
            alert(' 실패!!');
        }
	});
	
}
 */
});
</script>
</head>
<body>

<h1>this is admin.jsp </h1>



<div style="background-color:#aaaa00;height:150px;">

<p>
<a href="${pageContext.request.contextPath }/admin/memberList.bim">회원관리 (회원리스트, 회원가입설정)</a>
<a href="${pageContext.request.contextPath }/admin/boardList.bim">홈페이지 관리(게시판 관리)</a>
<a href="#this">메인 사진 등록 시스템(메인 사진 등록 관리)</a>
<a href="${pageContext.request.contextPath }/order/viewList.bim">온라인 신청서</a>
<a href="#this">온라인 신청서 생성 및 관리</a>


<a href="http://www.bridgeimpact.com/">이전 BIM 페이지</a>
</p>

<p>

<a href="${pageContext.request.contextPath }/test/writeForm.bim">통합 글쓰기</a>
</p>

</div>
</body>
</html>