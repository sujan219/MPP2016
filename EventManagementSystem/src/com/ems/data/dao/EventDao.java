package com.ems.data.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ems.baseclasses.DataObject;
import com.ems.baseclasses.Event;

public abstract class EventDao extends AbstractDao {

	protected Event event;
	private static final String FILE_NAME = "event.json";
	
	public EventDao(Event event){
		this.event = event;
	}
	
	public EventDao(){}
	
	@Override
	public DataObject getObjectFromJSON(JSONObject obj) throws JSONException {
		return null;
	}

	@Override
	public JSONObject getJSONObject() throws JSONException {
		return null;
	}

	@Override
	public String getFileName() {
		return FILE_NAME;
	}
	
	protected abstract JSONArray getTransport();

}
