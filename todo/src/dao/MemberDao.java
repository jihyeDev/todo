package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Member;

public class MemberDao {
	
	// 로그인하는 메서드
	public Member login(Connection conn, Member member) throws SQLException {
		Member loginMember = null;
		String sql = MemberQuery.LOGIN;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		stmt.setString(2, member.getMemberPw());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			loginMember = new Member();
			loginMember.setMemberId(rs.getString("memberId"));
		}
		rs.close();
		stmt.close();
		
		return loginMember;
	}
	
	// 회원가입 시 아이디 중복 검사를하는 메서드
	// 중복확인 할 memberIdCheck 값을 받아와서 SELECT 하고 memberId에 저장하여 리턴
	// 리턴하는 memberId값이 null이면 사용 가능한 ID, 아니라면 이미 사용중인 ID
	public String selectMemberId(Connection conn, String memberIdCheck) throws SQLException {
		String memberId = null;
		String sql = MemberQuery.SELECT_MEMBER_ID;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberIdCheck);
		System.out.println(stmt);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			memberId = rs.getString("memberId");
		}
		stmt.close();
		return memberId;
	}
	
	// 회원가입하는 메서드
	public boolean insertMember(Connection conn, Member member) throws SQLException {
		boolean result = false;
		String sql = MemberQuery.INSERT_MEMBER;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		stmt.setString(2, member.getMemberPw());
		int row = stmt.executeUpdate();
		if(row == 1) {
			result = true;
		}
		System.out.println(stmt);
		stmt.close();
		// 성공 : result = true, 실패 : false
		return result;
	}
	
	// memberId를 받아서 그 멤버를 삭제, 탈퇴하는 메서드
	public int deleteMember(Connection conn, Member member) throws SQLException {
		String sql = MemberQuery.DELETE_MEMBER;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		stmt.setString(2, member.getMemberPw());
		int row = stmt.executeUpdate();
		System.out.println(stmt);
		stmt.close();
		return row;
	}
}
