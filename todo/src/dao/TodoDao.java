package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Member;
import vo.Todo;

public class TodoDao {
	
	// 회원 탈퇴시 member_id를 받아서 그 회원과 관련된 todo도 같이 삭제됨
	// 하나의 커넥션을 같이 사용해서 하나의 서비스로 사용함
	public void deleteTodo(Connection conn, Member member) throws SQLException {
		String sql = TodoQuery.DELETE_TODO;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		stmt.executeUpdate();
		System.out.println(stmt);
		stmt.close();
	}
	
	// todo List를 select 하는 메서드
	public List<Todo> selectTodoListByDate(Connection conn, Todo todo) throws SQLException {
		List<Todo> list = new ArrayList<Todo>();
		String sql = TodoQuery.SELECT_TODO_LIST_BY_DATE;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getTodoDate());
		stmt.setString(2, todo.getMemberId());
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Todo t = new Todo();
			t.setTodoNo(rs.getInt("todoNo"));
			t.setTodoDate(rs.getString("todoDate"));
			t.setTodoContent(rs.getString("todoContent"));
			t.setFontColor(rs.getString("fontColor"));
			t.setCreateDate(rs.getString("createDate"));
			t.setUpdateDate(rs.getString("updateDate"));
			list.add(t);
		}
		return list;
	}
	
	// todo를 입력, 추가하는 메서드
	public boolean insertTodo(Connection conn, Todo todo) throws SQLException {
		boolean result = false;
		String sql = TodoQuery.INSERT_TODO;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getMemberId());
		stmt.setString(2, todo.getTodoDate());
		stmt.setString(3, todo.getTodoContent());
		stmt.setString(4, todo.getFontColor());
		int row = stmt.executeUpdate();
		if(row == 1) {
			result = true;
		}
		System.out.println(stmt);
		stmt.close();
		// 성공 : result = true, 실패 : false
		return result;
	}
	
	public List<Todo> selectTodoListByMonth(Connection conn, Todo todo) throws SQLException {
		List<Todo> list = new ArrayList<Todo>();
		String sql = TodoQuery.SELECT_TODO_LIST_BY_MONTH;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getMemberId());
		stmt.setString(2, todo.getTodoDate().substring(0, 7));
		System.out.println(stmt);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Todo t = new Todo();
			t.setTodoDate(rs.getString("todoDate"));
			t.setTodoContent(rs.getString("todoContent5"));
			t.setFontColor(rs.getString("fontColor"));
			list.add(t);
			}
		return list;
		}
	
	// todo를 수정하는 메서드
	public boolean updateTodo(Connection conn, Todo todo) throws SQLException {
		boolean result = false;
		String sql = TodoQuery.UPDATE_TODO;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getTodoContent());
		stmt.setString(2, todo.getFontColor());
		stmt.setInt(3, todo.getTodoNo());
		stmt.setString(4, todo.getMemberId());
		int row = stmt.executeUpdate();
		if(row == 1) {
			result = true;
		}
		System.out.println(stmt);
		stmt.close();
		// 성공 : result = true, 실패 : false
		return result;
	}
	
	// 특정 하나의 todo의 정보를 불러오는 메서드
	public Todo selectTodoOne(Connection conn, int todoNo) throws SQLException {
		// Todo 객체를 사용하기 위해 null로 초기화
		Todo todo = null;
		
		// 매개변수 값을 디버깅
		System.out.println(todoNo+" <-- 매개변수 todoNo");
		
		String sql = TodoQuery.SELECT_TODO_ONE;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, todoNo);
		// 디버깅
		System.out.println(stmt);
		
		// 데이터 가공 (자료구조화)
		// ResultSet이라는 특수한 타입에서 객체라는 일반화된 타입으로 변환(가공)
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			// todo 객체 생성 후 저장
			todo = new Todo();
			todo.setTodoNo(todoNo);
			todo.setTodoDate(rs.getString("todoDate"));
			todo.setTodoContent(rs.getString("todoContent"));
			todo.setFontColor(rs.getString("fontColor"));
		}
		stmt.close();
		// todo를 return
		return todo;
	}
	
	// todo를 삭제하는 메서드
	public void deleteTodoList(Connection conn, Todo todo) throws SQLException {
		String sql = TodoQuery.DELETE_TODO_LIST;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getMemberId());
		stmt.setInt(2, todo.getTodoNo());
		
		// 디버깅
		System.out.println(stmt);
		
		stmt.executeUpdate();
		stmt.close();
   }
}
