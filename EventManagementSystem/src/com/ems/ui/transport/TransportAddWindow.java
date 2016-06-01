package com.ems.ui.transport;

import java.io.IOException;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Student;
import com.ems.baseclasses.Transport;
import com.ems.data.dao.DataReadException;
import com.ems.data.dao.TransportDao;
import com.ems.ui.AddActionWindow;
import com.ems.ui.DialogUtil;
import com.ems.ui.Refreshable;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class TransportAddWindow extends AddActionWindow{

	private TextField nameField;
	private TextArea descriptionField;
	private TextField capacityField;
	private Label messageLabel;
	
	public TransportAddWindow(int id, Refreshable refreshable) {
		super(id, refreshable);
		setTitle("Transport");
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AddRecord.fxml"));
			setScene(new Scene(root));
			initAddAction();
			nameField = (TextField) root.lookup("#txtTransportName");
			descriptionField = (TextArea) root.lookup("#txtTransportDesc");
			capacityField = (TextField) root.lookup("#txtTransportCapacity");
			messageLabel = (Label) root.lookup("#lblMessage");
			if(id != 0){
				populateData();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void populateData() {
		try {
			TransportDao tdao = new TransportDao();
			Transport transport = (Transport)tdao.getRecordById(entityId);
			nameField.setText(transport.getName());
			descriptionField.setText(transport.getDescription());
			capacityField.setText(transport.getCapacity() + "");
		} catch (DataReadException e) {
			e.printStackTrace();
			DialogUtil.showErrorDialog(e.getMessage());
		}
	}

	@Override
	public DataObject getActionObject() {
		try	{
			String name = nameField.getText();
			String description = descriptionField.getText();
			int capacity = Integer.parseInt(capacityField.getText());
			if(name.length() == 0){
				messageLabel.setText("Name cannot be empty");
			}else if(capacity == 0){
				messageLabel.setText("Capacity cannot be empty");
			}
			else if(description.length() == 0){
				messageLabel.setText("Description cannot be empty");
			}else{
				return new Transport(entityId, name, description, capacity);
			}
		}catch(Exception e)	{
			e.printStackTrace();
			messageLabel.setText("Invalid quantity");
		}
		return null;
		
		
	}
}
