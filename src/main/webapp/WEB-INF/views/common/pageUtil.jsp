<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
            <div>
            	<c:set var="currentPage" value="${pageUtil.totalPageCnt }" scope="page"/>
            	출력 : ${currentPage }
           	
            	<c:choose>
            	<c:when test="${currentPage > 10 }"><c:set var="currentPage" value="10"/></c:when>
				<c:otherwise>
				<c:set var="currentPage" value="${pageUtil.totalPageCnt }"/>
				</c:otherwise>
            	</c:choose>
 
                <c:forEach var="PageCntByBoard" begin="1" end="${currentPage}" varStatus="status">
	                <c:choose >	
	                	<c:when test="${status.current eq pageUtil.currentPage }">
	                		<font color="red">${status.current }</font>
	                	</c:when>     
	                		<c:otherwise>							
	                			<a href="${pageContext.request.contextPath }/board/viewList.bim?id=${boardName }&page=${status.current }">${status.current }</a>
	                		</c:otherwise>
	                		
	                </c:choose>
                </c:forEach>
            </div>
            
            <div>현재 페이지 : ${pageUtil.currentPage}</div>


</body>
</html>