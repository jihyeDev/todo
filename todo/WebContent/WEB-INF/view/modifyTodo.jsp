<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TO - DO LIST</title>
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

/* Header/Blog Title */
.header {
  padding: 30px;
  font-size: 40px;
  text-align: center;
  background-image: url("${pageContext.request.contextPath}/image/1.png");
  background-size: 100%;
}

/* Create two unequal columns that floats next to each other */
/* Left column */
.leftcolumn {
  float: left;
  width: 80%;
}

/* Right column */
.rightcolumn {
  float: left;
  width: 20%;
  padding-left: 20px;
}

/* Fake image */
.fakeimg {
  background-color: #aaa;
  width: 100%;
  padding: 20px;
}

/* Add a card effect for articles */
.card {
  background-color: white;
  border-radius: 0.75rem;
  padding: 20px;
  margin-top: 20px;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

.row {
  margin-right: 0px;
  margin-left: 0px;
}

/* Footer */
.footer {
  padding: 20px;
  text-align: center;
  background: #71605a;
  margin-top: 20px;
}

/* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 800px) {
  .leftcolumn, .rightcolumn {
    width: 100%;
    padding: 0;
  }
}

/* 지혜 추가 */  
a{
  text-decoration: none;
  color: #A6A6A6;
}

a:hover{
  text-decoration:none;
  color: black;
}

.cal {
  width: 100%;
  background-color: #e2dbc9;
  padding: 20px;
  margin-top: 20px;
  position: relative;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-direction: column;
  flex-direction: column;
  min-width: 0;
  word-wrap: break-word;
  background-clip: border-box;
  border-radius: 0.25rem;
}

.cal table {
  border: 2px solid #e2dbc9;
  margin-top: 5px;
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
		<div class="header">
		  <div class="text-center">
				<h3>${loginMember.memberId} : TO - DO LIST</h3>
			</div>
		</div>
	
		<div class="cal">
			<div class="row mt-3">
			
			  <div class="leftcolumn">
			  
			    <div class="card">
			      	<div class="w-100 text-center mb-3">
						<span style="font-size:24px; font-weight:bold;">수정하기</span>
					</div>
					<form method="post" action="${pageContext.request.contextPath}/member/modifyTodo">
					<!-- ModifyTodoController.doPost() -->
						<div>
							<input type="hidden" name="todoNo" value="${oneTodo.todoNo}">
							<input type="hidden" name="memberId" value="${loginMember.memberId}">
							<input type="hidden" name="todoDate" value="${oneTodo.todoDate}">
							<input type="color" name="fontColor" value="#FBE1E2"> ${oneTodo.todoDate}
							
							<div class="float-right"><button type="submit" class="btn" style="margin-top:-10px;">입력</button></div>
						</div>
						<div>
							<textarea rows="3" cols="50" name="todoContent" class="form-control">${oneTodo.todoContent}</textarea>
						</div>
					</form>
			    </div>
			    
			  </div>
			  
			  <div class="rightcolumn">
			  
			    
			    <div>
					<div class="text-center">
						<a href="${pageContext.request.contextPath}/member/logout" class="btn btn-sm mt-3" style="background-color:#A6A6A6; color:#fff; font-weight:bold;">로그아웃</a>
						<a href="${pageContext.request.contextPath}/member/removeMember" class="btn btn-sm mt-3" style="background-color:#A6A6A6; color:#fff; font-weight:bold;">회원탈퇴</a>
					</div>
			    </div>
			    
			  </div>
			  
			</div>
		</div>
		
		<div class="footer">
		</div>
	</div>
</body>
</html>