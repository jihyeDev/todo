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


@WebServlet("/addMember")
public class AddMemberController extends HttpServlet {
	
	private MemberService memberService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입 홈페이지, login이 되지 않았을 때 들어갈 수 있음
		//이미 로그인 되었다면 요청처리 불가
		HttpSession session = request.getSession();
		if (session.getAttribute("loginMember") != null) { // 이미 로그인되어있는 상태다
			response.sendRedirect(request.getContextPath()+"/member/calendar");
			return;
		}
		
		// 아이디 중복 검사를 완료한 memberIdCheck = 공백값이거나, 중복체크를 완료한 사용가능한 아이디 값
		// 중복 값인지 검사하고 다시 이 페이지로 보내서 저장함
		// memberIdCheck에 저장된 값을 ID 입력칸에 value로 넣어줌
		String memberIdCheck = "";
		if(request.getParameter("memberIdCheck") != null){
			memberIdCheck = request.getParameter("memberIdCheck");
		}
		String result = "";
		if(request.getParameter("idCheckResult") == null & memberIdCheck != "") {
			result ="* 사용할 수 있는 ID 입니다.";
		} else if(request.getParameter("idCheckResult") == null) {
			result ="* 사용하려는 ID를 입력한 뒤 중복확인을 해주세요.";
		} else if(request.getParameter("idCheckResult").equals("Y")){
			result ="* 중복된 ID 입니다. 다시 입력해주세요.";
		}
		
		request.setAttribute("memberIdCheck", memberIdCheck);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입 홈페이지, login이 되지 않았을 때 들어갈 수 있음
		//이미 로그인 되었다면 요청처리 불가
		HttpSession session = request.getSession();
		if (session.getAttribute("loginMember") != null) { // 이미 로그인되어있는 상태다
			response.sendRedirect(request.getContextPath()+"/member/calendar");
			return;
		}
		
		// addMember Action : insert 할 member 값들을 받아와서 데이터 추가하는 doPost()
		// 값들을 paramMember라는 member 객체를 생성해 저장
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		Member paramMember = new Member();
		paramMember.setMemberId(memberId);
		paramMember.setMemberPw(memberPw);
		// 디버깅
		System.out.println("paramMember : "+paramMember);
		// memberService 호출
		memberService = new MemberService();
		if(memberService.addMember(paramMember)==true) {
			System.out.println("회원가입 성공!");
		}
		// 회원가입이 끝난뒤 - login 페이지로 이동함
		response.sendRedirect(request.getContextPath()+"/login");
	}

}
