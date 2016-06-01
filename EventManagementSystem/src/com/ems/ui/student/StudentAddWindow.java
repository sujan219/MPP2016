package com.ems.ui.student;

import java.io.IOException;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Personnel;
import com.ems.baseclasses.Student;
import com.ems.data.dao.DataReadException;
import com.ems.data.dao.StudentDao;
import com.ems.ui.AddActionWindow;
import com.ems.ui.DialogUtil;
import com.ems.ui.Refreshable;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StudentAddWindow extends AddActionWindow{
	
	private TextField nameField;
	private TextField emailField;
	private TextField entryField;
	private Label messageLabel;
	
	public StudentAddWindow(int id, Refreshable refreshable) {
		super(id, refreshable);
		setTitle("Personnel");
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AddRecord.fxml"));
			setScene(new Scene(root));
			initAddAction();
			
			nameField = (TextField) root.lookup("#nameField");
			emailField = (TextField) root.lookup("#emailField");
			entryField = (TextField) root.lookup("#entryField");
			messageLabel = (Label) root.lookup("#message");
			
			if(id != 0){
				populateData();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void populateData(){
		try {
			StudentDao pdao = new StudentDao();
			Student pe = (Student) pdao.getRecordById(entityId);
			nameField.setText(pe.getName());
			emailField.setText(pe.getEmail());
			entryField.setText(pe.getEntry());
		} catch (DataReadException e) {
			e.printStackTrace();
			DialogUtil.showErrorDialog(e.getMessage());
		}
	}
	
	@Override
	public DataObject getActionObject() {
		String name = nameField.getText();
		String email = emailField.getText();
		String entry = entryField.getText();
		
		if(name.length() == 0){
			messageLabel.setText("Name cannot be empty");
		}else if(email.length() == 0){
			messageLabel.setText("Description cannot be empty");
		}else if(entry.length() == 0){
			messageLabel.setText("Entry cannot be empty");
		}else{
			return new Student(entityId, name, email, entry);
		}
		return null;
	}
}
