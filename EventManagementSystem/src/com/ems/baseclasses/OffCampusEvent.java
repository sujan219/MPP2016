package com.ems.baseclasses;

import java.util.Calendar;
import java.util.List;

public class OffCampusEvent extends Event{

	private List<Transport> transportList;
	
	public OffCampusEvent(int id, String name, String location, String description, Calendar startDateTime, Calendar endDateTime,
			String type, double fund) {
		super(id, name, location, description, startDateTime, endDateTime, type, fund);
	}

	public void setTransportList(List<Transport> transportList){
		this.transportList = transportList;
	}
	
	public List<Transport> getTransportList() {
		return transportList;
	}
	
}
