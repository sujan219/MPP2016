package com.ems.baseclasses;

public class UserLogin {
	private String userName;
	private String password;
	
	public UserLogin(String aUserName, String aPassword){
		userName = aUserName;
		password = aPassword;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public String getPassword(){
		return password;
	}
}
