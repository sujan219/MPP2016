package com.ems.baseclasses;

public class Expense extends DataObject {
	private String name;
	private double cost;
	
	public Expense(String name, double cost){
		this.name = name;
		this.cost = cost;
	}
	
	public String getName() {
		return name;
	}
	
	public double getCost() {
		return cost;
	}

	@Override
	public int getId() {
		return 0;
	}
	
	@Override
	public String toString() {
		return name + " : " + cost;
	}
}
