package controller;


import dao.TeacherDAO;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import model.Teacher;

public class TeacherController {
	public void loadData(ObservableList<Teacher> list, TableView<Teacher> table) {
		list.clear();
		list.addAll(TeacherDAO.view());
		table.setItems(list);

	}

	public void addTeacher(TextField nameField, TextField phoneField, TextField salaryField, Label messageLabel) {
		if (nameField.getText().isEmpty() || phoneField.getText().isEmpty() || salaryField.getText().isEmpty()) {
	        messageLabel.setText("Please fill all fields");
	        return;
	    }
		Teacher t = new Teacher(nameField.getText(), phoneField.getText(), Double.parseDouble(salaryField.getText()));
		TeacherDAO.add(t);

		nameField.clear();
		phoneField.clear();
		salaryField.clear();
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("success");
		alert.setHeaderText("teacher saved");
		alert.setContentText("data inserted successfully");
		alert.showAndWait();

	}

	public void deleteTeacher(Teacher selectedItem, Label messageLabel) {
		if (selectedItem == null) {
			messageLabel.setText("please select a row");
			return;
		}
		Teacher t = new Teacher(selectedItem.getTeacher_id());
		TeacherDAO.delete(t);
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("succes");
		alert.setHeaderText("teacher deleted");
		alert.setContentText("data deleted successfully");
		alert.showAndWait();

	}

	public void updateTeacher(Teacher selectedItem, TextField nameField, TextField phoneField, TextField salaryField,
			Label messageLabel) {
		if (selectedItem == null) {
			messageLabel.setText("please select a row");
			return;
		}
		Teacher t = new Teacher(selectedItem.getTeacher_id(), nameField.getText(), phoneField.getText(),
				Double.parseDouble(salaryField.getText()));
		TeacherDAO.update(t);
		nameField.clear();
		phoneField.clear();
		salaryField.clear();
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("succes");
		alert.setHeaderText("teacher updated");
		alert.setContentText("data updated successfully");
		alert.showAndWait();
	}

}
