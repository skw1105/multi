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
<script src="${contextPath}/resources/js/member.js"></script>

<title>Insert title here</title>

<script>
	$(function() {
		$('.id-check').checkUserId(); // 사용자 ID 중복 체크 플러그인
		$('#member').checkPassword(); // 비밀번호 일치 체크 플러그인
	})
</script>
</head>
<body>
	<div class="row">
		<div class="col-sm-6 mx-auto">
			<h2 class="my-5 text-primary">
				<i class="fas fa-user-plus"></i> 회원 추가
			</h2>
			<form:form modelAttribute="member">
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
					<label for="userLevel">사용자 레벨</label>
					<form:select path="userLevel" class="form-control"
						items="${userLevels}" itemLabel = 'label' itemValue = 'value'/>
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
					<label for="address">주소</label>
					<form:input path="address" class="form-control" />
				</div>
				<div class="text-center">
					<button type="submit" class="btn btn-primary" disabled>
						<i class="fas fa-check"></i> 완료
					</button>
					<a href="list?page=${param.page}" class="btn btn-primary back">
						<i class="fas fa-undo"></i> 목록
					</a>
				</div>
			</form:form>
		</div>
		<!-- div.col -->
	</div>
	<!-- div.row -->
</body>
</html>