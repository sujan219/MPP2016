package com.ems.ui.event;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Event;
import com.ems.baseclasses.Expense;
import com.ems.baseclasses.OffCampusEvent;
import com.ems.baseclasses.OnCampusEvent;
import com.ems.baseclasses.Personnel;
import com.ems.baseclasses.Resource;
import com.ems.baseclasses.Student;
import com.ems.baseclasses.Transport;
import com.ems.ui.AddActionWindow;
import com.ems.ui.DialogUtil;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.GridPane;

public class EventWindow extends AddActionWindow implements EventHandler<ActionEvent>{
	
	public static final String ON_CAMPUS = "OnCampus";
	public static final String OFF_CAMPUS = "OffCampus";
	
	private Parent root;
	private GridPane gridPane;
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
	
	private Button editMgrButton;
	private Button editResourceButton;
	private Button editStudentButton;
	private Button editTransportButton;
	private Button editExpenseButton;
	
	public EventWindow(int id){
		super(id, null);
		try {
			root = FXMLLoader.load(getClass().getResource("EventWindow.fxml"));
			setScene(new Scene(root));
			gridPane = (GridPane) root.lookup(".GridPane");
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
		fundField = (TextField) root.lookup("#fundField");
		personnelListView = (ListView) root.lookup("#managerListView");
		resourceListView = (ListView) root.lookup("#resourceListView");
		studentListView = (ListView) root.lookup("#studentListView");
		expenseListView = (ListView) root.lookup("#expenseListView");
		transportListView = (ListView) root.lookup("#transportListView");
		
		editMgrButton = (Button) root.lookup("#editMgrButton");
		editResourceButton = (Button) root.lookup("#editResourceButton");
		editStudentButton = (Button) root.lookup("#editStudentButton");
		editTransportButton = (Button) root.lookup("#editTransportButton");
		editExpenseButton = (Button) root.lookup("#editExpenseButton");
		editMgrButton.setOnAction(this);
		editResourceButton.setOnAction(this);
		editStudentButton.setOnAction(this);
		editTransportButton.setOnAction(this);
		editExpenseButton.setOnAction(this);
		
		ObservableList<String> cBoxItems = FXCollections.observableArrayList();
		cBoxItems.addAll(ON_CAMPUS, OFF_CAMPUS);
		typeField.setItems(cBoxItems);
		
		if(entityId != 0){
			populateData();
		}else{
			typeField.getSelectionModel().selectFirst();
			showOnCampus();
		}
		
		typeField.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue.equals(ON_CAMPUS)){
					showOnCampus();
				}else{
					showOffCampus();
				}
			}
		});
		
		initAddAction();
	}

	private void showOffCampus(){
		locationField.setVisible(true);
		transportListView.setVisible(true);
		editTransportButton.setVisible(true);
	}
	
	private void showOnCampus(){
		locationField.setVisible(false);
		transportListView.setVisible(false);
		editTransportButton.setVisible(false);
	}
	
	@Override
	public void handle(ActionEvent event) {
		AbstractEventDependenciesWindow window = null;
		ListView listViewToUpdate = null;
		if(event.getSource() == editMgrButton){
			window = new PersonnelDependencyWindow();
			listViewToUpdate = personnelListView;
		}else if(event.getSource() == editStudentButton){
			window = new StudentDependencyWindow();
			listViewToUpdate = studentListView;
		}else if(event.getSource() == editResourceButton){
			window = new ResourceDependencyWindow();
			listViewToUpdate = resourceListView;
		}else if(event.getSource() == editTransportButton){
			window = new TransportDependencyWindow();
			listViewToUpdate = transportListView;
		}else if(event.getSource() == editExpenseButton){
			window = new ExpenseDependencyWindow();
			listViewToUpdate = expenseListView;
		}
		
		final ListView finalListView = listViewToUpdate;
		window.setDependencyAddedListener(new DependencyAddedListener() {
			@Override
			public void dependencyAdded(List<DataObject> dataObjList) {
				ObservableList<DataObject> oList = FXCollections.observableArrayList();
				for(DataObject d:dataObjList){
					oList.add(d);
				}
				finalListView.setItems(oList);
			}
		});
	}

	private void populateData(){
		//TODO
	}
	
	@Override
	public DataObject getActionObject() {
		
		
		List<Personnel> mgrList = getTFromObservable(personnelListView.getItems());
		List<Student> stdList = getTFromObservable(studentListView.getItems());
		List<Expense> expList = getTFromObservable(expenseListView.getItems());
		List<Resource> resList = getTFromObservable(resourceListView.getItems());
		List<Transport> transportList = getTFromObservable(transportListView.getItems());
		
		Event event = null;
		Calendar startDateTime = Calendar.getInstance();
		try {
			String name = nameField.getText();
			String desc = descField.getText();
			String start = startField.getText();
			String end = endField.getText();
			String location = locationField.getText();
			double fund = Double.parseDouble(fundField.getText());
			String type = typeField.getSelectionModel().getSelectedItem();
			
			
			startDateTime.setTime(Event.dateFormat.parse(start));
			Calendar endDateTime = Calendar.getInstance();
			endDateTime.setTime(Event.dateFormat.parse(end));
			if(type.equals(ON_CAMPUS)){
				event = new OnCampusEvent(entityId, name, desc, startDateTime, endDateTime, type, fund);
			}else{
				OffCampusEvent tempEvent = new OffCampusEvent(entityId, name, location, desc, startDateTime, endDateTime, type, fund);
				tempEvent.setTransportList(transportList);
				event = tempEvent;
			}
			event.setEventManagerList(mgrList);
			event.setStudentList(stdList);
			event.setResourceList(resList);
			event.setExpenseList(expList);
			
			return event;
		} catch (ParseException e) {
			e.printStackTrace();
			DialogUtil.showErrorDialog("Invalid date format");
		} catch (NumberFormatException e){
			DialogUtil.showErrorDialog("Invalid numeric value");
		}
		return null;
	}
	
	private static <T> List<T> getTFromObservable(ObservableList<T> oList){
		List<T> returnList = new ArrayList<T>();
		for(T data:oList){
			returnList.add(data);
		}
		return returnList;
	}
}