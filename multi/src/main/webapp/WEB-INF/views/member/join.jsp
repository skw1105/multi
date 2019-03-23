<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="iot"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

<script src="${contextPath}/resources/js/member.js"></script>
<script src="${contextPath}/resources/js/common.js"></script>

<script>
	$(function() {
		$('.id-check').checkUserId();
		$('#member').checkPassword();
	})
</script>

</head>
<body>
	<div class="row">
		<div class="col-sm-6 mx-auto">
			<h2 class="my-5 text-primary">
				<i class="fas fa-user-plus"></i> 회원 가입
			</h2>
			<form:form modelAttribute="membership">
				<div class="form-group">
					<label for="userId"> 사용자 ID
						<button type="button" class="btn btn-primary btn-sm id-check">
							<i class="fas fa-user-check"></i> 중복 체크
						</button> <span id="message"></span>
					</label>
					<form:input path="userId" class="form-control" />
					<form:errors path="userId" element="div" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="password">비밀번호</label>
					<form:password path="password" class="form-control" />
					<form:errors path="password" element="div" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="password2">비밀번호 확인</label> <input type="password"
						id="password2" class="form-control" />
				</div>
				<div class="form-group">
					<label for="name">이름</label>
					<form:input path="name" class="form-control" />
					<form:errors path="name" element="div" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="email">email</label>
					<form:input path="email" class="form-control" />
					<form:errors path="email" element="div" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="phone">전화번호</label>
					<form:input path="phone" class="form-control" />
				</div>
				<div class="form-group">
					<label for="explanation">블로그 소개</label>
					<form:input path="explanation" class="form-control" />
				</div>
				<div class="text-center">
					<button type="submit" class="btn btn-primary" disabled>
						<i class="fas fa-check"></i> 완료
					</button>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>

