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
<script
	src="${contextPath}/resources/bower_components/tinymce/tinymce.min.js"></script>
<script src="${contextPath}/recources/css/style.css"></script>
<script>
	$(function() {
		tinymce.init({
			selector : 'textarea',
			language : 'ko_KR',
			height : 200,
			theme_advanced_resizing : false
		});
		
		$(".btn.cmt-list").click(function(){
			$(".table-toggle").toggle();
		});
		
		
		
	});
</script>
<title>Insert title here</title>
</head>
<body>
	<h2 class="my-5">
		<i class="fas fa-file-alt"></i> ${blogBoard.title}
	</h2>
	<div style="overflow: hidden">
		<div class="float-left">작성자 : ${blogBoard.blogHost} &nbsp / &nbsp 조회수 :
			${blogBoard.readCnt}</div>
		<div class="float-right">
			수정일 :
			<fmt:formatDate value="${blogBoard.updateDate}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</div>
	</div>
	<hr />
	${blogBoard.content}
	<hr />


	<!-- 사진 목록 -->
	<c:forEach var="image" items="${blogBoard.list}" varStatus="s">
		<img src="${contextPath}/gallery/image/${image.imageId}" width="400">
		<img src="${contextPath}/gallery/thumb/${image.imageId}">
		<a href="${contextPath}/gallery/download/${image.imageId}"> <i
			class="fas fa-download"></i> 다운로드
		</a>
		<hr />
	</c:forEach>


	<div id="delete-panel"></div>
	<div class="text-center">
		<c:if test="${USER.userId == blogBoard.blogHost}">
			<a href="../edit/${blogBoard.boardId}?page=${param.page}"
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
	<div class="container cmt">

		<h6 class="my-3">
			<button class="btn cmt-list" style="border: 2px solid pink">
				<i class="fas fa-list-ul" style="margin-right: 1em"></i>댓글 목록
				&nbsp[${pi.totalCount}]
			</button>
		</h6>
		<div class="table-toggle">
			<table id="cmt-tb">
				<tbody>
					<c:forEach var="comment" items="${pi.list}">
						<tr class="cmt-tb-writer">
							<td><i class="fas fa-id-card"></i> ${comment.writer}</td>
						</tr>
						<tr class="cmt-tb-content">
							<td>${comment.content}</td>
						</tr>
						<tr class="cmt-tb-regDate">
							<td><fmt:formatDate value="${comment.regDate}"
									pattern="yyyy-MM-dd" /></td>
						</tr>
						<tr class="cmt-tb-replyBtn">
							<td><button>답글</button></td>
						</tr>
						
					</c:forEach>
				</tbody>
			</table>
		</div>

		<iot:pagination pageInfo="${pi}"></iot:pagination>

		<div class="text-left">
			<p>[user id]</p>
			<form method="post"
				action="${contextPath}/gallery/replyCreate/${blogBoard.boardId}">
				<textarea id="txtarea" name="content"> web editor </textarea>
				<input type="submit" class="btn">
			</form>
		</div>
	</div>


</body>
</html>