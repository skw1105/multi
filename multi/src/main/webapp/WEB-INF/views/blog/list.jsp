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
	<div class="row outer">
		<%-- <div class="col-sm-2">
			<div class="card-deck-container">
				<form class="card-deck form-group">
					<div class="row">
						<label for="userId" class="mr-sm-2">아이디 : </label> <input
							type="text" placeholder="insert id" class="form-control mr-sm-2"
							id="userId">
					</div>
					<br>
					<div class="row">
						<label for="pwd" class="mr-sm-2">패스워드 : </label> <input
							type="password" class="form-control mr-sm-2" id="pwd">
					<br>
					</div>
					
					<button type="submit" class="btn btn-primary">로그인</button>
				</form>
			</div>
		</div> --%>
		<div class="col-sm-12">
			<div class="card-deck-container">
				<div class="card-deck">
					<div class="card bg-light">
					
						<a href="namisum.html"><img class="card-img-top"
							src="${contextPath}/resources/img/photo.jpg" alt="Card image" width="5" height = "10"/></a>
						<div class="card-img">
							<h4 class="card-title">안창균</h4>
							<p class="card-text">안녕하세요. </p>
							<i class="fas fa-apple-alt"></i> <a href="#"
								class="btn move-homepage" target="_blank">www.naver.com</a>
						</div>
					</div>
					<div class="card bg-light">
						<img class="card-img-top" src="${contextPath}/resources/img/BT.jpg" alt="Card image">
						<div class="card-img">
							<h4 class="card-title">정병태</h4>
							<p class="card-text">안녕하세요. </p>
							<i class="fas fa-apple-alt"></i> <a href="#"
								class="btn move-homepage" target="_blank">www.naver.com</a>
						</div>
					</div>
					<div class="card bg-light">
						<img class="card-img-top" src="${contextPath}/resources/img/google.jpg" alt="Card image">
						<div class="card-img">
							<h4 class="card-title">백상우</h4>
							<p class="card-text">안녕하세요.</p>
							<i class="fas fa-apple-alt"></i> <a href="#"
								class="btn move-homepage" target="_blank">www.naver.com</a>
						</div>
					</div>
					
					<div class="card bg-light">
						<img class="card-img-top" src="../IMG/Tulips.jpg" alt="Card image">
						<div class="card-img">
							<h4 class="card-title">심규원</h4>
							<p class="card-text">안녕하세요. 여기는 남이섬입니다.</p>
							<i class="fas fa-apple-alt"></i> <a href="#"
								class="btn move-homepage" target="_blank">www.naver.com</a>
						</div>
					</div>
					
				</div>
				<div class="card-deck">
					<div class="card bg-light">
						<img class="card-img-top" src="../IMG/Tulips.jpg" alt="Card image">
						<div class="card-img">
							<h4 class="card-title">심규원</h4>
							<p class="card-text">안녕하세요. 여기는 남이섬입니다.</p>
							<i class="fas fa-apple-alt"></i> <a href="#"
								class="btn move-homepage" target="_blank">www.naver.com</a>
						</div>
					</div>
					<div class="card bg-light">
						<img class="card-img-top" src="../IMG/Tulips.jpg" alt="Card image">
						<div class="card-img">
							<h4 class="card-title"></h4>
							<p class="card-text">안녕하세요. 여기는 남이섬입니다.</p>
							<i class="fas fa-apple-alt"></i> <a href="#"
								class="btn move-homepage" target="_blank">www.naver.com</a>
						</div>
					</div>
					<div class="card bg-light">
						<img class="card-img-top" src="../IMG/Tulips.jpg" alt="Card image">
						<div class="card-img">
							<h4 class="card-title">남이섬</h4>
							<p class="card-text">안녕하세요. 여기는 남이섬입니다.</p>
							<i class="fas fa-apple-alt"></i> <a href="#"
								class="btn move-homepage" target="_blank">www.naver.com</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>