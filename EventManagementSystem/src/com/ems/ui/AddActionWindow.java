package com.ems.ui;

import com.ems.baseclasses.DataObject;
import com.ems.data.dao.DataSaveException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public abstract class AddActionWindow extends ModalDialog {
	
	protected int entityId;
	private Refreshable refreshable;
	
	protected AddActionWindow(int id, Refreshable refreshable){
		entityId = id;
		this.refreshable = refreshable;
	}
	
	protected void initAddAction(){
		Button addButton = (Button) getScene().lookup(".button");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DataObject actionObject = getActionObject();
				if(actionObject != null){
					try {
						if(entityId == 0){
							actionObject.saveNewData();
							DialogUtil.showSuccessDialog("Record added");
						}else{
							actionObject.modifyData();
							DialogUtil.showSuccessDialog("Record modified");
						}
						refreshable.refresh();
						AddActionWindow.this.close();
					} catch (DataSaveException e) {
						e.printStackTrace();
						DialogUtil.showErrorDialog();
					}
				}
			}
		});
	}
	
	public abstract DataObject getActionObject();
}
