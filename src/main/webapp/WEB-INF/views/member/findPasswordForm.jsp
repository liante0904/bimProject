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
        $(document).ready(function () {
            $('form').validate({
                rules: {
                    name: {
                        required: true,
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    phone: {
                        required: true,
                        number: true
                    }
                },
                messages: {
                    id: {
                        required: "",
                        minlength: ""
                    },
                    name: {
                        required: "필수 정보입니다.",
                    },
                    email: {
                        required: "",
                        email: "이메일 형식이 아닙니다."
                    },
                    phone: {
                        required: "필수 정보입니다.",
                        number: "숫자만 입력해야 합니다."
                    }
                },

            });

            $("#id").focusout(function () {
                var userIdInput = $(this).val();
                var idResult = $("#idResult");
                if (userIdInput.length <= 0) {
                    idResult.html("아이디를 입력해주세요.");
                }else {
                    idResult.html("");
                }
            });

            $('#email').focusout(function () {
                var userEmailInput = $(this).val();
                var emailResult = $('#emailResult');
                if (userEmailInput.length <= 0) {
                    emailResult.css("color", "red");
                    emailResult.html("이메일을 입력해주세요.");
                }else {
                    emailResult.html("");
                }
            });
        });
function findPassword() {
    var inputId = document.getElementById('id').value;
    var inputName = document.getElementById('name').value;
    var inputEmail = document.getElementById('email').value;
    var inputPhone = document.getElementById('phone').value;

    console.log(inputId);
    console.log(inputName);
    console.log(inputEmail);
    console.log(inputPhone);

    var MemberVO = {
        id: inputId,
        name: inputName,
        email: inputEmail,
        phone: inputPhone
    };
    for (var key in MemberVO) {
        console.log("Key : " + key + ", value : " + MemberVO[key]);
    }
    $.ajax({
        url :   "../member/askAccountPassword.bim",
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
    <h2>비밀번호 찾기</h2>
    <form name="joinForm" id="joinForm" method="POST">
        <div class="form-group">
            <label for="id">ID</label>
            <input type="text" id="id" name="id" class="form-control" placeholder="아이디"/>
            <span id="idResult">아이디를 입력해주세요.</span>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-xs-4">
                    <label for="name">Name</label>
                    <input type="text" name="name" id="name" class="form-control" placeholder="이름"/>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-xs-8">
                    <label for="email">E-Mail</label>
                    <input type="text" name="email" id="email" class="form-control" placeholder="E-MAIL"/>
                    <span id="emailResult"> 이메일을 입력해주세요. </span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-xs-7">
                    <label for="phone">Phone</label>
                    <input type="text" name="phone" id="phone" class="form-control" placeholder="휴대전화(숫자만 입력하세요)"/>
                </div>
            </div>
        </div>
        <div class="form-group">
            <button class="btn btn-lg btn-primary btn-block" onclick="findPassword()">
                비밀번호 찾기
            </button>
            <button style="align:center" class="btn btn-lg btn-primary btn-block">
                취소
            </button>
        </div>
    </form>
</div>
</body>
</html>