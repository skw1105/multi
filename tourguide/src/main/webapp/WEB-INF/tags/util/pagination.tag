<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="pageInfo" required="true" type="edu.autocar.domain.PageInfo"%>
<script>
$(function(){
	/* $(".page-link").click(function(){
		if($(this).parents().hasClass("cmt")){
			if(!$(this).parent().hasClass("active")){
				alert("test");
				$(this).attr("href","?cmtPage=${i}")
			}
		}else{
			if(!$(this).parent().hasClass("active")){
				$(this).attr("href","?page=${i}")
			}
		}
	}); */
});	

</script>
<ul class="pagination justify-content-center">
	<c:forEach var="i" begin="1" end="${pageInfo.totalPage}">
		<c:choose>
			<c:when test="${i == pageInfo.page}">
				<li class="page-item active">
					<a class="page-link" href="#">${i}</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="page-item">
					<a class="page-link" href="?page=${i}">${i}</a>
				</li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</ul>