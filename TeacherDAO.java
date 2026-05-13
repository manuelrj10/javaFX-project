package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.Teacher;

public class TeacherDAO {
	public static void add(Teacher teacher) {
		String Query = "insert into teachers(teacher_name,teacher_phone,teacher_salary)values(?,?,?)";
		try (Connection conn = DBConnection.getConnetion(); PreparedStatement pstmt = conn.prepareStatement(Query)) {
			pstmt.setString(1, teacher.getTeacher_name());
			pstmt.setString(2, teacher.getTeacher_phone());
			pstmt.setDouble(3, teacher.getTeacher_salary());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void update(Teacher t) {
		String query = "UPDATE teachers SET teacher_name=?, teacher_phone=?, teacher_salary=? WHERE teacher_id=?";
		try (Connection conn = DBConnection.getConnetion(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, t.getTeacher_name());
			pstmt.setString(2, t.getTeacher_phone());
			pstmt.setDouble(3, t.getTeacher_salary());
			pstmt.setInt(4, t.getTeacher_id());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void delete(Teacher teacher) {
		String query = "DELETE FROM teachers WHERE teacher_id=?";
		try (Connection conn = DBConnection.getConnetion(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, teacher.getTeacher_id());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static List<Teacher> view() {
		List<Teacher> teachers = new ArrayList<>();
		String query = "SELECT * FROM teachers";
		try (Connection conn = DBConnection.getConnetion();
				PreparedStatement pstmt=conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery(query)) {
			while (rs.next()) {
				teachers.add(new Teacher(rs.getInt("teacher_id"), rs.getString("teacher_name"),
						rs.getString("teacher_phone"), rs.getDouble("teacher_salary")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teachers;
	}
}
