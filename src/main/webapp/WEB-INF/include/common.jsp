<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">
var localhost = '${pageContext.request.contextPath }';
    var getParameters = function (paramName) {
        // 리턴값을 위한 변수 선언
        var returnValue;
        // 현재 URL 가져오기
        var url = location.href;
        // get 파라미터 값을 가져올 수 있는 ? 를 기점으로 slice 한 후 split 으로 나눔
        var parameters = (url.slice(url.indexOf('?') + 1, url.length)).split('&');
        // 나누어진 값의 비교를 통해 paramName 으로 요청된 데이터의 값만 return
        for (var i = 0; i < parameters.length; i++) {
            var varName = parameters[i].split('=')[0];
            if (varName.toUpperCase() == paramName.toUpperCase()) {
                returnValue = parameters[i].split('=')[1];
                return decodeURIComponent(returnValue);
            }
        }
    };
    // 파라미터 호출 예시 (id) console.log(getParameters('id'));
    $(document).ready(function(){
        var idParam = "?" + "id=" + getParameters('id');
        $("#list").click(function() {
            location.href="../board/viewList.bim"+idParam;
        });

        
        $("#cancel").click(function() {
            if (confirm("정말로 취소 하시겠습니까?")) {
                location.href="../board/viewList.bim"+idParam;
                    
                }
        });

        
        
        $("#writelist").click(function() {
            if (confirm("정말로 글쓰기를 취소 하시겠습니까?")) {
                location.href="../board/viewList.bim"+idParam;
                    
                }
        });
        
        $("#joinFormCancel").click(function() {
            if (confirm("정말로 회원가입을 취소 하시겠습니까?")) {
                location.href="../";
                    
                }
        });

    	$("#logout").click(function(){
    		var url = window.location.href;
    		$.ajax({
    	        type : "GET",
    	        url : url + "logout.bim",
    	        dataType : "text",
    	        error : function(){
    	            alert('로그아웃 실패!!');
    	        },
    	        success : function(data){
    	            if(confirm("로그아웃 하시겠습니까?") == true)
    	             location.href= "${pageContext.request.contextPath }/logout.bim";
    	        }

    		});
    	});
}); 

    

</script>
