package com.ems.ui.event;

import java.util.ArrayList;
import java.util.List;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Personnel;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PersonnelDependencyWindow extends AbstractEventDependenciesWindow{

	public PersonnelDependencyWindow() {
		super("Personnel");
	}

	@Override
	protected Pane getEachPane(int index) {
		Pane pane = new Pane();
		HBox hbox = new HBox();
		
		Personnel p = (Personnel) getItem(index);
		Label nameLabel = new Label(p.getName());
		CheckBox cBox = new CheckBox();
		cBox.setId("checkBox_" + index);
		hbox.getChildren().addAll(nameLabel, cBox);
		pane.getChildren().add(hbox);
		return pane;
	}

	@Override
	protected List<DataObject> getSelectedObjects() {
		List<DataObject> listToReturn = new ArrayList<>();
		ObservableList<Node> nodeList = getPaneList();
		for(int i=0; i<nodeList.size(); ++i){
			CheckBox cb = (CheckBox) nodeList.get(i).lookup("#checkBox_" + i);
			if(cb.isSelected()){
				listToReturn.add(getItem(i));
			}
		}
		return listToReturn;
	}
}
