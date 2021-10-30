package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TodoService;
import vo.Member;
import vo.Todo;

@WebServlet("/member/removeTodo")
public class RemoveTodoController extends HttpServlet {
	// todo 수정을 하기위한 service 호출
   private TodoService todoService;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   // login 되어있지 않다면 요청 처리 불가
	   // LoginFilter 필터를 사용 = /member/ 추가
	   String memberId = ((Member)(request.getSession().getAttribute("loginMember"))).getMemberId();
	   int todoNo = Integer.parseInt(request.getParameter("todoNo"));
	   String todoDate = request.getParameter("todoDate");
	   // 디버깅
	   System.out.println(memberId+"<--memberId");
	   System.out.println(todoNo+"<--todoNo");
	   System.out.println(todoDate+"<--todoDate");
	   
	   Todo todo = new Todo();
	   todo.setMemberId(memberId);
	   todo.setTodoNo(todoNo);
	   
	   // todoService 호출
	   todoService = new TodoService();
	   todoService.removeTodoList(todo);
      
	   // todoDate 
	   String[]arrDate = todoDate.split("-");
	   for(int i = 0; i < arrDate.length; i++) {
		   System.out.println(i + " : " + arrDate[i]);
		   }
	   response.sendRedirect(request.getContextPath()+"/member/todoList?y="+arrDate[0]+"&m="+arrDate[1]+"&d="+arrDate[2]);
   	}

}