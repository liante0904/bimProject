<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- header 영역 작성중 -->
<script type="text/javascript"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<script type="text/javascript">

$(function(){
	$('#idChk').click(function(){
		var localhost= "${pageContext.request.contextPath }";
		
		 $.ajax({
		        url : localhost+"/member/memberIdCheck.bim",
		        type: "get",
		        data : { "id" : $("#id").val() },
		        success : function(data){
		         if ( data.result == "success") {
					alert("사용 가능한 아이디");
				}else {
					alert("중복된 아이디");
				}
		        }
		    });
		
		
		
		
		
		
		});
	
	
	
});



</script>

</head>
<body>

<h1>joinForm</h1>



 <form action="${pageContext.request.contextPath }/member/joinSubmit.bim"  method="POST">

  <div>
    <pre>
아이디 :  <input type="text" id="id" name="id"><span id="idchk"></span> <input type="button" id="idChk" value="중복체크" > 
패스워드 <input type="password" name="password" >
이름 <input type="text" name="name" >
E - MAIL<input type="text" name="email">
휴대전화 <input type="text" name="phone" />
주소 <input type="text" name="address" />
출석교회 <input type="text" name="church" />
 

    </pre>
  </div>

   <div>
   <input type="button" value="취소" onclick="javascript:window.close()" style="align:center">
   <input type="submit" value="가입" id="join" />

  </div>
 </form>
 
</body>
</html>