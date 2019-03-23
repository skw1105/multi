<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<a class="navbar-brand" href="#"> 
		 <i class="fas fa-list-ul"></i> 블로그 목록 보기
	</a>
	

	<div class="collapse navbar-collapse" id="collapsibleNavbar">
		<ul class="navbar-nav ml-auto">
			<c:if test="${empty USER}">
				<li class="nav-item"><a class="nav-link"
					href="${contextPath}/join"> <i class="fas fa-user-plus"></i>
						회원가입
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${contextPath}/login"> <i class="fas fa-sign-in-alt"></i>
						로그인
				</a></li>
			</c:if>
			<c:if test="${not empty USER}">
			<li class="nav-item"><a class="nav-link"
					href="${contextPath}/member/view"> <i class="fas fa-address-book"></i>
						My Blog
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${contextPath}/member/view"> <i class="fas fa-user"></i>
						${USER.userId}
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${contextPath}/logout"> <i class="fas fa-sign-out-alt"></i>
						로그아웃
				</a></li>
			</c:if>
		</ul>
	</div>
</nav>