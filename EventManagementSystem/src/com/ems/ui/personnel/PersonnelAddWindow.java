package com.ems.ui.personnel;

import java.io.IOException;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Personnel;
import com.ems.data.dao.DataReadException;
import com.ems.data.dao.PersonnelDao;
import com.ems.ui.AddActionWindow;
import com.ems.ui.DialogUtil;
import com.ems.ui.Refreshable;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PersonnelAddWindow extends AddActionWindow{
	
	private TextField nameField;
	private TextArea descField;
	private CheckBox isMgrField;
	private CheckBox isDriverField;
	private Label messageLabel;
	
	public PersonnelAddWindow(int id, Refreshable refreshable) {
		super(id, refreshable);
		setTitle("Personnel");
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AddRecord.fxml"));
			setScene(new Scene(root));
			initAddAction();
			
			nameField = (TextField) root.lookup("#nameField");
			descField = (TextArea) root.lookup("#descField");
			isMgrField = (CheckBox) root.lookup("#isMgrField");
			isDriverField = (CheckBox) root.lookup("#isDriverField");
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
			PersonnelDao pdao = new PersonnelDao();
			Personnel pe = (Personnel) pdao.getRecordById(entityId);
			nameField.setText(pe.getName());
			descField.setText(pe.getDescription());
			isMgrField.setSelected(pe.isEventManager());
			isDriverField.setSelected(pe.isDriver());
		} catch (DataReadException e) {
			e.printStackTrace();
			DialogUtil.showErrorDialog(e.getMessage());
		}
	}
	
	@Override
	public DataObject getActionObject() {
		String name = nameField.getText();
		String description = descField.getText();
		boolean isEventManager = isMgrField.isSelected();
		boolean isDriver = isDriverField.isSelected();
		
		if(name.length() == 0){
			messageLabel.setText("Name cannot be empty");
		}else if(description.length() == 0){
			messageLabel.setText("Description cannot be empty");
		}else{
			return new Personnel(entityId, name, description, isEventManager, isDriver);
		}
		return null;
	}
}
