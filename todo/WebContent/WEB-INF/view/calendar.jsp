<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CALENDAR</title>
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
  background-size: 100% 100%;
}

/* Create two unequal columns that floats next to each other */
/* Left column */
.leftcolumn {
  float: left;
  width: 20%;
}

/* Right column */
.rightcolumn {
  float: left;
  width: 80%;
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

#circle{
  width:100%;
  height:215px;
  border-radius:50%;
  background:#a09083;
  text-align:center;
  padding-top:30px;
  color:#f3f4ef;
  font-weight:bolder;
}

#navigator {
  padding-top:15px;
  width:100%;
  height:50px;
}
</style>
</head>
<body>
	<div class="container pt-3">
		<div class="header">
		  <div class="text-center">
				<h3>
					<a href="${pageContext.request.contextPath}/member/calendar" style="color: black;">
					${loginMember.memberId} : TO - DO LIST
					</a>
				</h3>
			</div>
		</div>
		
		<div class="cal">
		<div class="row">
		
		<div class="leftcolumn">
		  	<div id="circle">
		  		<!-- 달력의 년도와 월이 나오는 곳 -->
		  		<div style="font-size:20px; margin-bottom:-30px;">${targetYear}</div>
		  		<span style="font-size:110px;">${targetMonth}</span>
		  		<!-- ${targetYear}년 ${targetMonth}월 -->
		  	</div>
		  	
		  	<div id="navigator">
		  		<!-- 이전, 다음 버튼 -->
		  		<span class="float-left"><a href="${pageContext.request.contextPath}/member/calendar?currentYear=${targetYear}&currentMonth=${targetMonth}&option=pre" class="btn"> &lt; 이전 </a></span>
		  		<span class="float-right"><a href="${pageContext.request.contextPath}/member/calendar?currentYear=${targetYear}&currentMonth=${targetMonth}&option=next" class="btn"> 다음 > </a></span>
		  	</div>
		  
		    <div class="card text-center">
		    	이달의 total todo
		      	<div style="font-weight:bold; font-size:25px;">${todoList.size()}</div>
		    </div>
		    
		    <div class="card">
		    	<div>오늘의 일정</div>
		    	<div>
			    	<c:forEach var="t" items="${todayTodoList}">
			    		<div>
			    			<span style="color:${t.fontColor};">● </span> ${t.todoContent}
						</div>
					</c:forEach>
				</div>
		    </div>
		    <div>
				<div class="text-center">
					<a href="${pageContext.request.contextPath}/member/logout" class="btn btn-sm mt-3" style="background-color:#A6A6A6; color:#fff; font-weight:bold;">로그아웃</a>
					<a href="${pageContext.request.contextPath}/member/removeMember" class="btn btn-sm mt-3" style="background-color:#A6A6A6; color:#fff; font-weight:bold;">회원탈퇴</a>
				</div>
		    </div>
		    
		  </div>
		
		  <div class="rightcolumn">
		  
		    <div class="card">
		    	<!--
			    <div class="w-100 text-center">
					<span class="float-left"><a href="${pageContext.request.contextPath}/member/calendar?currentYear=${targetYear}&currentMonth=${targetMonth}&option=pre" class="btn"> &lt; 이전 </a></span>
					<span>${targetYear}년 ${targetMonth}월</span>
					<span class="float-right"><a href="${pageContext.request.contextPath}/member/calendar?currentYear=${targetYear}&currentMonth=${targetMonth}&option=next" class="btn"> 다음 > </a></span>
				</div>
				 -->
				
				<table border="1">
					<tr class="text-center">
						<td style="width:14%; color:red;">S</td>
						<td style="width:14%;">M</td>
						<td style="width:14%;">T</td>
						<td style="width:14%;">W</td>
						<td style="width:14%;">T</td>
						<td style="width:14%;">F</td>
						<td style="width:14%; color:blue;">S</td>
					</tr>
					<tr>
						<!-- JSTL for문 -->
						<c:forEach var="i" begin="1" end="${startBlank+endDay+endBlank}" step="1">
			            <c:if test="${i-startBlank >= 1 && i-startBlank<=endDay}">
			            	<td style="height:100px; vertical-align:top">
			            		<a href="${pageContext.request.contextPath }/member/todoList?y=${targetYear}&m=${targetMonth }&d=${i-startBlank}">${i-startBlank}</a>
			            		<div>
				            		<!-- 날짜별 일정 -->
				            		<c:forEach var="todo" items="${todoList}">
				            			<c:if test="${i-startBlank == todo.todoDate.substring(8)}">
				            				<div><span style="color:${todo.fontColor};">● </span><span style="font-size:13px;">${todo.todoContent}</span></div>
				            			</c:if>
				            		</c:forEach>
			            		</div>
			            	</td>
			            </c:if>
			            <c:if test="${i-startBlank < 1 || i-startBlank>endDay}">
			            	<td>&nbsp;</td>
			            </c:if>
			            
			            <c:if test="${i%7 == 0}">
			            	</tr><tr>
			            </c:if>
			            </c:forEach>
					</tr>
				</table>
		    </div>
		    
		  </div>
		  
		 </div>
		  
		</div>
		
		<div class="footer">
		</div>
	</div>
	
</body>
</html>