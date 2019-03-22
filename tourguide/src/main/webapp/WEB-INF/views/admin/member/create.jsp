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
		$('.id-check').checkUserId(); // ����� ID �ߺ� üũ �÷�����
		$('#member').checkPassword(); // ��й�ȣ ��ġ üũ �÷�����
	})
</script>
</head>
<body>
	<div class="row">
		<div class="col-sm-6 mx-auto">
			<h2 class="my-5 text-primary">
				<i class="fas fa-user-plus"></i> ȸ�� �߰�
			</h2>
			<form:form modelAttribute="member">
				<div class="form-group">
					<label for="userId"> ����� ID
						<button type="button" class="btn btn-primary btn-sm id-check">
							<i class="fas fa-user-check"></i> �ߺ� üũ
						</button> <span id="message"></span>
					</label>
					<form:input path="userId" class="form-control" />
					<form:errors path="userId" element="div" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="password">��й�ȣ</label>
					<form:password path="password" class="form-control" />
					<form:errors path="password" element="div" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="password2">��й�ȣ Ȯ��</label> <input type="password"
						id="password2" class="form-control" />
				</div>
				<div class="form-group">
					<label for="name">�̸�</label>
					<form:input path="name" class="form-control" />
					<form:errors path="name" element="div" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="userLevel">����� ����</label>
					<form:select path="userLevel" class="form-control"
						items="${userLevels}" itemLabel = 'label' itemValue = 'value'/>
				</div>
				<div class="form-group">
					<label for="email">email</label>
					<form:input path="email" class="form-control" />
					<form:errors path="email" element="div" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="phone">��ȭ��ȣ</label>
					<form:input path="phone" class="form-control" />
				</div>
				<div class="form-group">
					<label for="address">�ּ�</label>
					<form:input path="address" class="form-control" />
				</div>
				<div class="text-center">
					<button type="submit" class="btn btn-primary" disabled>
						<i class="fas fa-check"></i> �Ϸ�
					</button>
					<a href="list?page=${param.page}" class="btn btn-primary back">
						<i class="fas fa-undo"></i> ���
					</a>
				</div>
			</form:form>
		</div>
		<!-- div.col -->
	</div>
	<!-- div.row -->
</body>
</html>