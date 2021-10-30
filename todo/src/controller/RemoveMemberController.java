package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import vo.Member;

@WebServlet("/member/removeMember")
public class RemoveMemberController extends HttpServlet {
	// 회원 탈퇴를 하기위한 service 호출
	// 두 가지의 쿼리를 실행시키기 위해 처리해 놓은 트랜잭션을 사용하기 위해서
	private MemberService memberService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 탈퇴 페이지, login 되어있지 않다면 접근 불가
		// LoginFilter 필터를 사용 = /member/ 추가
		/*
		HttpSession session = request.getSession();
		if (session.getAttribute("loginMember") == null) { // 로그인이 안되어있을때
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		*/
		
		request.getRequestDispatcher("/WEB-INF/view/removeMember.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// login 되어있지 않다면 요청 처리 불가
		// LoginFilter 필터를 사용 = /member/ 추가
		
		// removeMember Action
		// session에서 ID 값 사용, PASSWORD는 받아옴
		// 값들을 paramMember라는 Member 객체를 생성해 저장
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberId+"<--memberId");
		System.out.println(memberPw+"<--memberPw");
		Member paramMember = new Member();
		paramMember.setMemberId(memberId);
		paramMember.setMemberPw(memberPw);
		// memberService 호출
		memberService = new MemberService();
		if(memberService.removeMember(paramMember)==true) {
			// 회원 탈퇴 성공 시 - logout 페이지로 이동하여서 세션 삭제후에 login 페이지로
			System.out.println(memberId+"님의 회원 탈퇴 완료!");
			response.sendRedirect(request.getContextPath()+"/member/logout");
			return;
		} else {
			response.sendRedirect(request.getContextPath()+"/member/calendar");
		}
	}

}
