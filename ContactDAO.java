package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.Contact;

public class ContactDAO {

	// load data

	// add
	public static void saveContact(Contact contact) {
		String sql = "INSERT INTO contact (contact_name, contact_phone) VALUES (?, ?)";

		// Try-with-resources automatically closes connection and statement
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, contact.getName());
			pstmt.setLong(2, contact.getPhone());

			int rowsAffected = pstmt.executeUpdate();
			System.out.println("Saved " + rowsAffected + " contact(s) successfully.");

		} catch (SQLException e) {
			// Log the error or rethrow a custom exception

			e.printStackTrace();
		}
	}

	// view
	public static List<Contact> getAllContacts() {
		List<Contact> contactList = new ArrayList<>();
		String sql = "select * from contact";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				contactList.add(new Contact(rs.getInt("contact_id"), rs.getString("contact_name"),
						rs.getLong("contact_phone")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return contactList;

	}

	public static void deleteContact(int id) {
		String Query = "delete from contact where contact_id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(Query)) {

			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateContact(Contact contact) {
		String Query = "Update contact set contact_name=?,contact_phone=? where contact_id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(Query)) {

			pstmt.setString(1, contact.getName());
			pstmt.setLong(2, contact.getPhone());
			pstmt.setInt(3, contact.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
