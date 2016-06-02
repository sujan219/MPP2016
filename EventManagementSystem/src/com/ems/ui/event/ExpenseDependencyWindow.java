package com.ems.ui.event;

import java.util.ArrayList;
import java.util.List;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Expense;
import com.ems.baseclasses.Resource;
import com.ems.ui.DialogUtil;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ExpenseDependencyWindow extends AbstractEventDependenciesWindow{

	private Button addItemButton;
	
	public ExpenseDependencyWindow() {
		super("Expense");
		addItemButton = (Button) getScene().lookup("#addItemButton");
		addItemButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ObservableList<Node> nodeList = getPaneList();
				nodeList.add(getEachPane(nodeList.size()));
			}
		});
	}

	@Override
	protected Pane getEachPane(final int index) {
		Pane pane = new Pane();
		HBox hbox = new HBox();
		
		TextField nameField = new TextField();
		nameField.setId("name_" + index);
		nameField.setPromptText("Name");
		
		TextField costField = new TextField();
		costField.setId("cost_" + index);
		costField.setPromptText("Cost");
		
		hbox.getChildren().addAll(nameField, costField);
		pane.getChildren().add(hbox);
		return pane;
	}

	@Override
	protected List<DataObject> getSelectedObjects() {
		ObservableList<Node> paneList = getPaneList();
		List<DataObject> listObject = new ArrayList<DataObject>();
		try{
			for(int i = 0; i < paneList.size(); i++){
				Node node = paneList.get(i);
				TextField nameField = (TextField)node.lookup("#name_" + i);
				TextField costField = (TextField)node.lookup("#cost_" + i);
				String name = nameField.getText();
				double cost = Double.parseDouble(costField.getText());
				if(name.length() == 0){
					DialogUtil.showErrorDialog("Name cannot be empty");
					return null;
				}
				listObject.add(new Expense(name, cost));
			}
			return listObject;
		}catch(NumberFormatException e){
			e.printStackTrace();
			DialogUtil.showErrorDialog("Invalid cost");
			return null;
		}
	}
}
