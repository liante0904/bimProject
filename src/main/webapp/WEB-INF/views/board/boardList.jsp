<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Home</title>
<script type="text/javascript">

$(document).ready(function(){ 
	var	 paramIdValue = getParameters('id');
	$("#id").val(paramIdValue);
	
}); 

$(function(){
	var	 paramIdValue = getParameters('id');
	$("#write").click(function() {
		location.href="../board/writeForm.bim?id="+ paramIdValue;
	});
	
});


</script>
</head>
<body>
    <h1>boardList.jsp</h1>
 
 <div class="container">
    <table class="table table-hover">
        <thead>
            <tr>
                <th>게시판index</th>
                <th>게시판URL</th>
                <th>게시판이름</th>
				<th>삭제여부</th>
            </tr>
        </thead>
        <tbody>
         
            <c:forEach items="${boardList}" var="board" >
                <tr>
                    <td>${board.idx}</td>
                    <td>${board.id}</td>
                    <td>${board.name}</td>
                    <td>${board.delGb}</td>
                </tr>
                
            </c:forEach>

        </tbody>
    </table>		
</div>


</body>
</html>


