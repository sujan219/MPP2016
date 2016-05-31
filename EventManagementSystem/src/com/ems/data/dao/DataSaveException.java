package com.ems.data.dao;

public class DataSaveException extends Exception {
	private String msg;
	public DataSaveException(){
		this("Failed to save data");
	}
	
	public DataSaveException(String msg){
		super(msg);
		this.msg = msg;
	}
}
