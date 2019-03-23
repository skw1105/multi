<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js">
	
</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/bower_components/axios/dist/axios.min.js"></script>
<title>Insert title here</title>
<script>
	$(function() {
		$('#delete-panel').deletePanel({
			triger : '.delete', // 판넬 출력 제어
			url : '../delete/${member.userId}', // 삭제 요청 url
			moveUrl : '../list?page=${param.page}' // 삭제 후 이동 url
		});
	});
</script>
</head>
<body>

</body>
</html>