package com.ems.ui;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import com.ems.ui.personnel.PersonnelListWindow;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MainWindow extends Stage{
	private Stage primaryStage;
	public MainWindow(Stage aPrimaryStage){
		primaryStage = aPrimaryStage;
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
			setTitle("Event management system");
			
			MenuBar menuBar = (MenuBar) root.lookup("#menuBar");
			initMenuItems(menuBar);
			
			setScene(new Scene(root));
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
		
		MenuItem createEventMenu = new MenuItem("Create New Event");
		MenuItem viewPastMenu = new MenuItem("View Past Events");
		fileMenu.getItems().add(createEventMenu);
		fileMenu.getItems().add(viewPastMenu);
		
		MenuItem personnelMenuItem = new MenuItem("Personnel");
		MenuItem transportMenuItem = new MenuItem("Transport");
		MenuItem resourceMenuItem = new MenuItem("Resource");
		MenuItem studentMenuItem = new MenuItem("Student");
		recordMenu.getItems().addAll(personnelMenuItem, transportMenuItem, resourceMenuItem, studentMenuItem);
		
		personnelMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new PersonnelListWindow();
			}
		});
	}
}
