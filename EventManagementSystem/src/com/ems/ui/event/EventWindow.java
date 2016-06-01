package com.ems.ui.event;

import java.io.IOException;

import com.ems.baseclasses.Expense;
import com.ems.baseclasses.Personnel;
import com.ems.baseclasses.Resource;
import com.ems.baseclasses.Student;
import com.ems.baseclasses.Transport;
import com.ems.ui.ModalDialog;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EventWindow extends ModalDialog {
	
	public static final String ON_CAMPUS = "OnCampus";
	public static final String OFF_CAMPUS = "OffCampus";
	
	private Parent root;
	private TextField nameField;
	private TextArea descField;
	private TextField startField;
	private TextField endField;
	private ComboBox<String> typeField;
	private TextField locationField;
	private TextField fundField;
	private ListView<Personnel> personnelListView;
	private ListView<Resource> resourceListView;
	private ListView<Student> studentListView;
	private ListView<Expense> expenseListView;
	private ListView<Transport> transportListView;
	
	public EventWindow(){
		try {
			root = FXMLLoader.load(getClass().getResource("EventWindow.fxml"));
			setScene(new Scene(root));
			show();
			initControls();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setTitle("Event");
	}
	
	private void initControls(){
		nameField = (TextField) root.lookup("#nameField");
		descField = (TextArea) root.lookup("#descField");
		startField = (TextField) root.lookup("#startDateTimeField");
		endField = (TextField) root.lookup("#endDateTimeField");
		typeField = (ComboBox<String>) root.lookup("#typeField");
		locationField = (TextField) root.lookup("#locationField");
		fundField = (TextField) root.lookup("#nameField");
		personnelListView = (ListView) root.lookup("#personnelListView");
		resourceListView = (ListView) root.lookup("#resourceListView");
		studentListView = (ListView) root.lookup("#studentListView");
		expenseListView = (ListView) root.lookup("#expenseListView");
	}
}
