package dao;

public class MemberQuery {
	// 쿼리 관리를 편리하게 하기 위해 쿼리를 분리함
	
	public static final String LOGIN;
	public static final String DELETE_MEMBER;
	static { // static 필드를 초기화 시킬 때 사용
		LOGIN = "SELECT member_id memberId FROM member WHERE member_id=? AND member_pw=?";
		DELETE_MEMBER = "DELETE FROM member WHERE member_id=? AND member_pw=?";
	}
}
