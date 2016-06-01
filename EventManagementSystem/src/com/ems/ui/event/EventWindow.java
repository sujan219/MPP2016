package com.ems.ui.event;

import java.io.IOException;

import com.ems.ui.ModalDialog;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class EventWindow extends ModalDialog {
	
	public EventWindow(){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("EventWindow.fxml"));
			setScene(new Scene(root));
			show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setTitle("Event");
	}
}
