<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/includeHeader.jsp" %>
<%@ include file="/WEB-INF/include/articleHeader.jsp" %>
<title>Home</title>
<script type="text/javascript">

$(document).ready(function(){ 
	
}); 

$(function(){
	var	 paramIdValue = getParameters('id');
	$("#write").click(function() {
		location.href="${pageContext.request.contextPath }/board/writeForm.bim?id="+ paramIdValue;
	})
	
});



</script>
</head>
<body>
    <h1>boardList.jsp</h1>
 
 
    <table>
        <thead>
            <tr>
                <th>글번호</th>
                <th>게시판 구분</th>
                <th>제목</th>
				<th>작성자</th>
                <th>조회수</th>
                <th>날짜</th>
                <th>삭제여부</th>
            </tr>
        </thead>
        <tbody>
         
            <c:forEach items="${articleList}" var="article" begin="0" end="${pageUtil.displayArticleCnt }">
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
 <input type="button" id="write" value="글쓰기"/>
 
<%@ include file="/WEB-INF/views/common/pageUtil.jsp" %>
</body>
</html>


