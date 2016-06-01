package com.ems.baseclasses;

import java.util.Calendar;

public class OffCampusEvent extends Event{

	
	
	public OffCampusEvent(int id, String location, String description, Calendar startDateTime, Calendar endDateTime,
			String type, double fund) {
		super(id, location, description, startDateTime, endDateTime, type, fund);
	}

	
	
}
