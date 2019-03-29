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

		$(".btn.cmt-list").click(function() {
			$(".table-toggle").toggle();
		});

		$(".cmt.replyBtn").click(function() {
			var temp = $(this).next();
			if (temp.hasClass("visible")) {
				temp.removeClass("visible");
				temp.css("display", "none");
			} else {
				$(".cmt.replyTiny").removeClass("visible");
				$(".cmt.replyTiny").css("display", "none");
				temp.addClass("visible");
				temp.css("display", "block");
			}
		});
		var checkbox_val = $("input[type=checkbox]").val();
		
		$(".btn.modi").click(function(){
			alert("수정 중입니다.");
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
		<div class="float-left">작성자 : ${blogBoard.blogHost} &nbsp &nbsp
			조회수 : ${blogBoard.readCnt}</div>
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
			<button class="btn modi btn-primary ok text-white"><i class="fas fa-edit"></i>
				수정
			</button>
			<button class="btn modi btn-danger delete">
				<i class="fas fa-trash"></i> 삭제
			</button>
		</c:if>
		<a href="../list?userId=${blogBoard.blogHost}&page=${page}" class="btn btn-primary back"> <i
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
			<table id="cmt-tb" style="width: 100%">
				<tbody>
					<!-- 0 level의 일반 댓글 -->
					<c:forEach var="comment" items="${pi.list}">
						<tr>

							<td>
								<div class="first-section">
									<c:forEach varStatus="sts" begin="1" end="${comment.lv}">
										<c:if test="${sts.index ne 1}">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</c:if>
										<c:if test="${comment.lv>1 and sts.last}">
											<i class="fas fa-sign-in-alt"
												style="font-size: 2em; margin-right: 1em"></i>
										</c:if>
									</c:forEach>
								</div>
								<div class="second-section">
									<i class="fas fa-id-card" style="display: inline"></i>
									<p class="cmt writer">${comment.writer}</p>
									<div class="cmt content">${comment.content}</div>
									<p class="cmt regDate">
										<fmt:formatDate value="${comment.regDate}"
											pattern="yyyy-MM-dd" />
									</p>
									<button class="cmt replyBtn">답글</button>
									<div class="cmt replyTiny" style="display: none">
										<table class="cmt inner-tb">
											<tr>
												<td><i class="fas fa-sign-in-alt"
													style="font-size: 2em; margin-right: 1em"></i></td>
												<td>
													<form method="post"
														action="${contextPath}/gallery/replyCreate/${blogBoard.boardId}/${pi.page}">
														<input type="hidden" id="parentNo" name="parentNo"
															value="${comment.commentNo}">
														<textarea id="txtarea" name="content"></textarea>
														<input type="submit" class="btn">
													</form>
												</td>
											</tr>
										</table>
									</div>
						<hr />
						</div>
						</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<iot:pagination pageInfo="${pi}"></iot:pagination>

		<div class="text-left">
			<p>${blogBoard.blogHost}</p>
			<form method="post"
				action="${contextPath}/gallery/replyCreate/${blogBoard.boardId}/${pi.page}">
				<textarea id="txtarea" name="content"></textarea>
				<input type="submit" class="btn">
			</form>
		</div>
	</div>


</body>
</html>