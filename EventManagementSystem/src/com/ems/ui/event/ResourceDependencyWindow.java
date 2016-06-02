package com.ems.ui.event;

import java.util.ArrayList;
import java.util.List;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Resource;
import com.ems.ui.DialogUtil;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ResourceDependencyWindow extends AbstractEventDependenciesWindow{

	public ResourceDependencyWindow() {
		super("Resource");
	}

	@Override
	protected Pane getEachPane(int index) {
		Resource res = (Resource) getItem(index);	
		Pane pane = new Pane();
		HBox hbox = new HBox();
		CheckBox chk = new CheckBox();
		chk.setId("checkBox_" + index);
		
		TextField quantityField = new TextField();
		quantityField.setId("quantity_" + index);
		
		hbox.getChildren().addAll(new Label(res.getName()), chk, quantityField, new Label("(Max " + res.getQuantity() + ")"));
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
				CheckBox chk = (CheckBox)node.lookup("#checkBox_" + i);
				if(chk.isSelected()){
					Resource res = (Resource) getItem(i);
					int availableQuantity = res.getQuantity();
					TextField quantityField = (TextField) node.lookup("#quantity_" + i);
					int inputQuantity = Integer.parseInt(quantityField.getText());
					if(inputQuantity>availableQuantity){
						DialogUtil.showErrorDialog("Insufficient quantity");
						return null;
					}
					listObject.add(res);
				}
			}
			return listObject;
		}catch(NumberFormatException e){
			e.printStackTrace();
			DialogUtil.showErrorDialog("Invalid quantity");
			return null;
		}
	}
}
