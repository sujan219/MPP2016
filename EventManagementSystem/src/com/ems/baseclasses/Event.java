package com.ems.baseclasses;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class Event extends DataObject {

	private int id;
	private String name;
	private String location;
	private String description;
	private Calendar startDateTime;
	private Calendar endDateTime;
	private String type;
	private double fund;
	
	private List<Personnel> managerList;
	private List<Resource> resourceList;
	private List<Student> studentList;
	private List<Expense> expenseList;
	
	public Event(int id, String location, String description, Calendar startDateTime, Calendar endDateTime, String type, double fund){
		this.id = id;
		this.location = location;
		this.description = description;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.type = type;
		this.fund = fund;
		
		managerList = new ArrayList<>();
		resourceList = new ArrayList<>();
		studentList = new ArrayList<>();
		expenseList = new ArrayList<>();
	}
	
	@Override
	public int getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Calendar getStartDateTime() {
		return startDateTime;
	}
	
	public Calendar getEndDateTime() {
		return endDateTime;
	}
	
	public String getType() {
		return type;
	}
	
	public double getFund() {
		return fund;
	}
	
	public void addResourceInEvent(Resource r){
		resourceList.add(r);
	}
	
	public void addStudentInEvent(Student s){
		studentList.add(s);
	}
	
	public void removeResourceFromEvent(Resource r){
		//TODO
	}
	
	public void removeStudentFromEvent(Student s){
		//TODO
	}
	
	private void setTotalFund(double totalFund) {
		fund = totalFund;
	}
	
	public void addEventManager(Personnel p){
		managerList.add(p);
	}
	
	private void addExpense(Expense e) {
		expenseList.add(e);
	}
	
	private void removeExpense() {
		// TODO Auto-generated method stub
	}
	
	private void generateReport() {
		// TODO Auto-generated method stub
	}
}
