<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
		<c:forEach var="gallery" items="${pi.list}">
			<tr>
				<td>${gallery.galleryId}</td>
				<td><a href="view/${gallery.galleryId}?page=${pi.page}">
						${gallery.title} <span class="badge badge-pill badge-success">
							${gallery.list.size()}</span> <iot:newToday test="${gallery.regDate}" />
				</a></td>
				<td>${gallery.owner}</td>
				<td>${gallery.readCnt}</td>
				<td><fmt:formatDate value="${gallery.regDate}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
		</c:forEach>
	</table>
	<iot:pagination pageInfo="${pi}" />
</body>
</html>