<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/include/mainHeader.jsp" %>
<%@ include file="/WEB-INF/include/include.jsp" %>
<!-- header 영역 작성중 -->
<script type="text/javascript"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<script type="text/javascript">
$(document).ready(function(){
	var idCheck
	
	$("#id").keyup(function(){
		if($(this).val().length > 2){
			var id = $(this).val();
			console.log(id);
			 $.ajax({
			        url : "${pageContext.request.contextPath}/member/checkMemberIdAjax.bim",
			        type: "get",
			        data : { "id" : id },
			        success : function(data){
			         if ( data.result == "success") {
						console.log("사용 가능한 아이디");
						idCheck = 1;
						$("#result").html("사용 가능한 아이디");
					}else {
						console.log("중복 아이디");
						idCheck = 0;
						$("#result").html("중복된 아이디");
					}
			        }
			    });
			
		}
	});
	
	$('#join').on('click', function(){
		if (idCheck) {
			alert('true');
		}else{
		alert('false');
		return false;
		}
		alert("hello");
		$('form').submit();
	});
	
	
});
$(function(){

	$('#idChk').on('click',function(){
		
		 $.ajax({
		        url : "${pageContext.request.contextPath }/member/memberIdCheck.bim",
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
아이디 :  <span id="result">아이디를 입력해주세요.</span> <input type="text" id="id" name="id" class="form-control"> 중복 체크 결과: <span id="result">아이디를 입력해주세요.</span> <!--  <input type="button" id="idChk" value="중복체크" >  -->
패스워드 <input type="password" name="password"  class="form-control">
이름 <input type="text" name="name"  class="form-control">
E - MAIL<input type="text" name="email" class="form-control">
휴대전화 <input type="text" name="phone"  class="form-control"/>
주소 <input type="text" name="address"  class="form-control"/>
출석교회 <input type="text" name="church"  class="form-control"/>

    </pre>
  </div>

   <div>
   <input type="button" value="취소" onclick="javascript:window.close()" style="align:center"  class="btn btn-lg btn-primary btn-block"/>
   <input type="button" value="가입" id="join"  class="btn btn-lg btn-primary btn-block"/>

  </div>
 </form>
 
</body>
</html>