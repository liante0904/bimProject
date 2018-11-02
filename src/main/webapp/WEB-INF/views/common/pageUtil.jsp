<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../resources/css/pageUtil.css">    <!-- pageUtil CSS -->
<title></title>
</head>
<body>
	<nav>
		<div class="board_paging text-center">
			<c:set var="requestURI" value="${requestScope['javax.servlet.forward.servlet_path']}" />
			<ul class="pagination">
				<!-- (처음) 버튼 판별  (첫페이지가 아니거나 10페이지 이상일때)-->
				<c:if
					test="${pageUtil.currentPage +1 ne 1 and pageUtil.totalPageCnt > pageUtil.displayPageCnt}">
					<li><a
						href="..${requestURI}?id=${param.id }&page=${status.current }<c:if test="${!empty param.searchKeyword  || param.searchKeyword ne null }">&searchType=${param.searchType }&searchKeyword=${param.searchKeyword }</c:if>">처음</a></li>
				</c:if>

				<!-- 이전 버튼  (첫페이지가 아닐때)-->
				<c:if test="${ pageUtil.currentPage +1 ne 1}">
					<li><a
						href="..${requestURI}?id=${param.id }&page=${pageUtil.pageRangeCnt  }<c:if test="${!empty param.searchKeyword  || param.searchKeyword ne null }">&searchType=${param.searchType }&searchKeyword=${param.searchKeyword }</c:if>"
						aria-label="Previous"><span aria-hidden="true">이전</span></a></li>
				</c:if>

				<!-- 페이지 출력 부분 -->
				<c:forEach var="PageCntByBoard"
					begin="${pageUtil.pageRangeCnt + 1  }"
					end="${pageUtil.pageRangeCnt  + pageUtil.displayPageCnt }" varStatus="status">
					<c:choose>
						<c:when test="${status.current eq pageUtil.currentPage +1 }">
							<!-- 현재 페이지를 음영처리 -->
							<%-- 					<li><font color="red">${status.current }</font></li> --%>
							<li class="active"><a href="#">${status.current }</a></li>
						</c:when>
						<c:when test="${status.current ne pageUtil.currentPage +1  and status.current <= pageUtil.totalPageCnt }">
							<li><a
								href="..${requestURI}?id=${param.id }&page=${status.current }<c:if test="${!empty param.searchKeyword  || param.searchKeyword ne null }">&searchType=${param.searchType }&searchKeyword=${param.searchKeyword }</c:if>">${status.current }</a></li>
						</c:when>
					</c:choose>
				</c:forEach>

				<!-- 다음 버튼 -->
				<c:if test="${pageUtil.totalPageCnt > pageUtil.displayPageCnt and pageUtil.totalPageCnt ne pageUtil.currentPage +1}">
						<li><a
							href="..${requestURI}?id=${param.id }&page=${pageUtil.pageRangeCnt + 1 + pageUtil.displayPageCnt }<c:if test="${!empty param.searchKeyword  || param.searchKeyword ne null }">&searchType=${param.searchType }&searchKeyword=${param.searchKeyword }</c:if>"
							aria-label="Next"><span aria-hidden="true">다음</span></a></li>
				</c:if>
				<!-- 끝 버튼  판별-->
				<c:if
					test="${pageUtil.totalPageCnt > pageUtil.displayPageCnt and pageUtil.totalPageCnt  ne pageUtil.currentPage +1}">
					<li><a
						href="..${requestURI}?id=${param.id }&page=${pageUtil.totalPageCnt }<c:if test="${!empty param.searchKeyword  || param.searchKeyword ne null }">&searchType=${param.searchType }&searchKeyword=${param.searchKeyword }</c:if>">끝</a></li>
				</c:if>
			</ul>
		</div>
	</nav>
<%-- 
<div>
	<br>
${requestURI}
	<div>begin : ${pageUtil.pageRangeCnt + 1  }
		end="${pageUtil.pageRangeCnt  + pageUtil.displayPageCnt }"</div>

	<div>jspt(endPageCnt) : ${endPageCnt }</div>
	<div>pageRangeCnt: ${pageUtil.pageRangeCnt }</div>
	<div>displayPageCnt : ${pageUtil.displayPageCnt }</div>
	<div>
		현재 게시판의 총 페이지 갯수 : ${pageUtil.totalPageCnt } <br>
	</div>
	<div>현재 페이지 : ${pageUtil.currentPage +1}</div>
	<div>보여질 게시글 갯수 : ${pageUtil.displayArticleCnt}</div>


	<div>현재 범위 : ${ var1 + pageUtil.displayPageCnt }</div>
	<div>마지막페이지 범위 : ${var2 + pageUtil.displayPageCnt }</div>

</div>
 --%>
</body>
</html>