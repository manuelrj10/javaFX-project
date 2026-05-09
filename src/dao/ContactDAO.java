package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.Contact;

public class ContactDAO {
	Connection conn=null;
	PreparedStatement pstmt=null;
	
	//add
	public void addContact(Contact contact) {
		try {
			conn=DBConnection.getConnection();
			pstmt=conn.prepareStatement("insert into contact(contact_name,contact_phone)"
					+ "values(?,?)");
			pstmt.setString(1,contact.getName());
			pstmt.setLong(2,contact.getPhone());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	//view
	public List<Contact> getAllContacts() {
		List<Contact> contactList=new ArrayList<>();
		try {
			conn=DBConnection.getConnection();
			pstmt=conn.prepareStatement("select * from contact");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				contactList.add(new Contact(rs.getInt("contact_id"),
						rs.getString("contact_name"),
						rs.getLong("contact_phone")));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return contactList;
		
		
		
		
	}
	public void deleteContact(int id) {
		try {
			conn=DBConnection.getConnection();
			pstmt=conn.prepareStatement("delete from contact where contact_id=?");
			pstmt.setInt(1,id);
			pstmt.executeUpdate();
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateContact(Contact contact) {
		try {
			conn=DBConnection.getConnection();
			pstmt=conn.prepareStatement("Update contact set contact_name=?,contact_phone=? where contact_id=?");
			pstmt.setString(1,contact.getName());
			pstmt.setLong(2,contact.getPhone());
			pstmt.setInt(3,contact.getId());
			pstmt.executeUpdate();			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
