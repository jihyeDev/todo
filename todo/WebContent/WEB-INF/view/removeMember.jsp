<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REMOVE MEMBER</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- 구글 폰트 :Nanum+Gothic+Coding -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap" rel="stylesheet">
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
<body>
	<div class="container pt-3">
		<div class="text-center">
			<h1 style="margin-top:150px; color:#A6A6A6;">회원탈퇴</h1>
			${loginMember.memberId}님의 회원 탈퇴를 위해 정확한 PASSWORD를 입력해주세요.
			<form method="post" action="${pageContext.request.contextPath}/member/removeMember">
				<div class="mx-auto m-3" style="width:300px;">
					<input type="hidden" name="memberId" value="${loginMember.memberId}">
					<div><input type="text" name="memberPw" placeholder="P W" class="form-control"></div>
					<div>
						<button type="submit" class="btn mt-3">탈퇴</button>
						<a href="${pageContext.request.contextPath}/member/calendar" class="btn mt-3">캘린더</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>