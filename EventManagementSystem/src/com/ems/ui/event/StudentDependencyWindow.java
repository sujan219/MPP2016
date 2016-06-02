package com.ems.ui.event;

import java.util.ArrayList;
import java.util.List;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Student;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class StudentDependencyWindow extends AbstractEventDependenciesWindow{

	public StudentDependencyWindow() {
		super("Student");
	}

	@Override
	protected Pane getEachPane(int index) {
		Student std = (Student) getItem(index);	
		Pane pane = new Pane();
		HBox hbox = new HBox();
		CheckBox chk = new CheckBox();
		chk.setId("checkBox_" + index);
		hbox.getChildren().addAll(new Label(std.getName()), chk);
		pane.getChildren().add(hbox);
		return pane;
	}

	@Override
	protected List<DataObject> getSelectedObjects() {
		ObservableList<Node> paneList = getPaneList();
		List<DataObject> listObject = new ArrayList<DataObject>();
		for(int i = 0; i < paneList.size(); i++){
			Node node = paneList.get(i);
			CheckBox chk = (CheckBox)node.lookup("#checkBox_" + i);
			if(chk.isSelected()){
				listObject.add(getItem(i));
			}
		}
		return listObject;
	}
}
