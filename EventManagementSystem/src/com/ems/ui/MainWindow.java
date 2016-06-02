package com.ems.ui;

import java.io.IOException;
import java.util.List;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Event;
import com.ems.data.dao.DataReadException;
import com.ems.data.dao.EventDao;
import com.ems.ui.event.EventWindow;
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
			
			loadEvents();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadEvents() {
		pendingEvents.getChildren().removeAll(pendingEvents.getChildren());
		EventDao dao = new EventDao();
		try {
			List<DataObject> eventList = dao.getAllRecords();
			for(DataObject dObj:eventList){
				addPendingEvent((Event)dObj);
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
		hbox.getChildren().add(editButton);
		HBox.setMargin(editButton, new Insets(15));
		pendingEvents.getChildren().add(hbox);
		pendingEvents.setMargin(hbox, new Insets(10));
		
		editButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				new EventWindow(event.getId(), MainWindow.this);
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
