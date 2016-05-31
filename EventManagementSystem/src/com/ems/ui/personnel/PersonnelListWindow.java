package com.ems.ui.personnel;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ems.data.dao.DataReadException;
import com.ems.data.dao.PersonnelDao;
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
			JSONArray jsonArray = pDao.getAllRecords();
			ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
			for(int i=0; i<jsonArray.length(); ++i){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				ObservableList<String> row = FXCollections.observableArrayList();
				row.add(jsonObject.getInt(PersonnelDao.KEY_ID) + "");
				row.add(jsonObject.getString(PersonnelDao.KEY_NAME));
				row.add(jsonObject.getString(PersonnelDao.KEY_DESCRIPTION));
				row.add(jsonObject.getBoolean(PersonnelDao.KEY_IS_EVENT_MANAGER) + "");
				row.add(jsonObject.getBoolean(PersonnelDao.KEY_IS_DRIVER) + "");
				data.add(row);
			}
			return data;
		}catch(Exception e){
			e.printStackTrace();
			throw new DataReadException();
		}
	}
	
}
