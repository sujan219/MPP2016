package com.ems.ui.personnel;

import java.util.Arrays;
import java.util.List;

import com.ems.ui.ListWindow;

public class PersonnelListWindow extends ListWindow {

	private static final String TITLE = "Personnel";
	
	public PersonnelListWindow() {
		super(TITLE);
	}

	@Override
	protected List<String> getColumns() {
		return Arrays.asList("Id", "Name", "Description", "IsEventManager", "IsDriver");
	}
	
}
