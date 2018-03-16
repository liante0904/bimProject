<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
<!-- 		<c:set var="displayPageCnt" value="${pageUtil.totalPageCnt }" scope="page" />처음이랑 끝버튼에만 쓰고 있음
		
		<c:set var="endPageCnt" value="${pageUtil.totalPageCnt }" scope="page" />
		
		
		pageRangeCnt로 대체 부분
		현재페이지가 첫번째 범위 (1 ~ 10페이지) 일때, 분기점
		<c:choose>
			<c:when test="${pageUtil.currentPage + 1 < 10 }">
				<c:set var="endPageCnt" value="${pageUtil.pageRangeCnt + 10 }" scope="page" />
			</c:when>
				<c:otherwise>
					<c:set var="endPageCnt" value="${pageUtil.pageRangeCnt }" scope="page" />
				</c:otherwise>
		</c:choose>
		 -->
		
		<c:set var="requestURI" value="${requestScope['javax.servlet.forward.servlet_path']}" /> 

 
		 
		<!-- (처음) 버튼 판별  (첫페이지가 아니거나 10페이지 이상일때)-->
		<c:if test="${pageUtil.currentPage +1 ne 1 and pageUtil.totalPageCnt > 10}">
			<a href="${pageContext.request.contextPath } ${requestURI}?id=${param.id }&page=${status.current }<c:if test="${!empty param.searchKeyword  || param.searchKeyword ne null }">&searchType=${param.searchType }&searchKeyword=${param.searchKeyword }</c:if>">처음</a>
		</c:if>


		<!-- 이전 버튼  (첫페이지가 아닐때)-->
		<c:if test="${ pageUtil.currentPage +1 ne 1}">
				<a href="${pageContext.request.contextPath } ${requestURI}?id=${param.id }&page=${pageUtil.pageRangeCnt  }<c:if test="${!empty param.searchKeyword  || param.searchKeyword ne null }">&searchType=${param.searchType }&searchKeyword=${param.searchKeyword }</c:if>">이전</a>
		</c:if>


		<!-- 페이지 출력 부분 -->
		<c:forEach var="PageCntByBoard" begin="${pageUtil.pageRangeCnt + 1  }" end="${pageUtil.pageRangeCnt  + 10 }" varStatus="status">
				<!-- 현재 페이지를 음영처리 -->
			<c:choose>
				<c:when test="${status.current eq pageUtil.currentPage +1 }">
					<font color="red">${status.current }</font>
				</c:when>
				
				<c:when test="${status.current ne pageUtil.currentPage +1  and status.current <= pageUtil.totalPageCnt }">
					<a href="${pageContext.request.contextPath } ${requestURI}?id=${param.id }&page=${status.current }<c:if test="${!empty param.searchKeyword  || param.searchKeyword ne null }">&searchType=${param.searchType }&searchKeyword=${param.searchKeyword }</c:if>">${status.current }</a>
				</c:when>

			</c:choose>
		</c:forEach>

		<!-- 다음 버튼 -->
		<c:if test="${pageUtil.totalPageCnt > 10 and pageUtil.totalPageCnt ne pageUtil.currentPage +1}">
			<fmt:parseNumber var="var1" value="${pageUtil.currentPage +1 / 10}" integerOnly="true" />
			<fmt:parseNumber var="var2" value="${pageUtil.totalPageCnt  / 10}" integerOnly="true" />
				<c:if test="${var1 ne var2}">
					<a href="${pageContext.request.contextPath } ${requestURI}?id=${param.id }&page=${pageUtil.pageRangeCnt + 1 + 10 }<c:if test="${!empty param.searchKeyword  || param.searchKeyword ne null }">&searchType=${param.searchType }&searchKeyword=${param.searchKeyword }</c:if>">다음</a>
				</c:if>
		</c:if>
		<!-- 끝 버튼  판별-->
		<c:if test="${pageUtil.totalPageCnt > 10 and pageUtil.totalPageCnt  ne pageUtil.currentPage +1}">
			<a href="${pageContext.request.contextPath } ${requestURI}?id=${param.id }&page=${pageUtil.totalPageCnt }<c:if test="${!empty param.searchKeyword  || param.searchKeyword ne null }">&searchType=${param.searchType }&searchKeyword=${param.searchKeyword }</c:if>">끝</a>
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
	<div>현재 페이지 : ${pageUtil.currentPage +1}</div>
	<div>보여질 게시글 갯수 : ${pageUtil.displayArticleCnt}</div>

	
	<div>현재 범위 : ${ var1 + 10 } </div>
	<div>마지막페이지 범위 :  ${var2 + 10 } </div>

</body>
</html>