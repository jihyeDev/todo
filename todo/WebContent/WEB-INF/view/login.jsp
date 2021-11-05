<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- 구글 폰트 :Nanum+Gothic+Coding -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap" rel="stylesheet">
<!-- sweetAlert2 CDN -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<style>
body {
  padding: 20px;
  background: #f3f4ef;
  font-family: 'Nanum Gothic Coding', monospace;
}

.btn {
  background-color:#a09083;
  color:#fff;
  font-weight:bold
}
</style>
</head>
<body onload="myFunction()">
	<div class="container pt-3">
		<div class="text-center">
			<h1 style="margin-top:150px; color:#A6A6A6;">LOGIN</h1>
			<form method="post" action="${pageContext.request.contextPath}/login">
				<div class="mx-auto m-3" style="width:300px;">
					<div><input type="text" name="memberId" placeholder="I D" class="form-control"></div>
					<div><input type="text" name="memberPw" placeholder="P W" class="form-control"></div>
					<div>
						<button type="submit" class="btn mt-3">로그인</button>
						<a href="${pageContext.request.contextPath}/addMember" class="btn mt-3">회원가입</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
<script>
function myFunction() {
	Swal.fire({
		title:'<h1 style="color:#A6A6A6;">공지사항</h1>',
		html:'<c:forEach var="n" items="${noticeList}"><div>${n.createDate}${n.noticeTitle}</div></c:forEach>'
	})
}
</script>
</html>