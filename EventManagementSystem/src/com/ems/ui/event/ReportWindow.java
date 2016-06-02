package com.ems.ui.event;

import java.io.IOException;
import java.util.List;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Event;
import com.ems.baseclasses.OffCampusEvent;
import com.ems.ui.ModalDialog;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ReportWindow extends ModalDialog{
	
	private VBox reportVBox;
	
	public ReportWindow(Event e){
		Parent root;
		try {
			setTitle("Report");
			root = FXMLLoader.load(getClass().getResource("ReportWindow.fxml"));
			setScene(new Scene(root));
			show();
			reportVBox = (VBox) root.lookup("#reportVBox");
			generateReport(e);
			getScene().getStylesheets().add(getClass().getResource("report.css").toExternalForm());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void generateReport(Event e) {
		Label nameLabel = new Label("Name: " + e.getName());
		Label descLabel = new Label("Description: " + e.getDescription());
		Label locLabel = new Label("Location: " + e.getLocation());
		String start = Event.dateFormat.format(e.getStartDateTime().getTime());
		String end = Event.dateFormat.format(e.getEndDateTime().getTime());
		Label startLabel = new Label("Start time: " + start);
		Label endLabel = new Label("End time: " + end);
		Label typeLabel = new Label("Type: " + e.getType());
		Label fundLabel = new Label("Fund: " + e.getFund());
		
		String managers = getListLabelString(e.getManagerList(), "Managers");
		Label managerLabel = new Label(managers);
		
		String resources = getListLabelString(e.getResourceList(), "Resources");
		Label resourceLabel = new Label(resources);
		
		String students = getListLabelString(e.getStudentList(), "Students");
		Label studentLabel = new Label(students);
		
		String expenses = getListLabelString(e.getExpenseList(), "Expenses");
		Label expenseLabel = new Label(expenses);
		
		Label transportLabel = new Label();
		if(e instanceof OffCampusEvent){
			OffCampusEvent oce = (OffCampusEvent)e;
			String transports = getListLabelString(oce.getTransportList(), "Transports");
			transportLabel.setText(transports);
		}
		
		reportVBox.getChildren().addAll(nameLabel, descLabel, locLabel, startLabel, endLabel, typeLabel, fundLabel, managerLabel, resourceLabel, studentLabel, expenseLabel, transportLabel);
	}
	
	private <T extends DataObject> String getListLabelString(List<T> list, String labelName){
		String label = "";
		if(list != null){
			label = labelName + ": "; 
			for(int i=0; i<list.size(); ++i){
				DataObject obj = list.get(i);
				if(i>0){
					label += "\t\t";
				}
				label += obj.toString() + "\n";
			}
			
		}
		return label;
	}
}
