package com.ems.ui;

import java.io.IOException;
import java.util.List;

import com.ems.data.dao.DataReadException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

public abstract class ListWindow extends Stage {
	
	private AddActionWindow addActionWindow;
	private TableView tableView;
	
	public ListWindow(String title){
		setTitle(title);
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../ListWindow.fxml"));
			setScene(new Scene(root));
			show();
			
			tableView = (TableView) root.lookup("#tableView");
			initTable();
			loadDataIntoTable();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initTable(){
		List<String> columns = getColumns();
		float columnWidth = columns.size();
		TableColumn [] tableColumns = new TableColumn[columns.size()];     
	    int columnIndex = 0;
	    for(int i=0 ; i<columns.size(); i++) {
	        final int j = i;
	        TableColumn col = new TableColumn(columns.get(i));
	        col.prefWidthProperty().bind(tableView.widthProperty().divide(columnWidth));
	        col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                   
	           public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                             
	                return new SimpleStringProperty(param.getValue().get(j).toString());                       
	            }                   
	        });
	        tableView.getColumns().addAll(col);
	    }       
	}
	
	private void loadDataIntoTable(){
		try {
			tableView.setItems(getTableContent());
		} catch (DataReadException e) {
			e.printStackTrace();
		}
	}
	
	public void addAction(){
		//use addActionWindow
	}
	
	public void editAction(){
		
	}
	
	protected abstract List<String> getColumns();
	protected abstract ObservableList<ObservableList<String>> getTableContent() throws DataReadException;
}
