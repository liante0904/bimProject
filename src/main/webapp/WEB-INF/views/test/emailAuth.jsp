<%--
  Created by IntelliJ IDEA.
  User: shinseunghoon
  Date: 2018-11-20
  Time: 오후 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/include/navbar-header.jsp" %>			<!-- navbar header -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main/layout.css">    <!--main/layout CSS -->
    <script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){

        });

    </script>
</head>
<body>
<div class="container" id="main_page">
    ${result}
</div>
<%@ include file="/WEB-INF/views/main/footer.jsp" %>
</body>
</html>
