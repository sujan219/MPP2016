package com.ems.data.dao;

import org.json.JSONArray;

import com.ems.baseclasses.Event;

public class OnCampusEventDao extends EventDao {

	public OnCampusEventDao(Object event) {
		super((Event)event);
	}
	
	@Override
	protected JSONArray getTransport() {
		return null;
	}

}
