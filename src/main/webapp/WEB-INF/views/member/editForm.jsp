<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/include/includeHeader.jsp" %>
<!-- header 영역 작성중 -->
<script type="text/javascript"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
 

<!-- jQuery -->
<script type="text/javascript">

$(document).ready(function(){ 

/* 	
	$("#edit").click(function() {
		location.href="${pageContext.request.contextPath }/member/editSubmit.bim";
	})
	
	 */
	$("#delete").click(function() {
		location.href="${pageContext.request.contextPath }/member/deleteSubmit.bim";
	})

	
}); 




</script>

</head>
<body>

<h1>editForm</h1>



 <form action="${pageContext.request.contextPath }/member/editSubmit.bim"  method="POST" >

  <div>
    <pre>
아이디 :  <input type="text" name="id" readonly="readonly" value="${sessionScope.loginInfo.id}"> 
패스워드 <input type="password" name="password" value="">
이름 <input type="text" name="name" readonly="readonly" value="${sessionScope.loginInfo.name}">
E - MAIL<input type="text" name="email" value="${sessionScope.loginInfo.email}">
휴대전화 <input type="text" name="phone" value="${sessionScope.loginInfo.phone}"/>
주소 <input type="text" name="address" value="${sessionScope.loginInfo.address}" />
출석교회 <input type="text" name="church" value="${sessionScope.loginInfo.church}"/>
 

    </pre>
  </div>

   <div>
   <input type="button" value="취소" onclick="javascript:window.close()" style="align:center">
   <input type="submit" value="수정" id="edit" />

  </div>
  
     <div>
   <input type="button" value="탈퇴" id="delete" />

  </div>
 </form>
 
</body>
</html>