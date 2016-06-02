package com.ems.baseclasses;

import java.util.Calendar;

public class OnCampusEvent extends Event {

	public OnCampusEvent(int id, String name, String description, Calendar startDateTime, Calendar endDateTime,
			String type, double fund) {
		super(id, name, "OnCampus", description, startDateTime, endDateTime, type, fund);
	}
}
