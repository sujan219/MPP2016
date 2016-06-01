package com.ems.ui.event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Expense;
import com.ems.baseclasses.Personnel;
import com.ems.baseclasses.Resource;
import com.ems.baseclasses.Student;
import com.ems.baseclasses.Transport;
import com.ems.ui.ModalDialog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	
	private List<Personnel> personnelList;
	private List<Resource> resourceList;
	private List<Student> studentList;
	private List<Expense> expenseList;
	private List<Transport> transportList;
	
	private Button editMgrButton;
	
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
		personnelListView = (ListView) root.lookup("#managerListView");
		resourceListView = (ListView) root.lookup("#resourceListView");
		studentListView = (ListView) root.lookup("#studentListView");
		expenseListView = (ListView) root.lookup("#expenseListView");
		
		personnelList = new ArrayList<Personnel>();
		resourceList = new ArrayList<Resource>();
		studentList = new ArrayList<Student>();
		expenseList = new ArrayList<Expense>();
		transportList = new ArrayList<Transport>();
		
		editMgrButton = (Button) root.lookup("#editMgrButton");
		editMgrButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new PersonnelDependencyWindow().setDependencyAddedListener(new DependencyAddedListener() {
					@Override
					public void dependencyAdded(List<DataObject> dataObjList) {
						personnelList.removeAll(personnelList);
						ObservableList<Personnel> oList = FXCollections.observableArrayList();
						for(DataObject d:dataObjList){
							personnelList.add((Personnel)d);
							oList.add((Personnel)d);
						}
						
						personnelListView.setItems(oList);
					}
				});;
			}
		});
	}
}
