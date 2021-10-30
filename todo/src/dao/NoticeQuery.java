package dao;

public class NoticeQuery {
	public static final String SELECT_NOTICE_LIST5;
	static {
		SELECT_NOTICE_LIST5 ="SELECT notice_no noticeNo, notice_title noticeTitle, create_date createDate, update_date updateDate FROM notice ORDER BY create_date DESC LIMIT 0,5";
	}
}
