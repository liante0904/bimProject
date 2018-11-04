<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<link rel="stylesheet" href="../resources/css/member/member.css">    <!-- member CSS -->
<%@ include file="/WEB-INF/include/navbar-header.jsp" %>
<!-- header 영역 작성중 -->
<script type="text/javascript"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
 

<!-- jQuery -->
<script type="text/javascript">

$(document).ready(function(){ 
	$('form').validate({
		rules : {
			
			id: {
				required : true,
				minlength: 5
			},
			password: {
				required : true,
				minlength: 7
			},
			repassword: {
				required : true,
				equalTo : password
			},
			name: {
				required : true,
			},
			email: {
				required : true,	
				email : true
			},
			phone: {
				required : true,
			},
			address: {
				required : true,
			},
			church: {
				required : true,
			}
		},
		messages : {
			id:{
				required : "",	
				minlength : ""
			},
			password: {
				required : "패스워드를 입력하세요.",
				minlength : "패스워드가 너무 짧습니다."
			},
			repassword: {
				required : "패스워드를 입력하세요.",
				equalTo : "패스워드가 일치하지 않습니다."
			},
			name: {
				required : "필수 정보입니다.",
			},
			email: {
				required : "필수 정보입니다.",		
				email : "올바른 이메일 주소가 아닙니다."
			},
			phone: {
				required : "필수 정보입니다.",
			},
			address: {
				required : "필수 정보입니다.",
			},
			church: {
				required : "필수 정보입니다.",
			}
		},
/* 		
		submitHandler : function() {

		}
		 */
	});

	$("#edit").click(function() {
		document.editForm.action = "../member/editSubmit.bim"; 
	})
	
	 
	$("#delete").click(function() {
		var userInputPassword = $("#password").val();
		var userIdx = ${sessionScope.loginInfo.idx}
		
		 $.ajax({
		        url : "${pageContext.request.contextPath}/member/deleteSubmit.bim",
		        type: "post",
		        data : { "password" : userInputPassword },
		        success : function(data) {
		        	console.log("first :"+data.result);
		        	if (data.result == "success") {
			        	console.log("su :"+data.result);
		        		window.location.href = '${pageContext.request.contextPath}/';
					}else if(data.result == "error"){
			        	console.log("er : "+data.result);
			        	alert("회원 탈퇴시 올바른 패스워드를 입력해주세요.");
					}
				}
		    });
	})

}); 

function cancel() {
	if (confirm("취소 하시겠습니까?")) {
		history.back();
	}
}
</script>

</head>
<body>



<div class="container">
	<h1>회원 정보 수정</h1>
	 <form name="editForm" method="POST" >
	  <div class="form-group">
	  	<label for="id">ID</label>
		<input type="text" name="id" readonly="readonly" value="${sessionScope.loginInfo.id}" class="form-control" placeholder="아이디"> 
	  </div>
	  <div class="form-group">
	  	<label for="password">Password</label>
		<input type="password" name="password" id="password" value="" class="form-control" placeholder="패스워드">
		<input type="password" name="repassword" id="repassword" class="form-control" placeholder="패스워드 확인" /> <span id="passwordResult"></span>
	  </div>
  	  <div class="form-group">
  	  	<div class="row">
  	  		<div class="col-xs-4">
			  	<label for="name">Name</label>
				<input type="text" name="name" readonly="readonly" value="${sessionScope.loginInfo.name}" class="form-control" placeholder="이름">
  	  		</div>
  	  	</div>
	  </div>
	  
	  <div class="row">
	  	<div class="col-xs-7">
		  	<label for="email">E-Mail</label>
			<input type="text" name="email" value="${sessionScope.loginInfo.email}" class="form-control" placeholder="E-MAIL">
	  	</div>
	  </div>
	  
	  <div class="row">
	  	<div class="col-xs-7">
		  	<label for="phone">Phone</label>
			<input type="text" name="phone" value="${sessionScope.loginInfo.phone}" class="form-control" placeholder="휴대전화" />
	  	</div>
	  </div>
	  
	  <div class="form-group">
	  	<label for="address">Address</label>
		<input type="text" name="address" value="${sessionScope.loginInfo.address}" class="form-control" placeholder="주소" />
	  </div>
	  <div class="form-group">
	  	<label for="church">Church</label>
		<input type="text" name="church" value="${sessionScope.loginInfo.church}" class="form-control"/>
	  </div>
	
	   <div class="form-group">
		   <input type="button" value="취소" onclick="cancel();" style="align:center" class="btn btn-lg btn-primary btn-block"/>
		   <input type="submit" value="수정" id="edit" class="btn btn-lg btn-primary btn-block"/>
		   <input type="button" value="탈퇴" id="delete" class="btn btn-lg btn-primary btn-block"/>
	  </div>
	 </form>
 </div>
 
</body>
</html>