<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/include/includeHeader.jsp" %>
<!-- header 영역 작성중 -->
<script type="text/javascript"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<script type="text/javascript">
$(document).ready(function(){
	$("#id").keyup(function(){
		if($(this).val().length > 2){
			var id = $(this).val();
			console.log(id);
			 $.ajax({
			        url : "${pageContext.request.contextPath}/board/checkBoardIdAjax.bim",
			        type: "get",
			        data : { "id" : id },
			        success : function(data){
			         if ( data.result == "success") {
						console.log("사용 가능한 URL");
						$("#result").html("사용 가능한 URL입니다.");
					}else {
						console.log("중복된 URL");
						$("#result").html("중복된 URL입니다.");
					}
			        }
			    });
			
		}
	});
	
	
});
$(function(){
	
	$('#insert').click(function(){
		var obj = new Object();
		
		
		obj.id = $("#id").val();
		obj.name = $("#name").val();
		alert(obj.id);
		var jsonData = JSON.stringify(obj);
		
		 $.ajax({
		        url : "${pageContext.request.contextPath }/board/insertBoardAjax.bim",
		        type: "post",
		        data : { 
		        	"id" : $("#id").val(),
		        	"name" : $("#name").val()
		        },
		        success : function(data){
		         if ( data.result == "success") {
					alert("게시판 생성  성공");
				}
		        }
		        
		    });
		

		
		});
	
	
	
});



</script>

</head>
<body>

<h1>addBoard</h1>



 <form action="${pageContext.request.contextPath }/board/insertBoard.bim"  method="POST">

  <div>
    <pre>
게시판 이름   <input type="text" id="name" name="name">
접속 URL <input type="text" id="id" name="id">  <span id="result"> URL값을 입력하세요</span>

    </pre>
  </div>

   <div>
   <input type="button" value="취소" onclick="javascript:window.close()" style="align:center">
   <input type="button" value="가입" id="insert" />

  </div>
 </form>
 
</body>
</html>