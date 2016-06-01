package com.ems.data.dao;

import org.json.JSONException;
import org.json.JSONObject;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Resource;
import com.ems.baseclasses.Student;

public class ResourceDao extends AbstractDao{
	private Resource resource;
	public static final String KEY_ID = "Id";
	public static final String KEY_NAME = "Name";
	public static final String KEY_QUANTITY = "Quantity";
	public static final String KEY_DESCRIPTION = "Description";
	public static final String FILE_NAME = "resource.json";
	
	public ResourceDao(Object obj){
		super((Resource)obj);
		resource = (Resource) obj;
	}
	public ResourceDao(){}
	
	@Override
	public DataObject getObjectFromJSON(JSONObject obj) throws JSONException {
		int id = obj.getInt(KEY_ID);
		String name = obj.getString(KEY_NAME);
		int quantity = obj.getInt(KEY_QUANTITY);
		String description = obj.getString(KEY_DESCRIPTION);
		return new Resource(id, name, quantity, description);
	}
	
	@Override
	public String getFileName() {
		return FILE_NAME;
	}
	
	@Override
	public JSONObject getJSONObject() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put(KEY_ID, resource.getId());
		obj.put(KEY_NAME, resource.getName());
		obj.put(KEY_QUANTITY, resource.getQuantity());
		obj.put(KEY_DESCRIPTION, resource.getDescription());
		return obj;
	}
}
