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

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="row" style="margin-left:0px; margin-right:0px">
		<c:forEach var="membership" items="${ml}">
			<div class="card bg-light col-sm-12 col-md-6 col-lg-4">
				<a class="nav-link" href="${contextPath}/gallery/list?userId=${membership.userId}">
					<img class="card-img-top"
						src="${contextPath}/resources/img/google.jpg" alt="Card image"
						 style="height:20em" />
				</a>
				<div class="card-img">
					<h4 class="card-title">${membership.userId}</h4>
					<p class="card-text">${membership.explanation}</p>
				</div>
			</div> <!-- card -->
		</c:forEach>
	</div> <!-- row -->
</body>
</html>