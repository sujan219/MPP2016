package com.ems.ui.event;

import java.util.List;

import com.ems.baseclasses.DataObject;
import com.ems.data.dao.AbstractDao;
import com.ems.data.dao.DaoFactory;
import com.ems.ui.DialogUtil;
import com.ems.ui.ModalDialog;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public abstract class AbstractEventDependenciesWindow extends ModalDialog{
	
	private List<DataObject> dataObjectList;
	private VBox vBox;
	private DependencyAddedListener listener;
	
	public AbstractEventDependenciesWindow(String title){
		try {
			setTitle(title);
			Parent root = FXMLLoader.load(getClass().getResource("AddDependenciesWindow.fxml"));
			setScene(new Scene(root));
			show();
			
			AbstractDao aDao = DaoFactory.getDaoInterface(title);
			dataObjectList = aDao.getAllRecords();
			
			vBox = (VBox) root.lookup("#vBox");
			
			for(int i=0; i<dataObjectList.size(); ++i){
				Pane pane = getEachPane(i);
				pane.setId("pane_" + i);
				vBox.getChildren().add(pane);
			}
			
			Button addButton = (Button) root.lookup("#addButton");
			addButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					List<DataObject> obj = getSelectedObjects();
					if(obj != null && listener != null){
						listener.dependencyAdded(obj);
						AbstractEventDependenciesWindow.this.close();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			DialogUtil.showErrorDialog(e.getMessage());
		}
	}
	
	public void setDependencyAddedListener(DependencyAddedListener listener){
		this.listener = listener;
	}
	
	protected abstract Pane getEachPane(int index);
	
	protected DataObject getItem(int index){
		return dataObjectList.get(index);
	}
	
	protected ObservableList<Node> getPaneList(){
		return vBox.getChildren();
	}
	
	protected abstract List<DataObject> getSelectedObjects();
}