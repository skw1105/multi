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
	<h2 class="my-5 text-primary">
		<i class="fas fa-users"></i> ȸ�� ���
	</h2>
	<div class="text-right">
		<a href="create?page=${pi.page}"><i class="fas fa-user-plus"></i>
			�߰�</a> (�� : ${pi.totalCount} ��)
	</div>
	<table class="table table-striped table-hover">
		<tr>
			<th>No</th>
			<th>����� ID</th>
			<th>�̸�</th>
			<th>email</th>
			<th>��ȭ��ȣ</th>
			<th>�����</th>
		</tr>
		<c:forEach var="member" items="${pi.list}" varStatus="status">
			<tr>
				<td>${member.userIdx}</td>
				<td><a href="view/${member.userId}?page=${pi.page}">
						${member.userId} <iot:newToday test="${member.regDate}" />
				</a></td>
				<td>${member.name}</td>
				<td>${member.email}</td>
				<td>${member.phone}</td>
				<td><fmt:formatDate value="${member.regDate}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
		</c:forEach>
	</table>
	<iot:pagination pageInfo="${pi}" />
</body>
</html>