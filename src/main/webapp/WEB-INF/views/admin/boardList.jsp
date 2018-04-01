<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/mainHeader.jsp" %>
<%@ include file="/WEB-INF/include/adminHeader.jsp" %>

<script type="text/javascript">
$(document).ready(function(){

}); 

$(function(){
	
    $("#addBoard").click(function(){
    	location.href="../admin/board/addBoard.bim";
   });
	
    
    $("#checkAll").click(function() {
		if ($("#checkAll").prop("checked")) { //체크를 했을때
			$("input[name=checkBox]").prop("checked",true);
		
		}else{// 체크를 풀었을때
			$("input[name=checkBox]").prop("checked",false);
		}
	})
	
	$(".deleteBoard").click(function(e) {
		var eventCheckId = $(this).attr('id');
		var eventCheckValue = $(this).attr('value');
		var boardId = $(this).attr('data-id');
		var boardName = $('input[data-name='+boardId+']').val();
		console.log(eventCheckId);
		console.log(boardName);
		
		if (confirm("정말로  '"+ boardName + "' 게시판을 " + eventCheckValue + " 처리 하시겠습니까?" ) == true) {
			if (eventCheckId === "close") {
				 $.ajax({
				        url : "${pageContext.request.contextPath}/board/closeBoardAjax.bim",
				        type: "get",
				        data : { "id" : boardId },
				        success : function(data){
				         if ( data.result == "success") {
				        	 alert(data.resultMsg);
						}else {
							alert(data.resultMsg);
						}
				        }
				    });
			}else if (eventCheckId === "open") {
				 $.ajax({
				        url : "${pageContext.request.contextPath}/board/openBoardAjax.bim",
				        type: "get",
				        data : { "id" : boardId },
				        success : function(data){
				         if ( data.result == "success") {
				        	 alert(data.resultMsg);
						}else {
							alert(data.resultMsg);
						}
				        }
				    });
				
			}

		}
	})
    

});


function editBoard(boardId) {
	var id = boardId;
	alert(id);
}

function deleteBoard(boardId) {
	var eventCheck = $(this).attr('id');
	console.log(eventCheck);
	var id = boardId;
	
	var boardName = $('td[data-name='+id+']').text();
	if (confirm("정말로 게시판 "+ boardName + "을 비공개 처리 하시겠습니까?" ) == true) {
		 $.ajax({
		        url : "${pageContext.request.contextPath}/board/deleteBoardAjax.bim",
		        type: "get",
		        data : { "id" : id },
		        success : function(data){
		         if ( data.result == "success") {
		        	 alert(data.resultMsg);
					$("#result").html("사용 가능한 URL입니다.");
				}else {
					alert(data.resultMsg);
					$("#result").html("중복된 URL입니다.");
				}
		        }
		    });
	}
}

</script>
<title>Home</title>
 <style>
  table {
    width: 40%;
    border: 1px solid #444444;
  }
  th, td {
    border: 1px solid #444444;
  }
</style>
</head>
<body>

<div style="border: 1px solid black; float: left; width:49%;">
    <h1>게시판 관리</h1>

 
 
    <table style="height:80%; width:80%;">
        <thead>
            <tr>
            	<th><input type="checkbox" id="checkAll"> </th>
                <th>번호</th>
                <th>아이디(URL)</th>
                <th>이름</th>
                <th>삭제 구분</th>
				<th>보기</th>
				<th>관리</th>
                <th>비고</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${boardList}" var="board">
                <tr>
                    <td><input type="checkbox" name="checkBox"></td>
                    <td>${board.idx}</td>
                    <td>${board.id}</td>
                    <td><input type="text" value="${board.name}" readonly="readonly" data-name="${board.id}"></td>
                    <td>${board.delGb}</td>
					<td><a href="../board/viewList.bim?id=${board.id}">${board.id} </a></td>
                    <td><input type="button" id="edit" value="편집" onclick="editBoard('${board.id}')"> 
                    <c:set var="delGb" value="${board.delGb}"></c:set>
                    <c:choose>
                    <c:when test="${delGb eq 'N'}">
                    <input type="button" id="close" value="비공개" class="deleteBoard" data-id="${board.id}">
                    </c:when>
                    
                    <c:when test="${delGb eq 'Y'}">
                    <input type="button" id="open" value="공개" class="deleteBoard" data-id="${board.id}">
                    </c:when>
                    </c:choose>

                    </td>
                    <td><div></div></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <input type="button" id="addBoard" value="게시판 추가하기">
 </div>
 
<div style="border: 1px solid black; float: left; width:49%;">
<h1>게시글 현황</h1>
    <table style="height:30%; width:100%;">
        <thead>
            <tr>
                <th>글번호</th>
                <th>게시판 구분</th>
                <th>제목</th>
                <th>조회수</th>
				<th>작성자</th>
                <th>날짜</th>
                <th>삭제여부</th>
            </tr>
        </thead>
        <tbody>
         
            <c:forEach items="${articleList}" var="article">
                <tr>
                    <td>${article.idx}</td>
                    <td>${article.boardId}</td>
                    <td><a href="../board/viewArticle.bim?id=${article.boardId}&num=${article.idx}">${article.title}</a></td>
                    <td>${article.writeId}</td>
                    <td>${article.hitCnt}</td>
                    <td>${article.writeDt}</td>
                    <td>${article.delGb}</td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
</div> 
</body>
</html>


