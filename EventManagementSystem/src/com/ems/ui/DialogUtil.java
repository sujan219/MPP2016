package com.ems.ui;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class DialogUtil {
	
	public static void showErrorDialog(){
		showErrorDialog("Error occured while processing request.");
	}
	
	public static void showErrorDialog(String message){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ERROR!");
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	public static void showSuccessDialog(){
		showSuccessDialog("The process completed successfully.");
	}
	
	public static void showSuccessDialog(String message){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success!");
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	public static boolean showConfirmDialog(){
		return showConfirmDialog("Are you sure to delete?");
	}
	
	public static boolean showConfirmDialog(String msg){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm");
		alert.setContentText(msg);

		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;
	}
}
