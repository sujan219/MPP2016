package com.ems.ui.resource;

import java.util.Arrays;
import java.util.List;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Resource;
import com.ems.data.dao.DataReadException;
import com.ems.data.dao.ResourceDao;
import com.ems.ui.AddActionWindow;
import com.ems.ui.ListWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ResourceListWindow extends ListWindow{

	private static final String TITLE = "Resource";
	public ResourceListWindow() {
		super(TITLE);
	}

	@Override
	protected List<String> getColumns() {
		return Arrays.asList("Id", "Name", "Quantity", "Description");
	}
	
	@Override
	protected ObservableList<ObservableList<String>> getTableContent() throws DataReadException {
		try{
			ResourceDao sDao = new ResourceDao();
			List<DataObject> dataList = sDao.getAllRecords();
			ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
			for(DataObject eachDataObject:dataList){
				Resource eachResource = (Resource) eachDataObject;
				ObservableList<String> row = FXCollections.observableArrayList();
				row.add(eachResource.getId() + "");
				row.add(eachResource.getName());
				row.add(eachResource.getQuantity() + "");
				row.add(eachResource.getDescription());
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
		return new ResourceAddWindow(id, this);
	}

	@Override
	protected DataObject getDataObjectById(int id) {
		return new Resource(id, null, 0, null);
	}
}
