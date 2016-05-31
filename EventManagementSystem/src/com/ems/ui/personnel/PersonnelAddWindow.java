package com.ems.ui.personnel;

import java.io.IOException;

import com.ems.ui.AddActionWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class PersonnelAddWindow extends AddActionWindow{
	
	public PersonnelAddWindow() {
		setTitle("Personnel");
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AddRecord.fxml"));
			setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addAction() {
		
	}
}
