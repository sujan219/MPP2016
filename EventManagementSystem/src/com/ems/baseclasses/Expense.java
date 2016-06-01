package com.ems.baseclasses;

public class Expense {
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
}
