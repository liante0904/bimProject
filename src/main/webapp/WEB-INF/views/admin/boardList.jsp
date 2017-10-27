<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/includeHeader.jsp" %>
<%@ include file="/WEB-INF/include/adminHeader.jsp" %>
<script type="text/javascript">
$(function(){
	
    $("#addBoard").click(function(){
    	location.href="${pageContext.request.contextPath }/admin/board/addBoard.bim";

    	$.ajax({
		        url : "${pageContext.request.contextPath }/admin/board/addBoard.bim",
		        type: "get",
		        data : { 
		        	"id" : "test3",
		        	"name" : "테스트3 게시판",
		        },
		        success : function(data){
		        alert("??")	
		        }
		    });
   });
	
    $("#add").click(function(){
		 $.ajax({
		        url : "${pageContext.request.contextPath }/board/insertBoard.bim",
		        type: "post",
		        data : { 
		        	"id" : "test3",
		        	"name" : "테스트3 게시판",
		        },
		        success : function(data){
		         if ( data.result == "success") {
					alert("게시판 생성  성공");
					backPage();
				}
		        }
		    });
    });
});

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

 
 
    <table>
        <thead>
            <tr>
                <th>회원번호</th>
                <th>아이디</th>
                <th>이름</th>
                <th>삭제 구분</th>
				<th>보기</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${boardList}" var="board">
                <tr>
                    <td>${board.idx}</td>
                    <td>${board.id}</td>
                    <td>${board.name}</td>
                    <td>${board.delGb}</td>
					<td><a href="${pageContext.request.contextPath }/board/viewList.bim?id=${board.id}">${board.id} 게시판 이동</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <input type="button" id="add" value="생성">     <input type="button" id="edit" value="편집"> <input type="button" id="delete" value="삭제?">
 </div>
 
<div style="border: 1px solid black; float: left; width:49%;">
<h1>게시글 현황</h1>
    <table>
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
                    <td>${article.boardName}</td>
                    <td><a href="${pageContext.request.contextPath }/board/viewArticle.bim?id=${article.boardName}&num=${article.idx}">${article.title}</a></td>
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


