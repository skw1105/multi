<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="${contextPath}/resources/bower_components/tinymce/tinymce.min.js"></script>
<script>
$(function() {
	tinymce.init({
		selector : 'textarea',
		theme : "modern",
		language : 'ko_KR',
		height : 200,
        theme_advanced_resizing : false
    });
});
</script>
<title>Insert title here</title>
</head>
<body>
	<h2 class="my-5">
		<i class="fas fa-file-alt"></i> ${gallery.title}
	</h2>
	<div style="overflow: hidden">
		<div class="float-left">작성자 : ${gallery.owner}, 조회수 :
			${gallery.readCnt}</div>
		<div class="float-right">
			수정일 :
			<fmt:formatDate value="${gallery.updateDate}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</div>
	</div>
	<hr />
	${gallery.description}
	<hr />


	<!-- 사진 목록 -->
	<c:forEach var="image" items="${gallery.list}" varStatus="s">
		<img src="${contextPath}/gallery/image/${image.imageId}" width="400">
		<img src="${contextPath}/gallery/thumb/${image.imageId}">
		<a href="${contextPath}/gallery/download/${image.imageId}"> <i
			class="fas fa-download"></i> 다운로드
		</a>
		<hr />
	</c:forEach>


	<div id="delete-panel"></div>
	<div class="text-center">
		<c:if test="${USER.userId == gallery.owner}">
			<a href="../edit/${gallery.galleryId}?page=${param.page}"
				class="btn btn-primary ok text-white"> <i class="fas fa-edit"></i>
				수정
			</a>
			<button class="btn btn-danger delete">
				<i class="fas fa-trash"></i> 삭제
			</button>
		</c:if>
		<a href="../list?page=${page}" class="btn btn-primary back"> <i
			class="fas fa-undo"></i> 목록
		</a>
	</div>
	<hr />
	<div class="container">
		<h5 class="my-3">
			댓글 목록
			</h2>
			<table class="table table-striped table-hover">
				<tr>
					<th>No</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>등록일</th>
				</tr>
				<c:forEach var="board" items="${pi.list}">
					<tr>
						<td>${board.boardId}</td>
						<td><a href="view/${board.boardId}?page=${pi.page}">
								${board.title}</a> <iot:newToday test="${board.regDate}" /></td>
						<td>${board.writer}</td>
						<td>${board.readCnt}</td>
						<td><fmt:formatDate value="${board.regDate}"
								pattern="yyyy-MM-dd" /></td>
					</tr>
				</c:forEach>
			</table>
	</div>
	<div class="text-left">
		<h5 class="my-3"> 댓글을 달아주세요 </h5>
		<form:form modelAttribute="reply">
			
		</form:form>
		<textarea> web editor </textarea>
	</div>



</body>
</html>