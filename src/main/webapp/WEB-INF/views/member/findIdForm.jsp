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

                    name: {
                        required : true,
                    },
                    email: {
                        required : true,
                        email : true
                    },
                    phone: {
                        required : true,
                        number : true
                    }
                },
                messages : {
                    name: {
                        required : "필수 정보입니다.",
                    },
                    email: {
                        required : "필수 정보입니다.",
                        email : ""
                    },
                    phone: {
                        required : "필수 정보입니다.",
                        number : "숫자만 입력하세요."
                    },
                },
                /*
                        submitHandler : function() {

                        }
                         */
            });
        });

function findId() {
    var inputName = document.getElementById('name').value;
    var inputEmail = document.getElementById('email').value;
    var inputPhone = document.getElementById('phone').value;

    console.log(inputName);
    console.log(inputEmail);
    console.log(inputPhone);

    var MemberVO = {
        name: inputName,
        email: inputEmail,
        phone: inputPhone
    };
    for (var key in MemberVO) {
        console.log("Key : " + key + ", value : " + MemberVO[key]);
    }
    $.ajax({
        url :   "../member/askAccountId.bim",
        type:   "POST",
        data:   MemberVO,
        success : function(data){
            console.log(data+ " data.result : " + data.result);
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
    });

}

    </script>

</head>
<body>
<div class="container joinForm">
    <h2>아이디 찾기</h2>
        <div class="form-group">
            <div class="row">
                <div class="col-xs-4">
                    <label for="name">Name</label>
                    <input type="text" name="name"  id="name" class="form-control" placeholder="이름" />
                </div>
            </div>
        </div>

    <div class="form-group">
        <div class="row">
            <div class="col-xs-8">
                <label for="email">E-Mail</label>
                <input type="text" name="email" id="email" class="form-control" placeholder="E-MAIL" /> <span id="emailResult"></span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col-xs-7">
                <label for="phone">Phone</label>
                <input type="text" name="phone" id="phone" class="form-control" placeholder="휴대전화(숫자만 입력해주세요)" />
            </div>
        </div>
    </div>

        <div class="form-group">
            <button type="button" class="btn btn-lg btn-primary btn-block" onclick="findId()">
                아이디 찾기
            </button>
            <button type="button" style="align:center" class="btn btn-lg btn-primary btn-block">
                취소
            </button>
        </div>
</div>
</body>
</html>