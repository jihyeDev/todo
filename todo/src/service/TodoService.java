package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import commons.DBUtil;
import dao.TodoDao;
import vo.Todo;

public class TodoService {
	private TodoDao todoDao;
	
	public List<Todo> getTodoListByDate(Todo todo) {
		List<Todo> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3307/todo", "root", "1801");
			todoDao = new TodoDao();
			list = todoDao.selectTodoListByDate(conn, todo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// todo의 입력,추가
	// todoDao의 insertTodo가 정상 실행되면 true 리턴, 예외 시 예외문 리턴
	public boolean addTodo(Todo todo) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3307/todo", "root", "1801");
			todoDao = new TodoDao();
			result = todoDao.insertTodo(conn, todo);
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
	
	// todo의 수정
	// todoDao의 modifyTodo가 정상 실행되면 true 리턴, 예외 시 예외문 리턴
	public boolean modifyTodo(Todo todo) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3307/todo", "root", "1801");
			todoDao = new TodoDao();
			result = todoDao.updateTodo(conn, todo);
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
	
	// 특정 하나의 todo의 정보를 불러옴
	// todo 객체 리턴
	public Todo getTodoOne(int todoNo) {
		Todo todo = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3307/todo", "root", "1801");
			todoDao = new TodoDao();
			todo = todoDao.selectTodoOne(conn, todoNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return todo;
	}
	
	// todo의 삭제
	public void removeTodoList(Todo todo) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3307/todo", "root", "1801");
			todoDao = new TodoDao();
			todoDao.deleteTodoList(conn, todo);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
