<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<c:set var="displayPageCnt" value="${pageUtil.totalPageCnt }" scope="page" />
		
		<c:set var="displayEndPageCnt" value="${pageUtil.totalPageCnt }" scope="page" />
		<c:if test="${pageUtil.currentPage < 11 }">
			<c:if test="${pageUtil.pageRangeCnt+ 10 <= pageUtil.totalPageCnt   }">		
				<c:set var="displayEndPageCnt" value="${pageUtil.pageRangeCnt+10 }" scope="page" />
			</c:if>
		</c:if>


		<!-- (처음) 버튼 판별  -->
		<c:if test="${pageUtil.currentPage != 1 and displayPageCnt > 10}">
			<a
				href="${pageContext.request.contextPath }/board/viewList.bim?id=${boardName }&page=${status.current }">처음</a>

		</c:if>


		<!-- 이전 버트  --> 




		<!-- 게시판의 페이지 수를 통해, 보여질 게시판 갯수 검증 -->
		<c:choose>
			<c:when test="${displayPageCnt > 10 }">
				<c:set var="displayPageCnt" value="11" />
			</c:when>

			<c:otherwise>
				<c:set var="displayPageCnt" value="${pageUtil.totalPageCnt }" />
			</c:otherwise>
		</c:choose>

		<!-- 페이지 출력 부분 -->
		<c:forEach var="PageCntByBoard" begin="${pageUtil.pageRangeCnt+1  }" end="${displayEndPageCnt}"
			varStatus="status">
			<c:choose>
				<c:when test="${status.current eq pageUtil.currentPage }">
					<font color="red">${status.current }</font>
				</c:when>
				<c:otherwise>
					<a
						href="${pageContext.request.contextPath }/board/viewList.bim?id=${boardName }&page=${status.current }">${status.current }</a>
				</c:otherwise>

			</c:choose>

		</c:forEach>
		
		<!-- 다음 버튼 -->
		
		<!-- 끝 버튼  판별-->
		<c:if test="${displayPageCnt > 10}">
			<a
				href="${pageContext.request.contextPath }/board/viewList.bim?id=${boardName }&page=${displayEndPageCnt }">끝</a>
		</c:if>


	</div>

 
 <div>pageRangeCnt: ${pageUtil.pageRangeCnt }</div>
 <div>displayPageCnt : ${displayPageCnt }</div>
  <div>현재 게시판의 총 페이지 갯수 : ${pageUtil.totalPageCnt } <br></div>
		
	<div>현재 페이지 : ${pageUtil.currentPage}</div>
	<div>보여질 게시글 갯수 : ${pageUtil.displayArticleCnt}</div>


</body>
</html>