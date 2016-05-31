package com.ems.ui.student;

import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ems.data.dao.DataReadException;
import com.ems.data.dao.PersonnelDao;
import com.ems.data.dao.StudentDao;
import com.ems.ui.AddActionWindow;
import com.ems.ui.ListWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentListWindow extends ListWindow{

	private static final String TITLE = "Personnel";
	public StudentListWindow() {
		super(TITLE);
	}

	@Override
	protected List<String> getColumns() {
		return Arrays.asList("Id", "Name", "Email", "Entry");
	}
	
	@Override
	protected ObservableList<ObservableList<String>> getTableContent() throws DataReadException {
		try{
			StudentDao sDao = new StudentDao();
			JSONArray jsonArray = sDao.getAllRecords();
			ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
			for(int i=0; i<jsonArray.length(); ++i){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				ObservableList<String> row = FXCollections.observableArrayList();
				row.add(jsonObject.getInt(StudentDao.KEY_ID) + "");
				row.add(jsonObject.getString(StudentDao.KEY_NAME));
				row.add(jsonObject.getString(StudentDao.KEY_EMAIL));
				row.add(jsonObject.getString(StudentDao.KEY_ENTRY) + "");
				data.add(row);
			}
			return data;
		}catch(Exception e){
			e.printStackTrace();
			throw new DataReadException();
		}
	}

	@Override
	protected AddActionWindow getAddActionWindow() {
		// TODO Auto-generated method stub
		return null;
	}
}
