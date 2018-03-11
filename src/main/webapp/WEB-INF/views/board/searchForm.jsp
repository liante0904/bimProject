<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

$(document).ready(function(){ 
	
}); 
</script>
</head>
<body>
<form action="/board/search.bim" method="get">
<input type="hidden" id="id" name="id"> 

검색 : 
<select name="searchType" id="searchType">
	<option value="title">제목</option>
	<option value="content">본문</option>
	<option value="total">제목+본문</option>
	<option value="writer">글쓴이</option>
</select>
<input type="text" name="searchKeyword" id="searchKeyword" value=""> <input type="submit" value="검색">
</form>
</body>
</html>