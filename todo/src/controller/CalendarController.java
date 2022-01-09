package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CalendarService;
import service.TodoService;
import vo.Member;
import vo.Todo;

@WebServlet("/member/calendar")
public class CalendarController extends HttpServlet {
	private CalendarService calendarService;
	private TodoService todoService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// LoginFilter 필터를 사용
		/*
		HttpSession session = request.getSession();
		if (session.getAttribute("loginMember") == null) { // 로그인이 안되어있을때
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		*/
		
		// 캘린더
		String currentYear = request.getParameter("currentYear");
		String currentMonth = request.getParameter("currentMonth");
		String option = request.getParameter("option");
		
		// 디버깅
		System.out.println("currentYear : "+currentYear);
		System.out.println("currentMonth : "+currentMonth);
		System.out.println("option : "+option);
		
		calendarService = new CalendarService();
		String memberId = ((Member)request.getSession().getAttribute("loginMember")).getMemberId();
		// 디버깅
		System.out.println("memberId : "+memberId);
		
		Map<String, Object> map = calendarService.getTargetCalendar(memberId, currentYear, currentMonth, option);
		
		// 모델
		request.setAttribute("targetYear", map.get("targetYear"));
		request.setAttribute("targetMonth", map.get("targetMonth"));
		request.setAttribute("endDay", map.get("endDay"));
		// 달력을 출력할 때 앞/뒤 필요한 공백 <td>
		request.setAttribute("startBlank", map.get("startBlank"));
		request.setAttribute("endBlank", map.get("endBlank"));
		// 달력에 출력할 todo 모델 목록
		request.setAttribute("todoList", map.get("todoList"));
		
		// 오늘의 일정
		Calendar c = Calendar.getInstance(); // 오늘 날짜의 년도와 월을 가진다
		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH) + 1;
		String strm = "";
		if(m<10) {
			strm = "0"+m;
		}
		int d = c.get(Calendar.DATE);
		String todoDate = y+"-"+strm+"-"+d;
		Todo todo = new Todo();
		todo.setTodoDate(todoDate);
		todo.setMemberId(memberId);
		System.out.println("todo : "+todo);

		todoService = new TodoService();
		List<Todo> todayTodoList = todoService.getTodoListByDate(todo);
		System.out.println("todayTodoList : "+todayTodoList);

		request.setAttribute("todayTodoList", todayTodoList);

		
		request.getRequestDispatcher("/WEB-INF/view/calendar.jsp").forward(request, response);
	}

}
