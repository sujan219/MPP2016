package com.ems.ui.event;

import java.util.List;

import com.ems.baseclasses.DataObject;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class StudentDependencyWindow extends AbstractEventDependenciesWindow{

	public StudentDependencyWindow() {
		super("Student");
	}

	@Override
	protected Pane getEachPane(int index) {
		Pane pane = new Pane();
		HBox hbox = new HBox();
		hbox.getChildren().addAll(new Label("test1"), new Label("test2"));
		pane.getChildren().add(hbox);
		return pane;
	}

	@Override
	protected List<DataObject> getSelectedObjects() {
		return null;
	}
}
