package ui;

import controller.TeacherController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Teacher;

public class Home extends Application {
	TextField nameField = new TextField();
	TextField phoneField = new TextField();
	TextField salaryField = new TextField();
	Label messageLabel = new Label();
	TableView<Teacher> table = new TableView<>();
	ObservableList<Teacher> list = FXCollections.observableArrayList();

	public void start(Stage stage) {
		nameField.setPromptText("name");
		nameField.setTooltip(new Tooltip("enetr the name"));
		phoneField.setPromptText("phone numebr");
		phoneField.setTooltip(new Tooltip("enter the phone numebr"));
		salaryField.setPromptText("salary");
		salaryField.setTooltip(new Tooltip("enter the salary"));
		TableColumn<Teacher, Integer> colId = new TableColumn<>();
		colId.setCellValueFactory(
				c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getTeacher_id()).asObject());
		TableColumn<Teacher, String> colName = new TableColumn<>();
		colName.setCellValueFactory(
				c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getTeacher_name()));
		TableColumn<Teacher, String> colPhone = new TableColumn<>();
		colPhone.setCellValueFactory(
				c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getTeacher_phone()));
		TableColumn<Teacher, Double> colSalary = new TableColumn<>();
		colSalary.setCellValueFactory(
				c -> new javafx.beans.property.SimpleDoubleProperty(c.getValue().getTeacher_salary()).asObject());
		table.getColumns().addAll(colId, colName, colPhone, colSalary);
		Button addBtn = new Button("add");
		Button delBtn = new Button("delete");
		Button upddateBtn = new Button("update");

		TeacherController controller = new TeacherController();
		addBtn.setOnAction(ac -> {
			controller.addTeacher(nameField, phoneField, salaryField, messageLabel);
			controller.loadData(list,table);
			nameField.clear();
			phoneField.clear();
			salaryField.clear();

		});
		delBtn.setOnAction(ac -> {
			controller.deleteTeacher(table.getSelectionModel().getSelectedItem(), messageLabel);
			controller.loadData(list,table);

		});
		upddateBtn.setOnAction(ac -> {
			controller.updateTeacher(table.getSelectionModel().getSelectedItem(), nameField, phoneField, salaryField,
					messageLabel);
			controller.loadData(list,table);
		});

		table.setOnMouseClicked(e -> {
			Teacher t = table.getSelectionModel().getSelectedItem();
			if (t != null) {
				nameField.setText(t.getTeacher_name());
				phoneField.setText(t.getTeacher_phone());
				salaryField.setText(String.valueOf(t.getTeacher_salary()));
			}
		});
		VBox root = new VBox(10, nameField, phoneField,salaryField, messageLabel, addBtn, delBtn, upddateBtn, table);
		stage.setScene(new Scene(root, 400, 450));
		stage.setTitle("contact crud app");
		stage.show();

	}

}
