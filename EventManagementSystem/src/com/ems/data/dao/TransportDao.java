package com.ems.data.dao;

import org.json.JSONException;
import org.json.JSONObject;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Transport;

public class TransportDao extends AbstractDao {
	public static final String KEY_ID = "Id";
	public static final String KEY_NAME = "Name";
	public static final String KEY_DESCRIPTION = "Description";
	public static final String KEY_CAPACITY = "Capacity";
	
	public static final String FILE_NAME = "transport.json";
	public Transport transport;
	public TransportDao()
	{
		
	}
	public TransportDao(Object obj)
	{
		super((Transport)obj);
		transport = ((Transport)obj);
	}

	@Override
	public DataObject getObjectFromJSON(JSONObject obj) throws JSONException {
		
		int id = obj.getInt(KEY_ID);
		String name = obj.getString(KEY_NAME);
		String description = obj.getString(KEY_DESCRIPTION);
		int capacity = obj.getInt(KEY_CAPACITY);
		return new Transport(id, name, description, capacity);
				
	}

	@Override
	public JSONObject getJSONObject() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(KEY_ID, transport.getId());
		json.put(KEY_NAME, transport.getName());
		json.put(KEY_DESCRIPTION, transport.getDescription());
		json.put(KEY_CAPACITY, transport.getCapacity());
		return json;
	}

	@Override
	public String getFileName() {
		return FILE_NAME;
	}
}
