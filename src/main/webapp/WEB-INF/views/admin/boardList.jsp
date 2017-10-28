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
   });
	
    
    $("#checkAll").click(function() {
		if ($("#checkAll").prop("checked")) { //체크를 했을때
			$("input[name=checkBox]").prop("checked",true);
		
		}else{// 체크를 풀었을때
			$("input[name=checkBox]").prop("checked",false);
		}
	})
    
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
            	<th><input type="checkbox" id="checkAll"> </th>
                <th>번호</th>
                <th>아이디</th>
                <th>이름</th>
                <th>삭제 구분</th>
				<th>보기</th>
				<th>관리</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${boardList}" var="board">
                <tr>
                    <td><input type="checkbox" name="checkBox"></td>
                    <td>${board.idx}</td>
                    <td>${board.id}</td>
                    <td>${board.name}</td>
                    <td>${board.delGb}</td>
					<td><a href="${pageContext.request.contextPath }/board/viewList.bim?id=${board.id}">${board.id} </a></td>
                    <td><input type="button" id="edit" value="편집"> <input type="button" id="delete" value="삭제"></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <input type="button" id="addBoard" value="게시판 추가하기">
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


