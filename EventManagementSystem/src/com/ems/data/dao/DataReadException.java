package com.ems.data.dao;

public class DataReadException extends Exception {
	private String msg;
	public DataReadException(){
		this("Failed to read data");
	}
	
	public DataReadException(String msg){
		super(msg);
		this.msg = msg;
	}
}
