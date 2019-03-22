<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="pageInfo" required="true"
	type="edu.autocar.domain.PageInfo"%>

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