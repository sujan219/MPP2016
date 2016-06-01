package com.ems.ui.transport;

import java.util.Arrays;
import java.util.List;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Transport;
import com.ems.data.dao.DataReadException;
import com.ems.data.dao.TransportDao;
import com.ems.ui.AddActionWindow;
import com.ems.ui.ListWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TransportListWindow extends ListWindow{

	private static final String TITLE = "Transport";
	public TransportListWindow() {
		super(TITLE);
	}

	@Override
	protected List<String> getColumns() {
		return Arrays.asList("Id", "Name", "Description", "Capacity");
	}

	@Override
	protected ObservableList<ObservableList<String>> getTableContent() throws DataReadException {
		try
		{
			TransportDao tDao = new TransportDao();
			List<DataObject> list = tDao.getAllRecords();
			ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
			for(DataObject eachDataObject : list){
				Transport eachTransport = (Transport) eachDataObject;
				ObservableList<String> row = FXCollections.observableArrayList();
				row.add(eachTransport.getId() + "");
				row.add(eachTransport.getName());
				row.add(eachTransport.getDescription());
				row.add(eachTransport.getCapacity() + "");
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
		return new TransportAddWindow(id, this);
	}

	@Override
	protected DataObject getDataObjectById(int id) {
		return new Transport(id, null, null, 0);
	}
}
