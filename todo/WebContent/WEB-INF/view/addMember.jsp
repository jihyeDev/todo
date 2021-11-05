<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADD MEMBER</title>
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
			<h1 style="margin-top:150px; color:#A6A6A6;">회원가입</h1>
			<!-- 멤버 아이디가 사용가능한지 중복 확인하는 폼 -->
			<form method="post" action="${pageContext.request.contextPath}/memberIdDuplicate">
				<div class="mx-auto m-3">
					<table class="mx-auto" style="width:300px;">
						<tr>
							<td style="width:30px;">I D</td>
							<td>
								<div class="input-group">
									<input type="text" name="memberIdCheck" class="form-control">
									<div class="input-group-append">
				      					<button type="submit" class="btn btn-outline-secondary">중복확인</button>
				    				</div>
				    			</div>
							</td>
						</tr>
						<tr>
							<td colspan="2"><small style="font-size:12px;">${result}</small></td>
						</tr>
					</table>
				</div>
			</form>
			<form method="post" action="${pageContext.request.contextPath}/addMember">
				<div class="mx-auto m-3">
					<table class="mx-auto" style="width:300px;">
						<tr>
							<td style="width:30px;">I D</td>
							<td><input type="text" name="memberId" class="form-control" value="${memberIdCheck}"></td>
						</tr>
						<tr>
							<td style="width:30px;">P W -</td>
							<td><input type="text" name="memberPw" class="form-control"></td>
						</tr>
					</table>
					<div>
						<button type="submit" class="btn mt-3">가입</button>
						<a href="${pageContext.request.contextPath}/login" class="btn mt-3">로그인</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>