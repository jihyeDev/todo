package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TodoService;
import vo.Todo;

@WebServlet("/member/modifyTodo")
public class ModifyTodoController extends HttpServlet {
	// todo 수정을 하기위한 service 호출
	private TodoService todoService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// todo 수정 페이지, login 되어있지 않다면 접근 불가
		// LoginFilter 필터를 사용 = /member/ 추가
		/*
		HttpSession session = request.getSession();
		if (session.getAttribute("loginMember") == null) { // 로그인이 안되어있을때
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		*/
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		Todo todo = new Todo();
		todo.setTodoNo(todoNo);
		
		todoService = new TodoService();
		Todo oneTodo = todoService.getTodoOne(todoNo);
		
		request.setAttribute("oneTodo", oneTodo);
		request.getRequestDispatcher("/WEB-INF/view/modifyTodo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// login 되어있지 않다면 요청 처리 불가
		// LoginFilter 필터를 사용 = /member/ 추가
		
		// modifyTodo Action
		// session에서 ID 값 사용, todoNo과 수정할 todoContent, fontColor는 받아옴
		// 값들을 paramTodo라는 Todo 객체를 생성해 저장
		String todoContent = request.getParameter("todoContent");
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		String memberId = request.getParameter("memberId");
		String todoDate = request.getParameter("todoDate");
		String fontColor = request.getParameter("fontColor");
		// 디버깅
		System.out.println(todoContent+"<--todoContent");
		System.out.println(todoNo+"<--todoNo");
		System.out.println(memberId+"<--memberId");
		System.out.println(todoDate+"<--todoDate");
		
		Todo paramTodo = new Todo();
		paramTodo.setTodoContent(todoContent);
		paramTodo.setTodoNo(todoNo);
		paramTodo.setMemberId(memberId);
		paramTodo.setFontColor(fontColor);
		
		// todoService 호출
		todoService = new TodoService();
		if(todoService.modifyTodo(paramTodo)==true) {
			// 회원 탈퇴 성공 시 - logout 페이지로 이동하여서 세션 삭제후에 login 페이지로
			System.out.println("todo 수정 완료!");
		} else {
			System.out.println("todo 수정 실패!");
		}
		String[]arrDate = todoDate.split("-");
		for(int i = 0; i < arrDate.length; i++) {
			System.out.println(i + " : " + arrDate[i]);
		}
		response.sendRedirect(request.getContextPath()+"/member/todoList?y="+arrDate[0]+"&m="+arrDate[1]+"&d="+arrDate[2]);
	}

}
