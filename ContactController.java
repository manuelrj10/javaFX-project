package controller;

import dao.ContactDAO;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Contact;

public class ContactController {

	public void loadData(ObservableList<Contact> list, TableView<Contact> table) {
		list.clear();
		list.addAll(ContactDAO.getAllContacts());
		table.setItems(list);

	}

	// ------------------------------insert-------------------------------------------------
	public void insertData(TextField nameField, TextField phoneField, Label messageLabel) {
		if (nameField.getText().isEmpty()) {
			messageLabel.setText("please enter name");

			return;
		}
		if (phoneField.getText().isEmpty()) {
			messageLabel.setText("please enter phone number");
			return;
		}

		Contact contact = new Contact(nameField.getText(), Long.parseLong(phoneField.getText()));
		ContactDAO.saveContact(contact);

		nameField.clear();
		phoneField.clear();
		messageLabel.setText(" ");
		// messageLabel.setText("data inserted");
		// System.out.println("data inserted");
		// succes alert
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("success");
		alert.setHeaderText("contact saved");
		alert.setContentText("data inserted successfully");
		alert.showAndWait();
	}
	// -------------------------------update----------------------------------------------------

	public void updateData(Contact contactSelected, TextField nameField, TextField phoneField, Label messageLabel) {
		if (contactSelected == null) {
			messageLabel.setText("select a record to update");
			return;
		}
		Contact contact = new Contact(contactSelected.getId(), nameField.getText(),
				Long.parseLong(phoneField.getText()));
		ContactDAO.updateContact(contact);
		nameField.clear();
		phoneField.clear();
		messageLabel.setText(" ");

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("success");
		alert.setHeaderText("contact updated");
		alert.setContentText("data updated successfully");
		alert.showAndWait();
	}

	//---------------------------------delete---------------------------------------------------
	public void delete(Contact selected,Label messageLabel) {
		if(selected==null) {
			messageLabel.setText("select a row to delete");
			return;
		}
		ContactDAO.deleteContact(selected.getId());
		messageLabel.setText(" ");
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("success");
		alert.setHeaderText("contact deleted");
		alert.setContentText("data deleted successfully");
		alert.showAndWait();
	}

}
