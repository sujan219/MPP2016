package com.ems.data.dao;

import java.util.HashMap;

import org.json.JSONObject;

import com.ems.baseclasses.UserLogin;
import com.ems.data.DataSaver;

public class UserLoginDao {
	
	private UserLogin userLogin;
	private static final String FILE_NAME = "userlogin.json";
	private static final String KEY_USERNAME = "UserName";
	private static final String KEY_PASSWORD = "Password";
	
	public UserLoginDao(UserLogin aUserLogin){
		userLogin = aUserLogin;
	}
	
	public boolean authUser(){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(KEY_USERNAME, userLogin.getUserName());
		map.put(KEY_PASSWORD, userLogin.getPassword());
		
		JSONObject data = DataSaver.readData(FILE_NAME, map);
		if(data == null){
			return false;
		}else{
			return true;
		}
	}
}
