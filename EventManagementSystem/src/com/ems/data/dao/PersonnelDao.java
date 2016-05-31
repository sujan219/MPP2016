package com.ems.data.dao;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ems.baseclasses.Personnel;
import com.ems.data.DataSaver;

public class PersonnelDao implements DaoInterface{

	private Personnel personnel;
	public static final String FILE_NAME = "personnel.json";
	public static final String KEY_ID = "Id";
	public static final String KEY_NAME = "Name";
	public static final String KEY_DESCRIPTION = "Description";
	public static final String KEY_IS_EVENT_MANAGER = "IsEventManager";
	public static final String KEY_IS_DRIVER = "IsDriver";
	
	public PersonnelDao(Object obj) {
		personnel = (Personnel) obj;
	}
	
	public PersonnelDao(){}
	
	@Override
	public void saveNewRecord() throws DataSaveException {
		try{
			JSONObject obj = new JSONObject();
			obj.put(KEY_NAME, personnel.getName());
			obj.put(KEY_DESCRIPTION, personnel.getDescription());
			obj.put(KEY_IS_EVENT_MANAGER, personnel.isEventManager());
			obj.put(KEY_IS_DRIVER, personnel.isDriver());
			DataSaver.writeData(FILE_NAME, obj, true);
		}catch(Exception e){
			e.printStackTrace();
			throw new DataSaveException();
		}
	}

	@Override
	public void modifyRecord() throws DataSaveException {
		
	}

	@Override
	public void deleteRecord() throws DataSaveException {
		
	}

	@Override
	public JSONObject getRecordById(int id) throws DataReadException {
		return null;
	}

	@Override
	public JSONArray getAllRecords() throws DataReadException {
		return DataSaver.readAllData(FILE_NAME);
	}
}
