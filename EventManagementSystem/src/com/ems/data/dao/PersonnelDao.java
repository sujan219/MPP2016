package com.ems.data.dao;

import org.json.JSONObject;

import com.ems.baseclasses.Personnel;
import com.ems.data.DataSaver;

public class PersonnelDao implements DaoInterface{

	private Personnel personnel;
	private static final String FILE_NAME = "personnel.json";
	private static final String KEY_ID = "Id";
	private static final String KEY_NAME = "Name";
	private static final String KEY_DESCRIPTION = "Description";
	private static final String KEY_IS_EVENT_MANAGER = "IsEventManager";
	private static final String KEY_IS_DRIVER = "IsDriver";
	
	public PersonnelDao(Object obj) {
		personnel = (Personnel) obj;
	}
	
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
			throw new DataSaveException();
		}
	}

	@Override
	public void modifyRecord() throws DataSaveException {
		
	}

	@Override
	public void deleteRecord() throws DataSaveException {
		
	}
}
