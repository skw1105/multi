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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(function() {
		var temp = "${USER.userId}";
		$(".fas.fa-plus.blog.add").click(function(){
			if(temp == null || temp == ""){
				alert("로그인 해주세요");
			}
		}); 
	});
</script>
</head>
<body>
	<h2 class="my-5">
		<i class="fas fa-images"></i>

		<c:if test="${empty pi.list}">빈 블로그 입니다.</c:if>
		<c:if test="${not empty pi.list}">${pi.list[0].blogHost}</c:if>
	</h2>
	<div class="text-right">

		<c:if test="${USER.userId == name}">
			<a href="create?page=${pi.page}"><i class="fas fa-plus blog add"></i>
				추가</a> (총
		: ${pi.totalCount} 건)
		
		</c:if>
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