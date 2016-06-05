package com.ems.ui;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Event;
import com.ems.data.dao.DataReadException;
import com.ems.data.dao.DataSaveException;
import com.ems.data.dao.EventDao;
import com.ems.ui.event.EventWindow;
import com.ems.ui.event.ReportWindow;
import com.ems.ui.personnel.PersonnelListWindow;
import com.ems.ui.resource.ResourceListWindow;
import com.ems.ui.student.StudentListWindow;
import com.ems.ui.transport.TransportListWindow;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainWindow extends Stage implements EventHandler<ActionEvent>, Refreshable{
	
	private Stage primaryStage;
	private MenuItem personnelMenuItem;
	private MenuItem transportMenuItem;
	private MenuItem resourceMenuItem;
	private MenuItem studentMenuItem;
	private MenuItem createEventMenu;
	
	private VBox pendingEvents;
	private VBox pastEvents;
	
	public MainWindow(Stage aPrimaryStage){
		primaryStage = aPrimaryStage;
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
			setTitle("Event management system");

			setScene(new Scene(root, 700, 500));
			
			MenuBar menuBar = (MenuBar) root.lookup("#menuBar");
			initMenuItems(menuBar);
			
			show();
			pendingEvents = (VBox) root.lookup("#pendingEventsVBox");
			pastEvents = (VBox) root.lookup("#pastEventsVBox");
			loadEvents();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadEvents() {
		pendingEvents.getChildren().removeAll(pendingEvents.getChildren());
		pastEvents.getChildren().removeAll(pendingEvents.getChildren());
		EventDao dao = new EventDao();
		try {
			List<DataObject> eventList = dao.getAllRecords();
			for(DataObject dObj:eventList){
				Event event = (Event)dObj;
				Calendar cal = event.getEndDateTime();
				if(cal.getTimeInMillis() > Calendar.getInstance().getTimeInMillis()){
					addPendingEvent(event);
				}else{
					addPastEvent(event);
				}
			}
		} catch (DataReadException e) {
			e.printStackTrace();
		}
	}
	
	private void addPendingEvent(final Event event){
		HBox hbox = new HBox();
		VBox vbox = new VBox();
		Label eventName = new Label(event.getName() + " : " + event.getLocation());
		eventName.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		String startDate = Event.dateFormat.format(event.getStartDateTime().getTime());
		String endDate = Event.dateFormat.format(event.getEndDateTime().getTime());
		vbox.getChildren().addAll(eventName, new Label("From: " + startDate), new Label("To: " + endDate));
		hbox.getChildren().add(vbox);
		
		Button editButton = new Button("Edit");
		Button cancelButton = new Button("Cancel");
		hbox.getChildren().addAll(editButton, cancelButton);
		HBox.setMargin(editButton, new Insets(15));
		HBox.setMargin(cancelButton, new Insets(15));
		pendingEvents.getChildren().add(hbox);
		pendingEvents.setMargin(hbox, new Insets(10));
		
		editButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				new EventWindow(event.getId(), MainWindow.this);
			}
		});
		
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(DialogUtil.showConfirmDialog("Are you sure to delete?")){
					try {
						event.deleteData();
						refresh();
					} catch (DataSaveException e1) {
						e1.printStackTrace();
						DialogUtil.showErrorDialog(e1.getMessage());
					}
				}
			}
		});
	}
	
	private void addPastEvent(final Event event){
		HBox hbox = new HBox();
		VBox vbox = new VBox();
		Label eventName = new Label(event.getName() + " : " + event.getLocation());
		eventName.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		String startDate = Event.dateFormat.format(event.getStartDateTime().getTime());
		String endDate = Event.dateFormat.format(event.getEndDateTime().getTime());
		vbox.getChildren().addAll(eventName, new Label("From: " + startDate), new Label("To: " + endDate));
		hbox.getChildren().add(vbox);
		
		Button reportButton = new Button("Report");
		hbox.getChildren().add(reportButton);
		HBox.setMargin(reportButton, new Insets(15));
		pastEvents.getChildren().add(hbox);
		pastEvents.setMargin(hbox, new Insets(10));
		
		reportButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
//				new ReportWindow(event);
			}
		});
	}

	private void initMenuItems(MenuBar menuBar){
		
		Menu fileMenu = new Menu("File");
		Menu recordMenu = new Menu("Record");
		
		menuBar.getMenus().add(fileMenu);
		menuBar.getMenus().add(recordMenu);
		
		createEventMenu = new MenuItem("Create New Event");
		fileMenu.getItems().add(createEventMenu);
		
		personnelMenuItem = new MenuItem("Personnel");
		transportMenuItem = new MenuItem("Transport");
		resourceMenuItem = new MenuItem("Resource");
		studentMenuItem = new MenuItem("Student");
		recordMenu.getItems().addAll(personnelMenuItem, transportMenuItem, resourceMenuItem, studentMenuItem);
		
		personnelMenuItem.setOnAction(this);
		transportMenuItem.setOnAction(this);
		resourceMenuItem.setOnAction(this);
		studentMenuItem.setOnAction(this);
		createEventMenu.setOnAction(this);
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == personnelMenuItem){
			new PersonnelListWindow();
		}else if(event.getSource() == transportMenuItem){
			new TransportListWindow();
		}else if(event.getSource() == resourceMenuItem){
			new ResourceListWindow();
		}else if(event.getSource() == studentMenuItem){
			new StudentListWindow();
		}else if(event.getSource() == createEventMenu){
			new EventWindow(0, this);
		}
	}

	@Override
	public void refresh() {
		loadEvents();
	}
}
