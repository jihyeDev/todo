package dao;

public class TodoQuery {
	public static final String INSERT_TODO;
	public static final String UPDATE_TODO;
	public static final String DELETE_TODO;
	public static final String DELETE_TODO_LIST;
	public static final String SELECT_TODO_ONE;
	public static final String SELECT_TODO_LIST_BY_DATE;
	public static final String SELECT_TODO_LIST_BY_MONTH;
	static {
		INSERT_TODO = "INSERT INTO todo (member_id, todo_date, todo_content, font_color, create_date, update_date) values (?, ?, ?, ?, now(), now())";
		UPDATE_TODO = "UPDATE todo SET todo_content=?, font_color=?, update_date=now() WHERE todo_no=? AND member_id=?";
		DELETE_TODO = "DELETE FROM todo WHERE member_id=?";
		DELETE_TODO_LIST = "DELETE FROM todo WHERE member_id=? AND todo_no=?";
		SELECT_TODO_ONE = "SELECT todo_no todoNo, todo_date todoDate, todo_content todoContent, font_color fontColor, create_date createDate, update_date updateDate FROM todo WHERE todo_no=?";
		SELECT_TODO_LIST_BY_DATE = "SELECT todo_no todoNo, todo_date todoDate, todo_content todoContent, font_color fontColor, create_date createDate, update_date updateDate FROM todo WHERE todo_date=? AND member_id=? ORDER BY todo_date ASC";
		SELECT_TODO_LIST_BY_MONTH = "SELECT todo_date todoDate, SUBSTR(todo_content, 1, 7) todoContent5, font_color fontColor FROM todo WHERE member_id=? AND SUBSTR(todo_date, 1, 7)=? ORDER BY todo_date ASC";
	}
}
