package com.ems.ui;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import com.ems.ui.event.EventWindow;
import com.ems.ui.personnel.PersonnelListWindow;
import com.ems.ui.resource.ResourceListWindow;
import com.ems.ui.student.StudentListWindow;
import com.ems.ui.transport.TransportListWindow;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MainWindow extends Stage implements EventHandler<ActionEvent>{
	
	private Stage primaryStage;
	private MenuItem personnelMenuItem;
	private MenuItem transportMenuItem;
	private MenuItem resourceMenuItem;
	private MenuItem studentMenuItem;
	private MenuItem createEventMenu;
	
	public MainWindow(Stage aPrimaryStage){
		primaryStage = aPrimaryStage;
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
			setTitle("Event management system");
			
			MenuBar menuBar = (MenuBar) root.lookup("#menuBar");
			initMenuItems(menuBar);
			
			setScene(new Scene(root, 700, 500));
			show();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			new EventWindow();
		}
	}
}
