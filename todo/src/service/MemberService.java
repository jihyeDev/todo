package service;

import java.sql.Connection;
import java.sql.SQLException;

import commons.DBUtil;
import dao.MemberDao;
import dao.TodoDao;
import vo.Member;

public class MemberService {
	private MemberDao memberDao;
	private TodoDao todoDao;
	
	public Member login(Member member) {
		Member loginMember = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3307/todo", "root", "1801");
			memberDao = new MemberDao();
			loginMember = memberDao.login(conn, member);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return loginMember;
	}
	
	// 회원가입시 아이디 중복확인
	public String selectMemberId(String memberIdCheck) {
		String memberId = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3307/todo", "root", "1801");
			memberDao = new MemberDao();
			memberId = memberDao.selectMemberId(conn, memberIdCheck);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberId;
	}
	
	// 회원가입
	public boolean addMember(Member member) {
		boolean result =false;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3307/todo", "root", "1801");
			memberDao = new MemberDao();
			result = memberDao.insertMember(conn, member);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 회원탈퇴 - 트랜잭션 처리
	public boolean removeMember(Member member) {
		boolean result = false;
		Connection conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3307/todo", "root", "1801");
		try {
			conn.setAutoCommit(false);
			todoDao = new TodoDao();
			memberDao = new MemberDao();
			
			todoDao.deleteTodo(conn, member);
			if(memberDao.deleteMember(conn, member) != 1) {
				throw new Exception();
			}
			conn.commit();
			result = true;
		} catch(Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
