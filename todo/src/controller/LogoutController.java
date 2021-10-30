package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/logout")
public class LogoutController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// LoginFilter 필터를 사용
		/*
		HttpSession session = request.getSession();
		if (session.getAttribute("loginMember") == null) { // 로그인이 안되어있을때
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		*/
		
		request.getSession().invalidate();
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
}
