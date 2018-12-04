<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>브리지 임팩트 - 글수정</title>
<link rel="stylesheet" href="../resources/css/board/board-layout.css">    <!-- board-layout CSS -->
<%@ include file="/WEB-INF/include/navbar-header.jsp" %>
<script src="../resources/js/article.js"></script>
<script type="text/javascript">

$(document).ready(function(){

}); 

$(function(){
	$('form').validate({
		rules : {
			
			title: {
				required : true,
				minlength: 1,
				maxlength: 40
			},
			contents: {
				required : true,
				minlength: 3
			}
		},
		messages : {
			title:{
				required : "글 제목을 입력하세요",	
				minlength : "글 제목을 입력하세요",
				maxlength : "글 제목이 너무 길어요"
			},
			contents: {
				required : "본문을 입력하세요",
				minlength : "본문을 입력하세요"
			}
		},
	});

});
</script>
</head>
<body>
	<div class="container editForm">
		<h1 class="mb-1">글수정</h1>
		<form action="../board/editArticle.bim" method="post"  enctype="multipart/form-data">
			<div class="container">
				<div class="mt-2">
					<input type="text" id="title" name="title" class="form-control" placeholder="제목을 입력하세요." value="${article.title}"/>
				</div>
				<div class="mt-2">
					<textarea id="contents" name="contents" class="form-control">${article.contents}</textarea>
					<script>
						// Replace the <textarea id="editor1"> with a CKEditor
						// instance, using default configuration.
						CKEDITOR.replace( 'contents' );
					</script>
				</div>
				<div class="mt-2">
					<div id="fileList">
					<c:forEach items="${fileList}" var="file" varStatus="status">
						<div id="fileList${status.index }">
							<span class="glyphicon glyphicon-remove" aria-hidden="true" onclick="deleteFile('${file.articleIdx }', '${file.storedFileName}', '${sessionScope.loginInfo.id}', '${status.index }')"></span>
							<span>첨부파일 : </span>
							<a href="../download.bim?num=${file.articleIdx }&storedNm=${file.storedFileName}">${file.originalFileName }</a>
						</div>
					</c:forEach>
					</div>
					<input type="file" name="files1" class="form-control">
					<input type="file" name="files2" class="form-control">
					<input type="file" name="files3" class="form-control">
					<input type="file" name="files4" class="form-control">
				</div>
				<div class="mt-2">
					<input type="button" id="cancel" value="목록" class="btn btn-default"/>
					<input type="button" id="edit-submit" value="수정" class="btn btn-primary pull-right"/>
				</div>
			</div>
		</form>
	</div>
</body>
</html>


