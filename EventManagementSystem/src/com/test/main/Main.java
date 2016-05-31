package com.test.main;

import org.json.JSONException;

import com.ems.baseclasses.Student;
import com.ems.baseclasses.UserLogin;
import com.ems.data.dao.DaoFactory;
import com.ems.data.dao.DaoInterface;
import com.ems.data.dao.UserLoginDao;

public class Main {
	public static void main(String[] args) throws JSONException {		
		UserLogin user = new UserLogin("Sujan", "test1233");
		UserLoginDao loginDao = new UserLoginDao(user);
		System.out.println(loginDao.authUser());
		
		DaoInterface interfaceD = DaoFactory.getDaoInterface(new Student());
		if(interfaceD == null){
			System.out.println("null");
		}else{
			interfaceD.saveNewRecord();
		}
	}
}