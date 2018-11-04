<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="../resources/css/member/member.css">    <!-- member CSS -->
<%@ include file="/WEB-INF/include/navbar-header.jsp" %>
<!-- header 영역 작성중 -->
<script type="text/javascript"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
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
				required : "",
				minlength : ""
			},
			repassword: {
				required : "",
				equalTo : ""
			},
			name: {
				required : "필수 정보입니다.",
			},
			email: {
				required : "",		
				email : ""
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
	
	
	
	$("#id").focusout(function(){

		var userIdInput = $(this).val();
		var idResult = $("#idResult");
		
		if(userIdInput.length === 0){
			idResult.html("아이디를 입력해주세요.");
		}else if (userIdInput.length < 7) {
			idResult.html("아이디가 너무 짧습니다.");
		}else{

			 $.ajax({
			        url : "${pageContext.request.contextPath}/member/checkMemberIdAjax.bim",
			        type: "get",
			        data : { "id" : userIdInput },
			        success : function(data){
			         if ( data.result == "success") {
						idResult.css("color","green");
					}else {
						idResult.css("color","red");
					}

						console.log(userIdInput);
						console.log(data.resultMsg);
						idResult.html(data.resultMsg);
			         
			        }
			    });
		}
	});

$('#password').focusout(function() {

	passwordCheck = $(this).val();
	if (passwordCheck.length < 8) {
		$('#passwordResult').html("패스워드가 너무짧습니다.");
	}
});

	 $('#repassword').focusout(function() {
		 passwordCheck = $('#password').val();
		repasswordCheck = $(this).val();
		var passwordResult = $('#passwordResult');
		if (passwordCheck === repasswordCheck && passwordCheck != "") {
			if (repasswordCheck.length < 8) {
				passwordResult.css("color", "orange");
				passwordResult.html("패스워드가 일치하지만, 너무 짧습니다.");
				
			}else{
			passwordResult.css("color", "green");
			passwordResult.html("패스워드가 일치합니다.");
			}
		}else{
			passwordResult.html("패스워드가 일치하지 않습니다.");
				
		}
		
	});
	 
	 $('#email').focusout(function() {
		var userEmailInput = $(this).val();
		var emailResult = $('#emailResult');
		
		if (userEmailInput.length == 0) {
			emailResult.css("color","red");
			emailResult.html("이메일을 입력해주세요.");
		}else {
			if (userEmailInput.indexOf('@')  > 0 ) {
			 $.ajax({
			        url : "${pageContext.request.contextPath}/member/checkMemberEmailAjax.bim",
			        type: "post",
			        data : { "email" : userEmailInput },
			        success : function(data){
			         if ( data.result == "success") {
				    		emailResult.css("color","green");
					}else {
						emailResult.css("color","red");
					}
				    		emailResult.html(data.resultMsg);
			        	 console.log("asads: "+data.resultMsg);

/* 						console.log(id);
						console.log(data.resultMsg);
						idResult.html(data.resultMsg); */
			         
			        }
			    });
		}else {
			emailResult.css("color","red");
			emailResult.html("올바른 이메일 형식을 입력해주세요.");
		}
			
		} 

	});
	/* 
	$('#join').on('click', function(e){
		$('form').validate();
        var MemberVO = $("form").serializeObject();
		for(key in MemberVO) {
		    console.log('key:' + key + ' / ' + 'value:' + MemberVO[key]);
			if (MemberVO[key] === "") {
				console.log(key + "항목이 비어있습니다." );
				$('#'+key).focus();
				e.preventDefault();
			    e.stopPropagation();
				return false;
			}
		}
		$('form').submit();


	});
 */		
});

					
				
</script>

</head>
<body>



<div class="container joinForm">
	<h2>회원가입 페이지</h2>
		 <form action="../member/joinSubmit.bim"  name="joinForm" id="joinForm" method="POST">
			<div class="form-group">
				<label for="id">ID</label>
				<input type="text" id="id" name="id" class="form-control" placeholder="아이디"  /> <span id="idResult">아이디를 입력해주세요.</span> 
			</div>
			<div class="form-group">
				<label for="password">Password</label>
				<input type="password" name="password" id="password" class="form-control" placeholder="패스워드" />
				<input type="password" name="repassword" id="repassword" class="form-control" placeholder="패스워드 확인" /> <span id="passwordResult">패스워드를 입력해주세요.</span>  
			</div>
			
			<div class="form-group">
				<div class="row">
					<div class="col-xs-4">
						<label for="name">Name</label>
						<input type="text" name="name"  id="name" class="form-control" placeholder="이름" />
					</div>
				</div>
			</div>
				
				<div class="row">
					<div class="col-xs-8">
						<label for="email">E-Mail</label>
						<input type="text" name="email" id="email" class="form-control" placeholder="E-MAIL" /> <span id="emailResult"> 이메일을 입력해주세요. </span>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-7">
						<label for="phone">Phone</label>
						<input type="text" name="phone" id="phone" class="form-control" placeholder="휴대전화" />
					</div>
				</div>
					
				<div class="form-group">
					<label for="address">Address</label>
					<input type="text" name="address" id="address" class="form-control"  placeholder="주소" />
				</div>
				
				<div class="form-group">
					<label for="church">Church</label>
					<input type="text" name="church" id="church" class="form-control"  placeholder="출석교회" />
				</div>
				
			   <div class="form-group">
				   <input type="submit" value="가입" id="join"  class="btn btn-lg btn-primary btn-block"   />
				   <input type="button" value="취소" id="joinFormCancel" style="align:center" class="btn btn-lg btn-primary btn-block" >
			  </div>
		 </form>
</div>
</body>
</html>