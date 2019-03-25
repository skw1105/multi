<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="iot"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2 class="my-5">
		<i class="fas fa-images"></i> 갤러리 목록
	</h2>
	<div class="text-right">
		<a href="create?page=${pi.page}"><i class="fas fa-plus"></i> 추가</a> (총
		: ${pi.totalCount} 건)
	</div>
	<table class="table table-striped table-hover">
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>등록일</th>
		</tr>
		<c:forEach var="blog" items="${pi.list}">
			<tr>
				<td>${blog.boardId}</td>
				<td><a href="view/${blog.boardId}?page=${pi.page}">
						${blog.title} <span class="badge badge-pill badge-success">
							${blog.list.size()}</span> <iot:newToday test="${blog.regDate}" />
				</a></td>
				<td>${blog.blogHost}</td>
				<td>${blog.readCnt}</td>
				<td><fmt:formatDate value="${blog.regDate}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
		</c:forEach>
	</table>
	<iot:pagination pageInfo="${pi}" />
</body>
</html>