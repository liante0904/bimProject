<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/include/includeHeader.jsp" %>
 
</head>
 
    <form id="fileForm" action="/test/fileUpload" method="post"
        enctype="multipart/form-data">
        <input type="file" id="fileUp" name="fileUp"/><br/><br/>
        <input type="file" id="fileUp2" name="fileUp2"/><br/><br/>
          
        아이디 : <input type="text" name="id" />
        비밀번호 : <input type="password" name="pw" /><br/><br/>
        <input type="button" value="전송하기" onClick="fileSubmit();">
    </form>
 
<script>
    function fileSubmit() {
        var formData = new FormData($("#fileForm")[0]);
        $.ajax({
            type : 'post',
            url : '/test/fileUpload',
            data : formData,
            processData : false,
            contentType : false,
            success : function(html) {
                alert("파일 업로드하였습니다.");
            },
            error : function(error) {
                alert("파일 업로드에 실패하였습니다.");
                console.log(error);
                console.log(error.status);
            }
        });
    }
</script>
