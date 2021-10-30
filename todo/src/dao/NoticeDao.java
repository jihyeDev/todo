package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Notice;

public class NoticeDao {
	public List<Notice> selectNoticeList5(Connection conn) throws SQLException {
		List<Notice> list = new ArrayList<>();
		String sql = NoticeQuery.SELECT_NOTICE_LIST5;
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Notice n = new Notice();
			n.setNoticeNo(rs.getInt("noticeNo"));
			n.setNoticeTitle(rs.getString("noticeTitle"));
			n.setCreateDate(rs.getString("createDate"));
			list.add(n);
		}
		rs.close();
		stmt.close();
		return list;
	}
}
