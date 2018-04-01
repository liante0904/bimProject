<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/include/mainHeader.jsp" %>
<!-- header 영역 작성중 -->
<script type="text/javascript"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
 

<!-- jQuery -->
<script type="text/javascript">

$(document).ready(function(){ 


	$("#edit").click(function() {
		location.href="../member/editSubmit.bim";
	})
	
	 
	$("#delete").click(function() {
		location.href="../member/deleteSubmit.bim";
	})

	
}); 




</script>

</head>
<body>

<h1>editForm</h1>



 <form action="../member/editSubmit.bim"  method="POST" >

  <div>
    <pre>
아이디 :  <input type="text" name="id" readonly="readonly" value="${sessionScope.loginInfo.id}" class="form-control"> 
패스워드 <input type="password" name="password" value="" class="form-control">
이름 <input type="text" name="name" readonly="readonly" value="${sessionScope.loginInfo.name}" class="form-control">
E - MAIL<input type="text" name="email" value="${sessionScope.loginInfo.email}" class="form-control">
휴대전화 <input type="text" name="phone" value="${sessionScope.loginInfo.phone}" class="form-control"/>
주소 <input type="text" name="address" value="${sessionScope.loginInfo.address}" class="form-control"/>
출석교회 <input type="text" name="church" value="${sessionScope.loginInfo.church}" class="form-control"/>
 
    </pre>
  </div>

   <div>
	   <input type="button" value="취소" onclick="javascript:window.close()" style="align:center" class="btn btn-lg btn-primary btn-block"/>
	   <input type="button" value="수정" id="edit" class="btn btn-lg btn-primary btn-block"/>
	   <input type="button" value="탈퇴" id="delete" class="btn btn-lg btn-primary btn-block"/>
  </div>
 </form>
 
</body>
</html>