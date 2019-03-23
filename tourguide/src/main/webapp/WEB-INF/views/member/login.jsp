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
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<div class="col-sm-5 mx-auto">
			<h2 class="my-5 text-primary">
				<i class="fas fa-sign-in-alt"></i> 로그인
			</h2>
	
			<c:if test="${not empty loginInfo.target}">
				<div class="alert alert-warning">
					<strong>${loginInfo.reason}</strong>
				</div>
			</c:if>

			<form:form modelAttribute="loginInfo">
				<form:hidden path="target" />
				<div class="form-group">
					<label for="userId">사용자 ID</label>
					<form:input path="userId" class="form-control" />
					<form:errors path="userId" element="div" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="password">비밀번호</label>
					<form:password path="password" class="form-control" />
					<form:errors path="password" element="div" cssClass="error" />
				</div>
				<div class="form-group">
					<form:errors path="" element="div" cssClass="error" />
				</div>
				<div class="text-center">
					<button type="submit" class="btn btn-primary">
						<i class="fas fa-sign-in-alt"></i> 로그인
					</button>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>