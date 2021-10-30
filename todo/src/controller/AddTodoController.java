package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TodoService;
import vo.Todo;

@WebServlet("/member/addTodo")
public class AddTodoController extends HttpServlet {
	
	private TodoService todoService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// login 되어있지 않다면 요청 처리 불가
		// LoginFilter 필터를 사용 = /member/ 추가
		
		// addTodo Action : insert 할 todo 값들을 받아와서 데이터 추가하는 doPost()
		// session에서 ID 값 사용, 나머지 데이터는 받아옴
		// 값들을 paramTodo라는 Todo 객체를 생성해 저장
		String memberId = request.getParameter("memberId");
		String todoDate = request.getParameter("todoDate");
		String todoContent = request.getParameter("todoContent");
		String fontColor = request.getParameter("fontColor");
		Todo paramTodo = new Todo();
		paramTodo.setMemberId(memberId);
		paramTodo.setTodoDate(todoDate);
		paramTodo.setTodoContent(todoContent);
		paramTodo.setFontColor(fontColor);
		// 디버깅
		System.out.println("paramTodo : "+paramTodo);
		// todoService 호출
		todoService = new TodoService();
		if(todoService.addTodo(paramTodo)==true) {
			System.out.println("todo 입력 성공!");
		}
		// todo 입력이 끝난 뒤 - todoList 페이지로 이동함
		// split 사용하여 String 나누기
		/*
		 * // subString() 사용하였을 때
		 * String y = todoDate.substring(0,4);
		 * String m = todoDate.substring(5,7);
		 * String d = todoDate.substring(8,10);
		 */
		String[]arrDate = todoDate.split("-");
		for(int i = 0; i < arrDate.length; i++) {
			System.out.println(i + " : " + arrDate[i]);
		}
		response.sendRedirect(request.getContextPath()+"/member/todoList?y="+arrDate[0]+"&m="+arrDate[1]+"&d="+arrDate[2]);
	}

}
