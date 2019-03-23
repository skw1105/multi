<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="${contextPath}/resources/bower_components/axios/dist/axios.min.js"></script>
<script>
$(function(){
 	$('#delete-panel').deletePanel({
		triger : '.delete',
		url : '../delete/${member.userId}',
		moveUrl : '../list?page=${param.page}'
	});
});
</script>

<h2 class="my-5">
	<i class="fas fa-file-alt"></i> ${board.title}
</h2>
<div style="overflow:hidden">
	<div class="float-left">작성자 : ${board.writer}, 조회수 :
		${board.readCnt}</div>
	<div class="float-right">
		수정일 :
		<fmt:formatDate value="${board.updateDate}"
			pattern="yyyy-MM-dd HH:mm:ss" />
	</div>
</div>
<hr />
${board.content}
<hr />

<div id="delete-panel"></div>

<div class="text-center">
	<c:if test="${USER.userId == board.writer}">
		<a href="../edit/${board.boardId}?page=${param.page}"
			class="btn btn-primary ok text-white"> <i class="fas fa-edit"></i>
			수정
		</a>
		<button class="btn btn-danger delete">
			<i class="fas fa-trash"></i> 삭제
		</button>
	</c:if>
	<a href="../list?page=${param.page}" class="btn btn-primary back">
		<i class="fas fa-undo"></i> 목록
	</a>
</div>




