<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>브리지 임팩트 사역원 입니다.</title>
    <link rel="stylesheet" href="../resources/css/member/member.css">    <!-- member CSS -->
    <%@ include file="/WEB-INF/include/navbar-header.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <script type="text/javascript">
        $(document).ready(function(){
            $('form').validate({
                rules : {

                    password: {
                        required : true,
                        minlength: 7
                    },
                    repassword: {
                        required : true,
                        equalTo : password
                    }
                },
                messages : {

                    password: {
                        required : "",
                        minlength : ""
                    },
                    repassword: {
                        required : "",
                        equalTo : ""
                    }
                },
                /*
                        submitHandler : function() {

                        }
                         */
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
        });

function updatePassword() {
    var inputPassword = document.getElementById('password').value;
    var key = getParameters('key');
    console.log(inputPassword);
    console.log(key);


    var MemberVO = {
        key: key,
        password: inputPassword
    };
    for (var key in MemberVO) {
        console.log("Key : " + key + ", value : " + MemberVO[key]);
    }
    $.ajax({
        url :   "../member/updatePassword.bim",
        type:   "POST",
        data:   MemberVO,
        success : function(data){
            if ( data.result == "success") {
                alert("인증 메일을 발송하였습니다. \n  인증 메일을 받지 못했다면 입력정보를 확인 후 다시 시도하세요.");
            }
        },
        error : function(error){
            alert("인증 메일 발신을 실패하였습니다. 다시 시도 해주세요.");
        },
        complete : function(){
            //getArticleFileList();
        }

    })
}

    </script>

</head>
<body>
<div class="container joinForm">
    <h2>비밀번호 변경(등록) 페이지</h2>
    <form name="joinForm" id="joinForm" method="POST">
        <div class="row">
            <div class="col-xs-8">
                <label for="password">Password</label>
                <input type="password" name="password" id="password" class="form-control" placeholder="패스워드" />
                <input type="password" name="repassword" id="repassword" class="form-control" placeholder="패스워드 확인" /> <span id="passwordResult">패스워드를 입력해주세요.</span>
            </div>
        </div>

        <div class="form-group">
            <button class="btn btn-lg btn-primary btn-block" onclick="updatePassword()">
                비밀번호 등록
            </button>
            <button style="align:center" class="btn btn-lg btn-primary btn-block">
                취소
            </button>

        </div>
    </form>
</div>
</body>
</html>