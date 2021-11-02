package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;


@WebServlet("/memberIdDuplicate")
public class MemberIdDuplicateController extends HttpServlet {
	private MemberService memberService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입 홈페이지, login이 되지 않았을 때 들어갈 수 있음
		//이미 로그인 되었다면 요청처리 불가
		HttpSession session = request.getSession();
		if (session.getAttribute("loginMember") != null) { // 이미 로그인되어있는 상태다
			response.sendRedirect(request.getContextPath()+"/member/calendar");
			return;
		}
		
		// memberIdDuplicate Action
		// 중복검사한뒤 값 저장
		String memberIdCheck = request.getParameter("memberIdCheck");
		// 디버깅
		System.out.println(memberIdCheck+"<--memberIdCheck");
		
		// MemberService 호출
		memberService = new MemberService();
		String result = memberService.selectMemberId(memberIdCheck);

		// result 값이 null이면 사용 가능한 ID, 아니라면 이미 사용중인 ID
		if(result == null) {
			System.out.println("사용 가능한 ID");
			response.sendRedirect(request.getContextPath()+"/addMember?memberIdCheck="+memberIdCheck);
		} else {
			System.out.println("사용 불가능한 ID");
			response.sendRedirect(request.getContextPath()+"/addMember?idCheckResult=Y");
		}
	}

}
