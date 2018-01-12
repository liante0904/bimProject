<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<c:set var="displayPageCnt" value="${pageUtil.totalPageCnt }" scope="page" /><!-- 처음이랑 끝버튼에만 쓰고 있음 -->

		<c:set var="endPageCnt" value="${pageUtil.totalPageCnt }" scope="page" />
		
		

		<!-- 현재페이지가 1 ~ 10페이지 일때, 분기점 -->
		<c:choose>
			<c:when test="${pageUtil.currentPage < 11 }">
				<c:set var="endPageCnt" value="${pageUtil.pageRangeCnt + 10 }" scope="page" />
			</c:when>
				<c:otherwise>
					<c:set var="endPageCnt" value="${pageUtil.pageRangeCnt }" scope="page" />
				</c:otherwise>
		</c:choose>
		
		<!-- (처음) 버튼 판별  (첫페이지가 아니거나 10페이지 이상일때)-->
		<c:if test="${pageUtil.currentPage ne 1 and displayPageCnt > 10}">
			<a href="${pageContext.request.contextPath }/board/viewList.bim?id=${boardName }&page=${status.current }">처음</a>
		</c:if>


		<!-- 이전 버튼  (첫페이지가 아닐때)-->
		<c:if test="${ pageUtil.currentPage ne 1}">
			<a href="${pageContext.request.contextPath }/board/viewList.bim?id=${boardName }&page=${pageUtil.pageRangeCnt + 1 - 10 }">이전</a>
		</c:if>



		<!-- 게시판의 페이지 수를 통해, 보여질 게시판 갯수 검증 -->
		
		<c:if test="${pageUtil.pageRangeCnt  == pageUtil.currentPage}">이거 컨트롤러에서 해도 됨</c:if>



		<!-- 페이지 출력 부분 -->
		<c:forEach var="PageCntByBoard" begin="${pageUtil.pageRangeCnt + 1  }" end="${pageUtil.pageRangeCnt  + 10 }" varStatus="status">
				<!-- 현재 페이지를 음영처리 -->
			<c:choose>
				<c:when test="${status.current eq pageUtil.currentPage }">
					<font color="red">${status.current }</font>
				</c:when>
				
				<c:when test="${status.current ne pageUtil.currentPage  and status.current <= pageUtil.totalPageCnt }">
					<a href="${pageContext.request.contextPath }/board/viewList.bim?id=${boardName }&page=${status.current }">${status.current }</a>
				</c:when>

			</c:choose>

		</c:forEach>

		<!-- 다음 버튼 -->
		<c:if test="${displayPageCnt > 10 and pageUtil.totalPageCnt ne pageUtil.currentPage}">
			<fmt:parseNumber var="var1" value="${pageUtil.currentPage / 10}" integerOnly="true" />
			<fmt:parseNumber var="var2" value="${pageUtil.totalPageCnt  / 10}" integerOnly="true" />
				<c:if test="${var1 ne var2}">
					<a href="${pageContext.request.contextPath }/board/viewList.bim?id=${boardName }&page=${pageUtil.pageRangeCnt + 1 + 10 }">다음</a>
				</c:if>
		</c:if>
		<!-- 끝 버튼  판별-->
		<c:if test="${displayPageCnt > 10 and displayPageCnt  ne pageUtil.currentPage}">
			<a href="${pageContext.request.contextPath }/board/viewList.bim?id=${boardName }&page=${displayPageCnt }">끝</a>
		</c:if>


	</div>
	
	<br>
	
	<div> begin : ${pageUtil.pageRangeCnt + 1  }  end="${pageUtil.pageRangeCnt  + 10 }" </div>

	<div>	jspt(endPageCnt) : ${endPageCnt }</div>
	<div>pageRangeCnt: ${pageUtil.pageRangeCnt }</div>
	<div>displayPageCnt : ${displayPageCnt }</div>
	<div>
		현재 게시판의 총 페이지 갯수 : ${pageUtil.totalPageCnt } <br>
	</div>
	<div>현재 페이지 : ${pageUtil.currentPage}</div>
	<div>보여질 게시글 갯수 : ${pageUtil.displayArticleCnt}</div>



	
	<div>현재 범위 : ${ var1 + 10 } </div>
	<div>마지막페이지 범위 :  ${var2 + 10 } </div>

</body>
</html>