package com.ems.data.dao;

import org.json.JSONException;
import org.json.JSONObject;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Personnel;
import com.ems.data.DataSaver;
import com.ems.ui.DialogUtil;

public class PersonnelDao extends AbstractDao{

	private Personnel personnel;
	public static final String FILE_NAME = "personnel.json";
	public static final String KEY_ID = "Id";
	public static final String KEY_NAME = "Name";
	public static final String KEY_DESCRIPTION = "Description";
	public static final String KEY_IS_EVENT_MANAGER = "IsEventManager";
	public static final String KEY_IS_DRIVER = "IsDriver";
	
	public PersonnelDao(Object obj) {
		super((Personnel)obj);
		personnel = (Personnel) obj;
	}
	
	public PersonnelDao(){}
	
	@Override
	public JSONObject getJSONObject() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put(KEY_ID, personnel.getId());
		obj.put(KEY_NAME, personnel.getName());
		obj.put(KEY_DESCRIPTION, personnel.getDescription());
		obj.put(KEY_IS_EVENT_MANAGER, personnel.isEventManager());
		obj.put(KEY_IS_DRIVER, personnel.isDriver());
		return obj;
	}

	@Override
	public String getFileName() {
		return FILE_NAME;
	}

	@Override
	public DataObject getObjectFromJSON(JSONObject obj) throws JSONException {
		int id = obj.getInt(KEY_ID);
		String name = obj.getString(KEY_NAME);
		String description = obj.getString(KEY_DESCRIPTION);
		boolean isEventManager = obj.getBoolean(KEY_IS_EVENT_MANAGER);
		boolean isDriver = obj.getBoolean(KEY_IS_DRIVER);
		return new Personnel(id, name, description, isEventManager, isDriver);
	}
}
