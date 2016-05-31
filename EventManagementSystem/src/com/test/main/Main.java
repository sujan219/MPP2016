package com.test.main;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.ems.baseclasses.Student;
import com.ems.data.DataSaver;
import com.ems.data.dao.DaoFactory;
import com.ems.data.dao.DaoInterface;

public class Main {
	public static void main(String[] args) throws JSONException {		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("UserName", "Bande");
		map.put("Password", "Dande");
		JSONObject data = DataSaver.readData("userlogin.json", map);
		if(data == null){
			System.out.println("null");
		}else{
			System.out.println(data);
		}
		
		DaoInterface interfaceD = DaoFactory.getDaoInterface(new Student());
		if(interfaceD == null){
			System.out.println("null");
		}else{
			interfaceD.saveNewRecord();
		}
	}
}