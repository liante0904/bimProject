<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<%@ include file="/WEB-INF/include/mainHeader.jsp" %>
<%@ include file="/WEB-INF/include/include.jsp" %>
<!-- header 영역 작성중 -->
<script type="text/javascript"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<script type="text/javascript">
$(document).ready(function(){
	var idCheck = 0;
	var passwordCheck = 0;
	var password2Check = 0;
	
	
	$("#id").focusout(function(){

		var id = $(this).val();
		var idResult = $("#idResult");
		
		if($(this).val().length === 0){
			idResult.html("아이디를 입력해주세요.");
		}else{
			
			 $.ajax({
			        url : "${pageContext.request.contextPath}/member/checkMemberIdAjax.bim",
			        type: "get",
			        data : { "id" : id },
			        success : function(data){
			         if ( data.result == "success") {
						idCheck = 1;
						idResult.css("color","green");
					}else {
						idCheck = 0;
						idResult.css("color","red");
					}

						console.log(id);
						console.log(data.resultMsg);
						idResult.html(data.resultMsg);
			         
			        }
			    });
		}
	});

$('#password').focusout(function() {
	//TODO 패스워드 조건추가

	passwordCheck = $(this).val();
	$('#passwordResult').html("패스워드가 너무짧습니다.");
});
	 $('#password2').focusout(function() {
		password2Check = $(this).val();
		
		if (passwordCheck === password2Check) {
			$('#passwordResult').html("패스워드가 일치합니다.");
		}else{
			$('#passwordResult').html("패스워드가 일치하지 않습니다.");
				
		}
		
	})
	 
	$('#join').on('click', function(){
		if (idCheck) {
//			alert('true');
		}else{
//		alert('false');
		return false;
		}
		alert("hello");
		$('form').submit();
		
	});
	
	
});

$(function(){


	$('form').submit(
	function() {
		alert("area");
	}		
	);
	
});

</script>

</head>
<body>

<h2>회원가입 페이지</h2>


<div class="container joinForm">
 <form action="../member/joinSubmit.bim"  method="POST">
	<div class="form-group">
		<input type="text" id="id" name="id" class="form-control" placeholder="아이디" /> <span id="idResult">아이디를 입력해주세요.</span>  <!--  <input type="button" id="idChk" value="중복체크" >  -->
	</div>
	<div class="form-group">
		<input type="password" name="password" id="password" class="form-control" placeholder="패스워드" />
		<input type="password" name="password" id="password2" class="form-control" placeholder="패스워드 확인" /> <span id="passwordResult"></span>  
	</div>
	<div class="form-group">	
		<input type="text" name="name"  class="form-control" placeholder="이름" />
		<input type="text" name="email" class="form-control" placeholder="E-MAIL" />
		<input type="text" name="phone"  class="form-control" placeholder="휴대전화" />
		<input type="text" name="address"  class="form-control"  placeholder="주소" />
		<input type="text" name="church"  class="form-control"  placeholder="출석교회" />
	</div>
   <div>
   <input type="submit" value="가입" id="join"  class="btn btn-lg btn-primary btn-block" disabled="disabled" />
   <input type="button" value="취소" id="joinFormCancel" style="align:center" class="btn btn-lg btn-primary btn-block" >

  </div>
 </form>
</div>
</body>
</html>