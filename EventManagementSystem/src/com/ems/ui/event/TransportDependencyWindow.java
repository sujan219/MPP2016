package com.ems.ui.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Personnel;
import com.ems.baseclasses.Resource;
import com.ems.baseclasses.Transport;
import com.ems.data.dao.DataReadException;
import com.ems.data.dao.PersonnelDao;
import com.ems.ui.DialogUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class TransportDependencyWindow extends AbstractEventDependenciesWindow{

	public TransportDependencyWindow() {
		super("Transport");
	}

	@Override
	protected Pane getEachPane(int index) {
		Transport transport = (Transport) getItem(index);	
		Pane pane = new Pane();
		HBox hbox = new HBox();
		CheckBox chk = new CheckBox();
		chk.setId("checkBox_" + index);
		
		PersonnelDao dao = new PersonnelDao();
		Map<String, String> map = new HashMap<>();
		map.put(PersonnelDao.KEY_IS_DRIVER, "true");
		
		try {
			List<DataObject> driverList = dao.getAllRecords(map);
			ObservableList<DataObject> oList = FXCollections.observableArrayList();
			for(DataObject temp:driverList){
				oList.add(temp);
			}
			ComboBox<DataObject> driverCBox = new ComboBox<>();
			driverCBox.setId("driverCBox_" + index);
			driverCBox.setItems(oList);
			
			hbox.getChildren().addAll(new Label(transport.getName()), chk, driverCBox);
			pane.getChildren().add(hbox);
			return pane;
		} catch (DataReadException e) {
			e.printStackTrace();
			DialogUtil.showErrorDialog(e.getMessage());
		}
		return null;
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
					Transport res = (Transport) getItem(i);
					ComboBox<DataObject> driverCBox = (ComboBox) node.lookup("#driverCBox_" + i);
					Personnel selectedDriver = (Personnel)driverCBox.getSelectionModel().getSelectedItem();
					if(selectedDriver == null){
						DialogUtil.showErrorDialog("Select driver!");
						return null;
					}
					res.setDriver(selectedDriver);
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
