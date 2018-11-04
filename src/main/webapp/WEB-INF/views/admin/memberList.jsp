<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/navbar-header.jsp" %>

<title>Home</title>
</head>
<body>
    <h1>Hello world!</h1>
 
    <table>
        <thead>
            <tr>
				<th>선택</th>
                <th>회원번호</th>
                <th>아이디</th>
                <th>비밀번호</th>
                <th>이름</th>
                <th>탈퇴여부</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${memberList}" var="member">
                <tr>
                    <td><input type="checkbox" name="chk_info" id="test" value="${member.idx}"></td>
                    <td>${member.idx}</td>
                    <td>${member.id}</td>
                    <td>${member.password}</td>
                    <td>${member.name}</td>
                    <td>${member.type}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
 
 
</body>
</html>


