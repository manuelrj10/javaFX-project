package ui;

import controller.ContactController;
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
import model.Contact;

public class Home extends Application {
	// table to display contact
	TableView<Contact> table = new TableView<>();
	// list to hold contact dat
	ObservableList<Contact> list = FXCollections.observableArrayList();

	TextField nameField = new TextField();
	TextField phoneField = new TextField();

	Label messageLabel = new Label();

	ContactController controller = new ContactController();

	public void start(Stage stage) {
		nameField.setPromptText("enter Name");
		// phoneField.setPromptText("enter Phone");
		Tooltip tip = new Tooltip("enter the phone number");//
		phoneField.setTooltip(tip);

		TableColumn<Contact, Integer> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(
				c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getId().asObject()));
		TableColumn<Contact, String> colName = new TableColumn<>("Name");
		colName.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getName()));
		TableColumn<Contact, Integer> colPhone = new TableColumn<>("phone");
		colPhone.setCellValueFactory(
				c -> new javafx.beans.property.SimpleLongProperty(c.getValue().getPhone().asObject()));

		table.getColumns().add(colId);
		table.getColumns().add(colName);
		table.getColumns().add(colPhone);

		Button addBTn = new Button("Add");
		Button deleteBTn = new Button("Delete");
		Button UpdateBTn = new Button("Update");

		addBTn.setOnAction(e -> {
			controller.insertData(nameField, phoneField, messageLabel);
			controller.loadData(list, table);
		});
		deleteBTn.setOnAction(e -> {

			controller.deleteData(table.getSelectionModel().getSelectedItem(), messageLabel);
			controller.loadData(list, table);
			nameField.clear();
			phoneField.clear();
		});
		UpdateBTn.setOnAction(e -> {
			controller.UpdateData(table.getSelectionModel().getSelectedItem(), nameField, phoneField, messageLabel);
			controller.loadData(list, table);
		});

		table.setOnMouseClicked(e -> {
			Contact c = table.getSelectionModel().getSelectedItem();
			if (c != null) {
				nameField.setText(c.getName());
				phoneField.setText(String.valueOf(c.getPhone()));
			}
		});
		VBox root = new VBox(10, nameField, phoneField, addBTn, deleteBTn, UpdateBTn, table);
		LoadData();
		stage.setScene(new Scene(root, 400, 450));
		stage.setTitle("contact crud app");
		stage.show();
	}

	void LoadData() {
		list.clear();
		list.addAll(dao.getAllContacts());
		table.setItems(list);

	}

	void insertData() {
		if(nameField.getText().isEmpty()|| phoneField.getText().isEmpty()) {
			System.out.println("fields cannot be empty");
			return;
		}
		dao.addContact(){
			nameField.getText();
			Long.parseLong(phoneField.getText());
			
		}
		LoadData();
		na
	}

}
