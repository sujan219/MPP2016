package com.ems.data.dao;

import org.json.JSONArray;

import com.ems.baseclasses.Event;
import com.ems.baseclasses.OffCampusEvent;

public class OffCampusEventDao extends EventDao {

	public OffCampusEventDao(Object event) {
		super((Event)event);
	}

	@Override
	protected JSONArray getTransport() {
		OffCampusEvent offCampEvent = (OffCampusEvent) event;
		return null;
	}

}
