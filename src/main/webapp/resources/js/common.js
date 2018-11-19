/*common.js*/
$(document).ready(function(){
	
	/* queryString function */
	var localhost = "${pageContext.request.contextPath}";
	var idParam = "?" + "id=" + getParameters('id');
	var param =  idParam +"&num=" + getParameters('num');
	
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
	$("#edit-form").on('click',function() {
    		location.href="../board/editForm.bim"+param;
	});

	$("#delete-submit").on('click',function() {
		if (confirm("정말로 게시물을 삭제 하시겠습니까?")) 
		location.href="../board/deleteArticle.bim"+param;
	});
	
	$("#edit-submit").on("click", function(e) {
		e.preventDefault();
		$("form").submit();
	});
	
	/* sidebar function */
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

    $(document).mouseup(function (e){
	    	var container = $("#wrapper");
	    		if( container.hasClass("toggled"))
	            $("#wrapper").toggleClass("toggled");
	});

}); 
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

var logout = function () {
    if(confirm("로그아웃 하시겠습니까?") == true)
        location.href= "/logout.bim";
}