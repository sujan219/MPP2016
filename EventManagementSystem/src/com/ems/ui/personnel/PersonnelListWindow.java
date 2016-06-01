package com.ems.ui.personnel;

import java.util.Arrays;
import java.util.List;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Personnel;
import com.ems.data.dao.DataReadException;
import com.ems.data.dao.PersonnelDao;
import com.ems.ui.AddActionWindow;
import com.ems.ui.ListWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonnelListWindow extends ListWindow {

	private static final String TITLE = "Personnel";
	
	public PersonnelListWindow() {
		super(TITLE);
	}

	@Override
	protected List<String> getColumns() {
		return Arrays.asList("Id", "Name", "Description", "IsEventManager", "IsDriver");
	}

	@Override
	protected ObservableList<ObservableList<String>> getTableContent() throws DataReadException {
		try{
			PersonnelDao pDao = new PersonnelDao();
			List<DataObject> dataList = pDao.getAllRecords();
			ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
			for(DataObject dataObject:dataList){
				Personnel personnel = (Personnel) dataObject;
				ObservableList<String> row = FXCollections.observableArrayList();
				row.add(personnel.getId() + "");
				row.add(personnel.getName());
				row.add(personnel.getDescription());
				row.add(personnel.isEventManager() + "");
				row.add(personnel.isDriver() + "");
				data.add(row);
			}
			return data;
		}catch(Exception e){
			e.printStackTrace();
			throw new DataReadException();
		}
	}

	@Override
	protected AddActionWindow getAddActionWindow(int id) {
		return new PersonnelAddWindow(id, this);
	}

	@Override
	protected DataObject getDataObjectById(int id) {
		return new Personnel(id, null, null, false, false);
	}
}
