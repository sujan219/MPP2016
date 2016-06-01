package com.ems.ui.resource;

import java.io.IOException;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Resource;
import com.ems.data.dao.DataReadException;
import com.ems.data.dao.ResourceDao;
import com.ems.ui.AddActionWindow;
import com.ems.ui.DialogUtil;
import com.ems.ui.Refreshable;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ResourceAddWindow extends AddActionWindow{
	
	private TextField nameField;
	private TextField quantityField;
	private TextArea descriptionField;
	private Label messageLabel;
	
	public ResourceAddWindow(int id, Refreshable refreshable) {
		super(id, refreshable);
		setTitle("Resource");
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AddRecord.fxml"));
			setScene(new Scene(root));
			initAddAction();
			
			nameField = (TextField) root.lookup("#nameField");
			quantityField = (TextField) root.lookup("#quantityField");
			descriptionField = (TextArea) root.lookup("#descField");
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
			ResourceDao pdao = new ResourceDao();
			Resource pe = (Resource) pdao.getRecordById(entityId);
			nameField.setText(pe.getName());
			quantityField.setText(pe.getQuantity() + "");
			descriptionField.setText(pe.getDescription());
		} catch (DataReadException e) {
			e.printStackTrace();
			DialogUtil.showErrorDialog(e.getMessage());
		}
	}
	
	@Override
	public DataObject getActionObject() {
		try{
			String name = nameField.getText();
			int quantity = Integer.parseInt(quantityField.getText());
			String description = descriptionField.getText();
			
			if(name.length() == 0){
				messageLabel.setText("Name cannot be empty");
			}else if(description.length() == 0){
				messageLabel.setText("Description cannot be empty");
			}else{
				return new Resource(entityId, name, quantity, description);
			}
		}catch(NumberFormatException e){
			e.printStackTrace();
			messageLabel.setText("Invalid quantity");
		}
		return null;
	}
}
